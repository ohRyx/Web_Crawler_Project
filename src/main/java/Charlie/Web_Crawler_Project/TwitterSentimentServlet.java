package Charlie.Web_Crawler_Project;

import twitter4j.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "TwitterSentimentServlet", value = "/sentim")
public class TwitterSentimentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tweets tweets = new Tweets();
        tweets.tweetManager();

        List<Status> tweetsList = new ArrayList<Status>();

        try {
            tweetsList = tweets.performQuery("covid", "100");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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

        List<String> sentiList = new ArrayList<>();
        System.out.println("Sentiment Analysis Complete!\n\n");
        //Final output after analysis is complete
        System.out.println(NLPSentiment.getOverallSentiment());

        getServletContext().getRequestDispatcher("/index").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Tweets tweets = new Tweets();
        tweets.tweetManager();

        List<Status> tweetsList = new ArrayList<Status>();

        try {
            tweetsList = tweets.performQuery("covid", "100");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


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

        List<String> sentiList = new ArrayList<>();
        System.out.println("Sentiment Analysis Complete!\n\n");
        //Final output after analysis is complete
        System.out.println(NLPSentiment.getOverallSentiment());

    }
}
