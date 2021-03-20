package Charlie.Web_Crawler_Project;

import java.util.Properties;

import java.text.DecimalFormat;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

public class NLPSentiment {
    //Only 1 instance
    static StanfordCoreNLP pipeline;
    //Trackers
    static int VNegCount, NegCount, NeuCount, PosCount, vPosCount;

    public static void init() {
        //Starting new pipeline for sentiment using NLP Lib
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
        VNegCount = 0;
        NegCount = 0;
        NeuCount = 0;
        PosCount = 0;
        vPosCount = 0;
    }

    public static int getSentimentValue(String data) {

        //Sentiment Value based on the library
        int SentimentValue = 0;

        //Checking if data provided is empty
        if (data != null && data.length() > 0) {
            int longest = 0;

            //Sentiment processing
            Annotation annotation = pipeline.process(data);
            for (CoreMap sentence : annotation
                    .get(CoreAnnotations.SentencesAnnotation.class)) {
                Tree tree = sentence
                        .get(SentimentAnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                String partText = sentence.toString();
                if (partText.length() > longest) {
                    SentimentValue = sentiment;
                    longest = partText.length();
                }
            }
        }
        switch (SentimentValue) {
            case 0:
                VNegCount++;
            case 1:
                NegCount++;
            case 2:
                NeuCount++;
            case 3:
                PosCount++;
            case 4:
                vPosCount++;
        }
        return SentimentValue;
    }

    //Getting the overall sentiment after they have been analyzed
    public static String getOverallSentiment() {
        //Used to format the values for output
        DecimalFormat df = new DecimalFormat("0.0");

        //Converting into percentages
        int totalCount = VNegCount + NegCount + NeuCount + PosCount + vPosCount;
        double VNegPercent = ((double) VNegCount / totalCount) * 100;
        double NegPercent = ((double) NegCount / totalCount) * 100;
        double NeuPercent = ((double) NeuCount / totalCount) * 100;
        double PosPercent = ((double) PosCount / totalCount) * 100;
        double vPosPercent = ((double) vPosCount / totalCount) * 100;

        //Formatting text for output
        String output = "Overall Sentiment breakdown is: " +
                "\nVery Negative: " + df.format(VNegPercent) + "%" +
                "\nNegative: " + df.format(NegPercent) + "%" +
                "\nNeutral: " + df.format(NeuPercent) + "%" +
                "\nPositive: " + df.format(PosPercent) + "%" +
                "\nVery Positive: " + df.format(vPosPercent) + "%";

        return output;
    }
}