package Charlie.Web_Crawler_Project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ScrapServlet", value = "/scrap")
public class ScrapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       // response.setContentType("text/html");

        try
        {
            Document doc = Jsoup.connect("https://www.imdb.com/chart/top").timeout(6000).get();

            Elements body = doc.select("tbody.lister-list");

            int size =  body.select("tr").size();

            request.setAttribute("size", size);
            System.out.println(size);

        }
        catch(IOException e)
        {
            e.printStackTrace();
        }


/*        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<p>" + size + "</p>");
        out.println("</body></html>");*/


/*        for(Element e: body.select("tr"))
        {
            String title = e.select("td.posterColumn img").attr("alt");
            //System.out.println(title);
            //body.select("tr").size()
        }*/

        System.out.println("Test");
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
