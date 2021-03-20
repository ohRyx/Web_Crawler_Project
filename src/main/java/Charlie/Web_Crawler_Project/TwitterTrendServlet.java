package Charlie.Web_Crawler_Project;

import twitter4j.Status;
import twitter4j.TwitterException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TwitterTrendServlet", value = "/trend")
public class TwitterTrendServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //initialise Tweets object and call tweetManager() to establish twitter connection
        Tweets tweets = new Tweets();
        tweets.tweetManager();

        //create WriteTweets object
        Writer writeTweets = new Writer();

        //create trends.txt to store location trends
        writeTweets.createTxt("trends.txt");

        //create list to store trends
        List<String> trends = new ArrayList<>();
        List<twitterTrendClass> trendsList = new ArrayList<>();

        //retrieve trends, note blank parameter will take global trends
        try {
            trends = tweets.getLocationTrends("");
        } catch (TwitterException e) {
            e.printStackTrace();
        }

        //set for loop to obtain only top 20 trends
        for (Integer trendposition = 1; trendposition < 21; trendposition++) {
            //print trends position and name
            System.out.println(trendposition + ": " + trends.get(trendposition - 1));

            //add trends to list
            trendsList.add(new twitterTrendClass(trendposition, trends.get(trendposition - 1)));
            request.setAttribute("trendsList", trendsList);
        }

        //store trends in txt file
        writeTweets.storeStringTxt(trends, "trends.txt");

        //redirect to index.jsp page
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
