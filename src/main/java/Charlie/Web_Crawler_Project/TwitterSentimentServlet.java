package Charlie.Web_Crawler_Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "TwitterSentimentServlet", value = "/TwitterSentimentServlet")
public class TwitterSentimentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*        //Initializing the sentiment analyzer
        NLPSentiment.init();
        //Console feedback
        System.out.println("Performing Sentiment Analysis..\n");
        int analysisCounter = 0;
        int analysisTotal = 0;
        //Starting analysis
        for (Status t : tweets.getTweetsList) {
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
        System.out.println(NLPSentiment.getOverallSentiment());*/

    }
}
