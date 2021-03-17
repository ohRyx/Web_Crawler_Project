package Charlie.Web_Crawler_Project;

import twitter4j.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TwitterCrawlServlet", value = "/tweet2")
public class TwitterCrawlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tweets tweets = new Tweets();
        tweets.tweetManager();

        //set query keyword and tweet count
        String keyword = request.getParameter("search");
        int numberOfTweets = 10; //add_the_paramater_here

        //create tweets list
        List<Status> tweetsList = new ArrayList<Status>();
        List<twitterClass> twitterList = new ArrayList<>();

        //get tweets
        try {
            tweetsList = tweets.performQuery(keyword, numberOfTweets);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //add tweet data to twitterlist to display on page
        for (Status status : tweetsList) {
            String name = status.getUser().getName();
            String tweet = status.getText();

            //System.out.println(name);
            System.out.println(tweet);

            twitterList.add(new twitterClass(name, tweet));
            request.setAttribute("twitterlist", twitterList);
        }
        //create WriteTweets object
        Writer writeTweets = new Writer();

        //create a text file
        writeTweets.createTxt();

        //get tweets and write to txt file
        writeTweets.writeTxt(tweetsList);

        getServletContext().getRequestDispatcher("/twitter.jsp").forward(request, response);
    }
}
