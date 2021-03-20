package Charlie.Web_Crawler_Project;

import twitter4j.Status;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Writer {
    //create txt file
    public void createTxt(String fileName) throws IOException {
        File myObj = new File(fileName);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    //write to file function for storing tweets
    public void writeTxt(List<Status> tweets, String fileToWrite) throws IOException {
        int write_count = 1;
        //open write
        FileWriter myWriter = new FileWriter(fileToWrite);

        //write to file
        for (Status t : tweets)
            myWriter.write(write_count + t.getText());
            write_count++;
        myWriter.close();
    }

    public void writeStatus(List<Status> tweets, String fileToWrite) throws IOException {
        int write_count = 1;
        //open write
        FileWriter myWriter = new FileWriter(fileToWrite);

        //write to file
        for (Status t : tweets)
            myWriter.write(String.valueOf(t));
        write_count++;
        myWriter.close();
    }

    //write to file function for normal string data
    public void storeStringTxt(List<String> data, String fileToWrite) throws IOException {
        int write_count = 1;
        //open write
        FileWriter myWriter = new FileWriter(fileToWrite);

        //write to file
        for (String t : data) {
            myWriter.write(write_count + " " + t + "\n");
            write_count++;
        }
        myWriter.close();
    }
}
