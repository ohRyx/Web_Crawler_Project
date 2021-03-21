package Charlie.Web_Crawler_Project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * The type Reddit crawl, This is where various function related to Reddit will be use.
 *
 * @author gwwc0
 * @version 1.0
 */
public class RedditCrawl {
    //  Get Authentication Code, in order to crawl Reddit
    private static String getAuthToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("k_kIAAt6inRT-w", "gwg2euP3di0aSMnqNmCERwBG9k8oRQ");
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.put("User-Agent",
                Collections.singletonList("tomcat:com.reddit-test:v1.0 (by /u/TeamCharlie)"));
        String body = "grant_type=password&username=TeamCharlie16&password=Qwerty@123";
        HttpEntity<String> request = new HttpEntity<>(body, headers);
        String authUrl = "https://www.reddit.com/api/v1/access_token";
        ResponseEntity<String> response = restTemplate.postForEntity(authUrl, request, String.class);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        try {
            map.putAll(mapper.readValue(response.getBody(), new TypeReference<Map<String, Object>>() {
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // System.out.println(response.getBody());
        return String.valueOf(map.get("access_token"));
    }

    /**
     * Crawl popular post.
     *
     * @throws RestClientException the rest client exception
     * @throws ParseException      the parse exception
     */
    public static void crawlPopularPost() throws RestClientException, ParseException {
        //Declare Variables
        String articlesTitle, articlesUrl, selfText;
        Long comments;
        Double upVotes;

        //Initialization
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String authToken = getAuthToken();
        headers.setBearerAuth(authToken);
        headers.put("User-Agent",
                Collections.singletonList("tomcat:com.reddit-test:v1.0 (by /u/TeamCharlie)"));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String url = "https://oauth.reddit.com/r/popular/.json?geo_filter=SG&limit=20";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Create object and list
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();

        // Crawl the data from Reddit
        JSONParser parse = new JSONParser();
        JSONObject data_obj = (JSONObject) parse.parse(response.getBody());
        JSONObject data = (JSONObject) data_obj.get("data");
        JSONArray children = (JSONArray) data.get("children");
        for (Object o : children) {
            JSONObject o_post = (JSONObject) o;
            JSONObject o_data = (JSONObject) o_post.get("data");
            articlesTitle = (String) o_data.get("title");
            articlesUrl = (String) o_data.get("url");
            selfText = (String) o_data.get("selftext");
            comments = (Long) o_data.get("num_comments");
            upVotes = (Double) o_data.get("upvote_ratio");

            //Create a new JSON Object & store the crawl data
            JSONObject crawl_obj = new JSONObject();
            crawl_obj.put("Title", articlesTitle);
            crawl_obj.put("Url", articlesUrl);
            crawl_obj.put("Description", selfText);
            crawl_obj.put("Comments", comments);
            crawl_obj.put("Upvotes", upVotes * 100);
            list.add(crawl_obj);
            obj.put("Articles", list);

            // Write to JSON File
            try {
                FileWriter file = new FileWriter("Reddit_post.json");
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Crawl post.
     *
     * @param search the search
     * @param num    the num
     * @throws RestClientException the rest client exception
     * @throws ParseException      the parse exception
     */
    public static void crawlPost(String search, String num) throws RestClientException, ParseException {
        //Declare Variables
        String articlesTitle, articlesUrl, selfText;
        Long comments;
        Double upVotes;

        //Initialization
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String authToken = getAuthToken();
        headers.setBearerAuth(authToken);
        headers.put("User-Agent",
                Collections.singletonList("tomcat:com.reddit-test:v1.0 (by /u/TeamCharlie)"));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String url = "https://oauth.reddit.com/r/" + search + "/.json?limit=" + num;
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // Create object and list
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();

        //Create a new JSON Object & store the crawl data
        JSONParser parse = new JSONParser();
        JSONObject data_obj = (JSONObject) parse.parse(response.getBody());
        JSONObject data = (JSONObject) data_obj.get("data");
        JSONArray children = (JSONArray) data.get("children");
        for (Object o : children) {
            JSONObject o_post = (JSONObject) o;
            JSONObject o_data = (JSONObject) o_post.get("data");
            articlesTitle = (String) o_data.get("title");
            articlesUrl = (String) o_data.get("url");
            selfText = (String) o_data.get("selftext");
            comments = (Long) o_data.get("num_comments");
            upVotes = (Double) o_data.get("upvote_ratio");

            //Create a new JSON Object & store the crawl data
            JSONObject crawl_obj = new JSONObject();
            crawl_obj.put("Title", articlesTitle);
            crawl_obj.put("Url", articlesUrl);
            crawl_obj.put("Description", selfText);
            crawl_obj.put("Comments", comments);
            crawl_obj.put("Upvotes", upVotes * 100);
            list.add(crawl_obj);
            obj.put("Articles", list);

            // Write to JSON File
            try {
                FileWriter file = new FileWriter("Reddit_post.json");
                file.write(obj.toString());
                file.flush();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
