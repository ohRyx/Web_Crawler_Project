package Charlie.Web_Crawler_Project;

public class twitterClass {
    //declare global variables to store username, tweet text and retweet count of tweet
    private String name;
    private String tweet;
    private Integer rtcount;

    //class constructor
    public twitterClass() {
    }

    //class constructor
    public twitterClass(String name, String tweet, int rtcount) {
        this.name = name;
        this.tweet = tweet;
        this.rtcount = rtcount;
    }

    //define get() set() functions for variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTweet() {
        return tweet;
    }

    public void setTweet(String tweet) {
        this.tweet = tweet;
    }

    public Integer getRtcount() { return rtcount; }

    public void setRtcount(Integer rtcount) { this.rtcount = rtcount; }
}

