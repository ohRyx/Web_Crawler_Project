package Charlie.Web_Crawler_Project;


public class Articles {
    String title;
    String url;

    public Articles() {

    }

    public Articles(String title, String url) {
        super();
        this.title = title;
        this.url = url;
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

    public void info() {
        System.out.println("Title: " + title);
        System.out.println("URL: " + url + "\n");
    }
}

