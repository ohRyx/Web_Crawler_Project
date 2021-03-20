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

/**
 * This is a redditClass reading and storing Reddit post
 *
 * @author gwwc0
 * @version 1.0
 */
public class redditClass {
    private String title;
    private String url;
    private String noOfPost;
    private Long comments;
    private Double upvotes;

    /**
     * This is a default constructor
     */
    public redditClass() {

    }

    /**
     * Constructor for redditClass
     *
     * @param title
     * @param url
     * @param comments
     * @param upvotes
     */
    public redditClass(String title, String url, Long comments, Double upvotes) {
        super();
        this.title = title;
        this.url = url;
        this.comments = comments;
        this.upvotes = upvotes;
    }

    /**
     * Get the Title of reddit post
     *
     * @return title in string
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the Title of reddit post
     *
     * @param title the tile in string
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the Url of reddit post
     *
     * @return url in string
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the Url of reddit post
     *
     * @param url the url in string
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Get the amount of comment of the reddit post
     *
     * @return comments in long
     */
    public Long getComments() {
        return comments;
    }

    /**
     * Set the amount of comment of the reddit post
     *
     * @param comments the comments in long
     * @throws IllegalArgumentException if the comments is negative
     */
    public void setComments(Long comments) {
        if (comments < 0)
            throw new IllegalArgumentException("Invalid numbers of comments");
        this.comments = comments;
    }

    /**
     * Get the number of upvotes of the reddit post
     *
     * @return upvotes in double
     */
    public Double getUpvotes() {
        return upvotes;
    }

    /**
     * Set the number of upvotes of the reddit post
     *
     * @param upvotes the upvotes in double
     * @throws IllegalArgumentException if the upvotes is negative
     */
    public void setUpvotes(Double upvotes) {
        if (upvotes < 0)
            throw new IllegalArgumentException("Invalid upvotes number");
        this.upvotes = upvotes;
    }
}
