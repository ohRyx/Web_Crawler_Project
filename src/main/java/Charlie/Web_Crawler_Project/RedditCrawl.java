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

public class RedditCrawl {

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

    public static void crawlPopularPost() throws RestClientException, ParseException {
        String articlesTitle, articlesUrl, selfText;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String authToken = getAuthToken();
        headers.setBearerAuth(authToken);
        headers.put("User-Agent",
                Collections.singletonList("tomcat:com.reddit-test:v1.0 (by /u/TeamCharlie)"));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        //String url = "https://oauth.reddit.com/r/" + subReddit + "/hot/.json?limit=" + numOfpost;
        String url = "https://oauth.reddit.com/r/popular/.json?geo_filter=SG&limit=10";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();

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


            JSONObject crawl_obj = new JSONObject();
            crawl_obj.put("Title", articlesTitle);
            crawl_obj.put("Url", articlesUrl);
            crawl_obj.put("Description", selfText);
            list.add(crawl_obj);
            obj.put("Articles", list);

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

    public static void crawlPost(String search) throws RestClientException, ParseException {
        String articlesTitle, articlesUrl, selfText;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String authToken = getAuthToken();
        headers.setBearerAuth(authToken);
        headers.put("User-Agent",
                Collections.singletonList("tomcat:com.reddit-test:v1.0 (by /u/TeamCharlie)"));
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        //String url = "https://oauth.reddit.com/r/" + subReddit + "/hot/.json?limit=" + numOfpost;
        String url = "https://oauth.reddit.com/r/" + search + "/.json?limit=10";
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();

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


            JSONObject crawl_obj = new JSONObject();
            crawl_obj.put("Title", articlesTitle);
            crawl_obj.put("Url", articlesUrl);
            crawl_obj.put("Description", selfText);
            list.add(crawl_obj);
            obj.put("Articles", list);

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
