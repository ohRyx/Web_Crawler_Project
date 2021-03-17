package Charlie.Web_Crawler_Project;

import twitter4j.Status;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Writer {
    public void createTxt() throws IOException {
        File myObj = new File("tweets.txt");
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    public void writeTxt(List<Status> tweets) throws IOException {
        int write_count = 1;
        //open write
        FileWriter myWriter = new FileWriter("tweets.txt");

        //write to file
        for (Status t : tweets)
            myWriter.write(write_count + t.getText());
        myWriter.close();
    }
}
