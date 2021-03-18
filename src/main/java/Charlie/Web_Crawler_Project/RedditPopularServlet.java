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

@WebServlet(name = "RedditPopularServlet")
public class RedditPopularServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            RedditCrawl.crawlPopularPost();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<redditClass> redditlist = new ArrayList<redditClass>();
        String title, url;
        Long comments;
        Double upvotes;

        JSONParser jsonParser = new JSONParser();
        JSONObject data = null;
        try {
            data = (JSONObject) jsonParser.parse(new FileReader("Reddit_post.json"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONArray data_arr = (JSONArray) data.get("Articles");
        for (Object obj : data_arr) {
            JSONObject p_obj = (JSONObject) obj;
            title = (String) p_obj.get("Title");
            url = (String) p_obj.get("Url");
            comments = (Long) p_obj.get("Comments");
            upvotes = (Double) p_obj.get("Upvotes");
            redditlist.add(new redditClass(title, url, comments, upvotes));
        }

/*        for (redditClass articles : redditlist) {
            articles.info();
        }*/

        System.out.println("Reddit Popular Crawl Works!");
        request.setAttribute("redditList", redditlist);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
