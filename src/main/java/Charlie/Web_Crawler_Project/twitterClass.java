package Charlie.Web_Crawler_Project;


/**
 * The type Twitter class object. Is used to store tweet details
 *
 * @author Haikal
 * @version 1.0
 */
public class twitterClass {
    //declare global variables to store username, tweet text and retweet count of tweet
    private String name;
    private String tweet;
    private Integer rtcount;

    /**
     * Instantiates a new Twitter class.
     */
    //class constructor
    public twitterClass() {
    }

    /**
     * Instantiates a new Twitter class.
     *
     * @param name    the name
     * @param tweet   the tweet
     * @param rtcount the rtcount
     */
    public twitterClass(String name, String tweet, int rtcount) {
        this.name = name;
        this.tweet = tweet;
        this.rtcount = rtcount;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets tweet.
     *
     * @return the tweet
     */
    public String getTweet() {
        return tweet;
    }

    /**
     * Sets tweet.
     *
     * @param tweet the tweet
     */
    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    /**
     * Gets rtcount.
     *
     * @return the rtcount
     */
    public Integer getRtcount() {
        return rtcount;
    }

    /**
     * Sets rtcount.
     *
     * @param rtcount the rtcount
     */
    public void setRtcount(Integer rtcount) {
        if (rtcount < 0)
            throw new IllegalArgumentException("Invalid rtcount number");
        this.rtcount = rtcount;
    }
}

