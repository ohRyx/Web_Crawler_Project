package Charlie.Web_Crawler_Project;

import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.*;

public class scrap {


    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect("https://www.imdb.com/chart/top").timeout(6000).get();

        Elements body = doc.select("tbody.lister-list");

        for (Element e : body.select("tr")) {
            String title = e.select("td.posterColumn img").attr("alt");
            //System.out.println(title);
            //body.select("tr").size()
        }
        

    }


}
