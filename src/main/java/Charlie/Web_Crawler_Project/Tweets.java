package Charlie.Web_Crawler_Project;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tweets {
    private Twitter twitter;

    //Establish twitter connection
    public void tweetManager() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("kCz7E2IGV5ye71Ct79bsvq09a")
                .setOAuthConsumerSecret("jvHsqAeVIIYIdutfOzy5d8YQTtuxGZl5fdFPEc0lzDOvhmywEJ")
                .setOAuthAccessToken("370000643-X1HRE3RRuLE5WbfN9rthGlh7fr9NwlQ5laujtovE")
                .setOAuthAccessTokenSecret("KobVEymuTxqgFl6tf5dAfcWTPZCM5JKoHX2SZzeZYHF3f");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    //Perform query
    public List<Status> performQuery(String keyword, String num) throws InterruptedException, IOException {
        Query query = new Query(keyword + " -filter:retweets -filter:links -filter:replies -filter:images");
        int numberOfTweets = Integer.parseInt(num);

        //set language to English
        query.setLang("en");

        long lastID = Long.MAX_VALUE;
        //create arrayList to store tweets
        ArrayList<Status> tweets = new ArrayList<Status>();
        int write_count = 1;
        //&& numberOfTweets/100 < write_count+1
        //get tweets
        while (tweets.size() < numberOfTweets) {
            if (numberOfTweets - tweets.size() > 100)
                query.setCount(100);
            else
                query.setCount(numberOfTweets - tweets.size());
            try {
                QueryResult result = twitter.search(query);

                //add tweets to arrayList tweets
                tweets.addAll(result.getTweets());
                System.out.println("Gathered " + tweets.size() + " tweets");

                for (Status t : tweets)
                    if (t.getId() < lastID) lastID = t.getId();

            } catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            }
            query.setMaxId(lastID - 1);

            write_count++;
        }
        return tweets;
    }

    //Retrieves the trends of a particular location.
    public List<String> getLocationTrends(String location) throws TwitterException {

        Integer idTrendLocation = getTrendLocationId(location);

        ArrayList<String> trendsList = new ArrayList<String>();

        try {

            if (idTrendLocation == null) {
                System.out.println("Trend Location Not Found");
            }

            //getting trends based on the location ID
            Trends trends = twitter.getPlaceTrends(idTrendLocation);

            for (int i = 0; i < trends.getTrends().length; i++) {
                trendsList.add(trends.getTrends()[i].getName());
                //System.out.println(trends.getTrends()[i].getName());
            }

            //Catching error
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get trends: " + te.getMessage());
            System.exit(-1);
        }
        //Returns list for writing to file
        return trendsList;
    }

    private Integer getTrendLocationId(String placeName) {
        int LocationID = 0;
        if (placeName == "") {
            LocationID = 1;
            return LocationID;
        }
        try {
            ResponseList<Location> locations;
            locations = twitter.getAvailableTrends();

            for (Location location : locations) {
                if (location.getName().equalsIgnoreCase(placeName.toLowerCase())) {
                    LocationID = location.getWoeid();
                    break;
                }
            }

            //Checking if is valid
            if (LocationID > 0) {
                return LocationID;
            }
            return null;

            //Catching error if location does not exist
        } catch (TwitterException te) {
            System.out.println("Failed to get trends: " + te.getMessage());
            return null;
        }
    }
}
