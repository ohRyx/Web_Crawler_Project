package Charlie.Web_Crawler_Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
//import twitter4j.GeoLocation;       // jar found at http://twitter4j.org/en/index.html
//import twitter4j.Paging;
//import twitter4j.Query;
//import twitter4j.QueryResult;
//import twitter4j.Status;
import twitter4j.Twitter;

import twitter4j.TwitterFactory;
import twitter4j.TwitterException;

//import java.io.IOException;
//import java.io.PrintStream;
//
//import java.util.ArrayList;
//import java.util.List;
import twitter4j.conf.ConfigurationBuilder;

@WebServlet(name = "TweetServlet", value = "/tweet")
public class TweetServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("kCz7E2IGV5ye71Ct79bsvq09a")
                .setOAuthConsumerSecret("jvHsqAeVIIYIdutfOzy5d8YQTtuxGZl5fdFPEc0lzDOvhmywEJ")
                .setOAuthAccessToken("370000643-X1HRE3RRuLE5WbfN9rthGlh7fr9NwlQ5laujtovE")
                .setOAuthAccessTokenSecret("KobVEymuTxqgFl6tf5dAfcWTPZCM5JKoHX2SZzeZYHF3f");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            Query query = new Query("manutd");
            query.setCount(100);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                String user = status.getUser().getScreenName() + ": " + status.getText();
                //System.out.println("@" + user + ":" + status.getText());
                System.out.println(user);
                request.setAttribute("user", user);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        System.out.println("Tweet");
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
