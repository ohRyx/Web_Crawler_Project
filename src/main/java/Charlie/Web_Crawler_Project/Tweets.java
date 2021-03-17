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
    public void tweetManager(){
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
    public List<Status> performQuery(String keyword, int numberOfTweets) throws InterruptedException, IOException {
        Query query = new Query(keyword + " -filter:retweets -filter:links -filter:replies -filter:images");
        System.out.println("TESTESTESTES");
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

    //print tweets
}
