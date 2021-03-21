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
 * The type Reddit crawl post servlet, This servlet is use to control reddit crawl post
 *
 * @author gwwc0
 * @version 1.0
 */
@WebServlet(name = "RedditCrawlPostServlet", value = "/redditcrawl")
public class RedditCrawlPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        getServletContext().getRequestDispatcher("/reddit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        //Declare variable
        String search = request.getParameter("searchReddit");
        String num = request.getParameter("noReddit");
        List<redditClass> redditlist = new ArrayList<redditClass>();
        String error = "";

        // Exception Handling
        if (Utils.isEmpty(search, num)) {
            error = "Search is empty";
            request.setAttribute("error", error);

        } else if (num.equals("0")) {
            error = "Enter a number between 0-100";
            request.setAttribute("error", error);

        } else if (search.length() < 2) {
            error = "Enter a valid keyword";
            request.setAttribute("error", error);

        } else {
            // Initiate The Servlet
            try {
                //Pass input to crawlPost() function
                RedditCrawl.crawlPost(search, num);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            //Declare Variables 
            String title, url;
            Double upvotes;
            Long comments;
            JSONParser jsonParser = new JSONParser();
            JSONObject data = null;

            // Read data from JSON file
            try {
                data = (JSONObject) jsonParser.parse(new FileReader("Reddit_post.json"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            JSONArray data_arr = (JSONArray) data.get("Articles");
            //Pass the crawl data to declare variables
            for (Object obj : data_arr) {
                JSONObject p_obj = (JSONObject) obj;
                System.out.println(p_obj);
                title = (String) p_obj.get("Title");
                url = (String) p_obj.get("Url");
                comments = (Long) p_obj.get("Comments");
                upvotes = (Double) p_obj.get("Upvotes");
                // Add into list
                redditlist.add(new redditClass(title, url, comments, upvotes));
            }
        }
        // Pass the parameters to JSP file to display it.
        request.setAttribute("redditcrawl", redditlist);

        // Redirect user back to reddit page
        getServletContext().getRequestDispatcher("/reddit.jsp").forward(request, response);

    }
}
