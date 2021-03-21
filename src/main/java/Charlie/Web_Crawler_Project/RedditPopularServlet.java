package Charlie.Web_Crawler_Project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Reddit popular servlet, This servlet is is to control the reddit popular post
 *
 * @author Haikal
 * @version 1.0
 */
@WebServlet(name = "RedditPopularServlet")
public class RedditPopularServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Initiate the servlet and call crawlPopularpost() function
        try {
            RedditCrawl.crawlPopularPost();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        // Declare Variables
        List<redditClass> redditlist = new ArrayList<redditClass>();
        String title, url;
        Long comments;
        Double upvotes;
        JSONParser jsonParser = new JSONParser();
        JSONObject data = null;

        // Read data from JSON file
        try {
            data = (JSONObject) jsonParser.parse(new FileReader("Reddit_post.json"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray data_arr = (JSONArray) data.get("Articles");
        // Pass the crawl data to declare variables
        for (Object obj : data_arr) {
            JSONObject p_obj = (JSONObject) obj;
            title = (String) p_obj.get("Title");
            url = (String) p_obj.get("Url");
            comments = (Long) p_obj.get("Comments");
            upvotes = (Double) p_obj.get("Upvotes");
            //Add into list
            redditlist.add(new redditClass(title, url, comments, upvotes));
        }

        // System print to check if it work
        System.out.println("Reddit Popular Crawl Works!");

        // Pass the parameters to JSP file to disdplay it
        request.setAttribute("redditList", redditlist);

        // Direct to TwitterTrendServlet
        getServletContext().getRequestDispatcher("/trend").include(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }
}
