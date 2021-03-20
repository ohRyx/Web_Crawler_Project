package Charlie.Web_Crawler_Project;

import twitter4j.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TwitterCrawlServlet", value = "/tweet")
public class TwitterCrawlServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tweets tweets = new Tweets();
        tweets.tweetManager();

        //set query keyword and tweet count
        String keyword = request.getParameter("searchTwitter");
        String num = request.getParameter("noTweets");
        String sen = "";
        String error = "";

        //create tweets list
        List<Status> tweetsList = new ArrayList<Status>();
        List<twitterClass> twitterList = new ArrayList<>();


        if (Utils.isEmpty(keyword, num)) {
            error = "Search is empty";
            request.setAttribute("error", error);

        } else if (num.equals("0")) {
            error = "Enter a number between 0-1000";
            request.setAttribute("error", error);

        } else if (keyword.length() < 2) {
            error = "Enter a valid keyword";
            request.setAttribute("error", error);
        } else {
            //get tweets
            try {
                tweetsList = tweets.performQuery(keyword, num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //add tweet data to twitterlist to display on page
            for (Status status : tweetsList) {
                String name = "@" + status.getUser().getName();
                String tweet = status.getText();
                Integer retcount = status.getRetweetCount();

                //System.out.println(name);
                System.out.println(tweet);

                twitterList.add(new twitterClass(name, tweet, retcount));
            }
            //create WriteTweets object
            Writer writeTweets = new Writer();

            //create a text file
            writeTweets.createTxt("tweets.txt");

            //get tweets and write to txt file
            writeTweets.writeTxt(tweetsList, "tweets.txt");

            //Initializing the sentiment analyzer
            NLPSentiment.init();
            //Console feedback
            System.out.println("Performing Sentiment Analysis..\n");
            int analysisCounter = 0;
            int analysisTotal = 0;
            //Starting analysis
            for (Status t : tweetsList) {
                NLPSentiment.getSentimentValue(t.getText());
                //Console feedback
                analysisCounter++;
                analysisTotal++;
                if (analysisCounter == 20) {
                    System.out.println("Tweets analyzed: " + analysisTotal);
                    analysisCounter = 0;
                }
            }

            sen = NLPSentiment.getOverallSentiment();
            System.out.println("Sentiment Analysis Complete!\n\n");
            //Final output after analysis is complete
            System.out.println(NLPSentiment.getOverallSentiment());
            System.out.println(sen);


        }
        request.setAttribute("twitterlist", twitterList);
        request.setAttribute("sen", sen);
        getServletContext().getRequestDispatcher("/wordcloud").include(request, response);
    }
}
