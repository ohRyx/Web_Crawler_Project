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

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class redditClass {
    private String title;
    private String url;
    private String noOfPost;
    private Long comments;
    private Double upvotes;

    public redditClass() {

    }

    public redditClass(String title, String url, Long comments, Double upvotes) {
        super();
        this.title = title;
        this.url = url;
        this.comments = comments;
        this.upvotes = upvotes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getComments() {
        return comments;
    }

    public void setComments(Long comments) {
        if (comments < 0 )
            throw new IllegalArgumentException("Invalid numbers of comments");
        this.comments = comments;
    }

    public Double getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(Double upvotes) {
        if (upvotes < 0)
            throw new IllegalArgumentException("Invalid upvotes number");
        this.upvotes = upvotes;
    }

    public void info() {
        System.out.println("Title: " + title);
        System.out.println("URL: " + url + "\n");
    }
}
