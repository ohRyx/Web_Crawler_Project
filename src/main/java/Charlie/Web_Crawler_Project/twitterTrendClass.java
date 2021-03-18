package Charlie.Web_Crawler_Project;

public class twitterTrendClass {
    private Integer position;
    private String trendName;

    public twitterTrendClass(){

    }
    public twitterTrendClass(Integer position, String trendName) {
        this.position = position;
        this.trendName = trendName;
    }

    public Integer getPosition() { return position; }

    public void setPosition(Integer position) { this.position = position; }

    public String getTrendName() { return trendName; }

    public void setTrendName(String trendName) { this.trendName = trendName; }
}
