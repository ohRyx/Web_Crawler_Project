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

public class WordCloud {
    public WordCloud() {

    }

    public void test() {
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
        System.out.println("testingfml");
        try {
            final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load("tweets.txt");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void createWordCloud(String tweets) throws IOException {
        //create word cloud
        final FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();

        //added in stopwords for word cloud from stoplist.txt
        Collection<String> stopwords = new ArrayList<String>(Files.readAllLines(Paths.get("stoplist.txt"))); //stoplist"+ File.separator+"
        frequencyAnalyzer.setStopWords(stopwords); //must put Collection<String>

        final List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(tweets);
        final Dimension dimension = new Dimension(500, 500);
        final com.kennycason.kumo.WordCloud wordCloud = new com.kennycason.kumo.WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(5);
        wordCloud.setBackground(new CircleBackground(300));
        wordCloud.setColorPalette(new ColorPalette(new Color(0x4055F1), new Color(0x408DF1), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
        wordCloud.setFontScalar(new SqrtFontScalar(10, 40));
        wordCloud.build(wordFrequencies);
        wordCloud.writeToFile("C:\\Users\\Haikal Wahab\\Documents\\SIT\\Tri 2\\OOP\\WebCrawler\\src\\main\\webapp\\image\\wordcloud.png");
        System.out.println("Printed wordcloud successfully");
    }

}
