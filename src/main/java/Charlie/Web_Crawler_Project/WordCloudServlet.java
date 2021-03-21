package Charlie.Web_Crawler_Project;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.*;
import java.io.IOException;

/**
 * The type Word cloud servlet, This servlet is used to control word cloud.
 *
 * @author Akmal
 * @version 1.0
 */
@WebServlet(name = "WordCloudServlet", value = "/wordcloud")
public class WordCloudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        //init word cloud object
        WordCloud wordCloud = new WordCloud();

        //call createWodCloud() to create word cloud using tweets.txt
        wordCloud.createWordCloud("tweets.txt");

        //redirect to twitter.jsp page
        getServletContext().getRequestDispatcher("/twitter.jsp").forward(request, response);

    }
}
