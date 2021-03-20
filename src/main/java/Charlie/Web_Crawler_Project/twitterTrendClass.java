package Charlie.Web_Crawler_Project;

/**
 * The type Twitter trend class.
 */
public class twitterTrendClass {
    //declare global variables to store twitter trend position and trend name
    private Integer position;
    private String trendName;

    /**
     * Instantiates a new Twitter trend class.
     */
//declare constructor
    public twitterTrendClass(){
    }

    /**
     * Instantiates a new Twitter trend class.
     *
     * @param position  the trend position
     * @param trendName the trend name
     */
//declare constructor
    public twitterTrendClass(Integer position, String trendName) {
        this.position = position;
        this.trendName = trendName;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
//define get() set() functions for variables
    public Integer getPosition() { return position; }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Integer position) { this.position = position; }

    /**
     * Gets trend name.
     *
     * @return the trend name
     */
    public String getTrendName() { return trendName; }

    /**
     * Sets trend name.
     *
     * @param trendName the trend name
     */
    public void setTrendName(String trendName) { this.trendName = trendName; }
}
