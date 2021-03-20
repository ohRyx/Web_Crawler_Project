package Charlie.Web_Crawler_Project;

import twitter4j.Status;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Writer class. Used to call functions to write/output
 *
 * @author Akmal, Princeton
 * @version 1.0
 */
public class Writer {
    /**
     * Instantiates a new Writer.
     */
    public Writer() {
    }

    /**
     * Create txt file.
     *
     * @param fileName the file name
     * @throws IOException the io exception
     */
    public void createTxt(String fileName) throws IOException {
        //initialise file object with fileName
        File myObj = new File(fileName);

        //check if file already exists
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
    }

    /**
     * Writes tweet text to a txt file.
     *
     * @param tweets      the tweets
     * @param fileToWrite the file to write
     * @throws IOException the io exception
     */
    public void writeTxt(List<Status> tweets, String fileToWrite) throws IOException {
        int write_count = 1;
        //open writer
        FileWriter myWriter = new FileWriter(fileToWrite);

        //write texts from tweets to file
        for (Status t : tweets)
            myWriter.write(write_count + t.getText());
            write_count++;

        //close writer
        myWriter.close();
    }

    /**
     * Write string tweet status to a txt file.
     *
     * @param tweets      the tweets
     * @param fileToWrite the file to write
     * @throws IOException the io exception
     */
    public void writeStatus(List<Status> tweets, String fileToWrite) throws IOException {
        //open writer
        FileWriter myWriter = new FileWriter(fileToWrite);

        //write to file
        for (Status t : tweets)
            myWriter.write(String.valueOf(t));

        //close writer
        myWriter.close();
    }

    /**
     * Write string to a txt file.
     *
     * @param data        the data
     * @param fileToWrite the file to write
     * @throws IOException the io exception
     */
    public void storeStringTxt(List<String> data, String fileToWrite) throws IOException {
        int write_count = 1;
        //open writer
        FileWriter myWriter = new FileWriter(fileToWrite);

        //write to file
        for (String t : data) {
            myWriter.write(write_count + " " + t + "\n");
            write_count++;
        }
        myWriter.close();
    }
}
