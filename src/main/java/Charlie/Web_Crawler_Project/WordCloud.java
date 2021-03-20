package Charlie.Web_Crawler_Project;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Word cloud. This class is used to call word cloud functions
 *
 * @author Akmal
 * @version 1.0
 */
public class WordCloud {
    /**
     * Instantiates a new Word cloud.
     */
//declare constructor
    public WordCloud() {
    }

    /**
     * Create word cloud.
     *
     * @param tweets the tweets
     * @throws IOException the io exception
     */
    public void createWordCloud(String tweets) throws IOException {
        //create word cloud
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();

        //added in stop words for word cloud from stoplist.txt
        Collection<String> stopwords = new ArrayList<String>(Files.readAllLines(Paths.get("stoplist.txt")));
        frequencyAnalyzer.setStopWords(stopwords);

        //load tweets to frequencyAnalyzer to calculate word frequencies
        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(tweets);

        //set dimension and colors for word cloud
        final Dimension dimension = new Dimension(500, 500);
        final com.kennycason.kumo.WordCloud wordCloud = new com.kennycason.kumo.WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(5);
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 40));

        //build word cloud using calculated word frequencies
        wordCloud.build(wordFrequencies);

        //output word cloud as a png
        //wordCloud.writeToFile("C:\\Users\\Akmal\\Web_Crawler_Project\\src\\main\\webapp\\image\\wordcloud.png");
        wordCloud.writeToFile("C:\\Users\\Haikal Wahab\\Documents\\SIT\\Tri 2\\OOP\\WebCrawler\\src\\main\\webapp\\image\\wordcloud.png");
        System.out.println("Printed wordcloud successfully");
    }

}
