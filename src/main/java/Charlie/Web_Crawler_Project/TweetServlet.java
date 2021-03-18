package Charlie.Web_Crawler_Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<twitterClass> twitterList = new ArrayList<>();
        String search = request.getParameter("search");

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("kCz7E2IGV5ye71Ct79bsvq09a")
                .setOAuthConsumerSecret("jvHsqAeVIIYIdutfOzy5d8YQTtuxGZl5fdFPEc0lzDOvhmywEJ")
                .setOAuthAccessToken("370000643-X1HRE3RRuLE5WbfN9rthGlh7fr9NwlQ5laujtovE")
                .setOAuthAccessTokenSecret("KobVEymuTxqgFl6tf5dAfcWTPZCM5JKoHX2SZzeZYHF3f");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        try {
            Query query = new Query(search);
            query.setCount(20);
            QueryResult result = twitter.search(query);
            for (Status status : result.getTweets()) {
                String name = status.getUser().getScreenName();
                String tweet = status.getText();

                System.out.println(name);
                System.out.println(tweet);

                twitterList.add(new twitterClass(name, tweet, 0));
                request.setAttribute("twitterlist", twitterList);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        getServletContext().getRequestDispatcher("/twitter.jsp").forward(request, response);
    }
}
