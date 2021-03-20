package Charlie.Web_Crawler_Project;

public class twitterTrendClass {
    //declare global variables to store twitter trend position and trend name
    private Integer position;
    private String trendName;

    //declare constructor
    public twitterTrendClass(){
    }

    //declare constructor
    public twitterTrendClass(Integer position, String trendName) {
        this.position = position;
        this.trendName = trendName;
    }

    //define get() set() functions for variables
    public Integer getPosition() { return position; }

    public void setPosition(Integer position) { this.position = position; }

    public String getTrendName() { return trendName; }

    public void setTrendName(String trendName) { this.trendName = trendName; }
}
