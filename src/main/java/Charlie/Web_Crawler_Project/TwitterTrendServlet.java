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

        for (Integer trendposition = 1; trendposition < 21; trendposition++) {
            //print trends postion and name
            System.out.println(trendposition + ": " + trends.get(trendposition - 1));

            //add trends to list
            trendsList.add(new twitterTrendClass(trendposition, trends.get(trendposition - 1)));
            request.setAttribute("trendsList", trendsList);
        }

        //store trends in txt file
        writeTweets.storeStringTxt(trends, "trends.txt");
        System.out.println("Twiiter Trends Crawl Works!");
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
