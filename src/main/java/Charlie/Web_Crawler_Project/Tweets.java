package Charlie.Web_Crawler_Project;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Tweets.
 */
public class Tweets {
    //Declare global variable twitter
    private Twitter twitter;

    /**
     * Tweet manager to establish twitter connection.
     */
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

    /**
     * Perform query to retrieve tweets.
     *
     * @param keyword the keyword
     * @param num     the num
     * @return the list
     * @throws InterruptedException the interrupted exception
     * @throws IOException          the io exception
     */
    public List<Status> performQuery(String keyword, String num) throws InterruptedException, IOException {
        //Filter out tweets with retweets, links, replies and images
        Query query = new Query(keyword + " -filter:retweets -filter:links -filter:replies -filter:images");

        //set language to English
        query.setLang("en");

        long lastID = Long.MAX_VALUE;
        //create arrayList to store tweets
        ArrayList<Status> tweets = new ArrayList<Status>();
        int numberOfTweets = Integer.parseInt(num);

        //get tweets, while loop used to retrieve 100 tweets at a time
        while (tweets.size() < numberOfTweets) {
            if (numberOfTweets - tweets.size() > 100)
                query.setCount(100);
            else
                query.setCount(numberOfTweets - tweets.size());
            try {
                QueryResult result = twitter.search(query);

                //add tweets to arrayList tweets
                tweets.addAll(result.getTweets());

                //System feedback
                System.out.println("Gathered " + tweets.size() + " tweets");

                for (Status t : tweets)
                    if (t.getId() < lastID) lastID = t.getId();

            } catch (TwitterException te) {
                System.out.println("Couldn't connect: " + te);
            }
            query.setMaxId(lastID - 1);
        }
        return tweets;
    }

    /**
     * Gets location trends.
     *
     * @param location the location
     * @return the location trends
     * @throws TwitterException the twitter exception
     */
    public List<String> getLocationTrends(String location) throws TwitterException {

        Integer idTrendLocation = getTrendLocationId(location);
        //declare trendsList to store trends
        ArrayList<String> trendsList = new ArrayList<String>();

        try {
            //check for null/empty input
            if (idTrendLocation == null) {
                System.out.println("Trend Location Not Found");
            }

            //getting trends based on the location ID
            Trends trends = twitter.getPlaceTrends(idTrendLocation);

            for (int i = 0; i < trends.getTrends().length; i++) {
                trendsList.add(trends.getTrends()[i].getName());
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

    /**
     * Gets location trend id
     *
     * @param placeName the location name
     * @return the location id
     */
    private Integer getTrendLocationId(String placeName) {
        int LocationID = 0;

        //if location is empty/null then return 1 to set location as globat
        if (placeName == "") {
            LocationID = 1;
            return LocationID;
        }
        try {
            //declare locations list to store location trends
            ResponseList<Location> locations;
            locations = twitter.getAvailableTrends();

            //iterate through locations list
            for (Location location : locations) {
                //check if location name equals to user input location
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
