package Charlie.Web_Crawler_Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "WordCloudServlet", value = "/wordcloud")
public class WordCloudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //init wordcloud object
        WordCloud wordCloud = new WordCloud();

        //create word cloud using tweets.txt
        wordCloud.createWordCloud("tweets.txt");

        getServletContext().getRequestDispatcher("/wordcloud.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //init wordcloud object
        WordCloud wordCloud = new WordCloud();

        //create word cloud using tweets.txt
        wordCloud.createWordCloud("tweets.txt");

        getServletContext().getRequestDispatcher("/wordcloud.jsp").forward(request, response);

    }
}
