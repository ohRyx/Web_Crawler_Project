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

@WebServlet(name = "RedditCrawlPostServlet", value = "/redditcrawl")
public class RedditCrawlPostServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/reddit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("searchReddit");
        String num = request.getParameter("noReddit");
        List<redditClass> redditlist = new ArrayList<redditClass>();
        String error = "";


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
            try {
                RedditCrawl.crawlPost(search, num);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String title, url;
            Double upvotes;
            Long comments;
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
                System.out.println(p_obj);
                title = (String) p_obj.get("Title");
                url = (String) p_obj.get("Url");
                comments = (Long) p_obj.get("Comments");
                upvotes = (Double) p_obj.get("Upvotes");
                redditlist.add(new redditClass(title, url, comments, upvotes));
            }
        }
        request.setAttribute("redditcrawl", redditlist);
        getServletContext().getRequestDispatcher("/reddit.jsp").forward(request, response);

    }
}
