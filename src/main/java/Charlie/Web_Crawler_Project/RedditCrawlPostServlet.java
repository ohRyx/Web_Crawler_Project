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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("searchReddit");
        try {
            RedditCrawl.crawlPost(search);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<redditClass> tList = new ArrayList<redditClass>();
        String title, url;
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
            tList.add(new redditClass(title, url));
        }

        for (redditClass articles : tList) {
            articles.info();
        }
        System.out.println("Total numbers of posts: " + tList.size());
        request.setAttribute("result", tList);
        getServletContext().getRequestDispatcher("/reddit.jsp").forward(request, response);

    }
}