package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TrigramDictionary {
    private static TrigramDictionary instance = null;
    private static File dictionaryRootFile;
    static {
        dictionaryRootFile = new File(Dictionary.class.getResource("ngrams").getFile());
    }

    public static TrigramDictionary getInstance() {
        if (instance == null) {
            instance = new TrigramDictionary();
        }
        return instance;
    }

    private double minValue = Double.POSITIVE_INFINITY;
    private double total;
    private Map<String, Double> frequencies;
    private Map<String, Double> scores;

    private TrigramDictionary() {
        readDictionary();
    }

    public double getFrequency(String quadgram) {
        Double freq = frequencies.get(quadgram);
        if (freq == null) {
            return 0;
        }
        return freq;
    }

    private void readDictionary() {
        frequencies = readNGramsFromFile("eng_trigrams.txt");

        // Calculate scores
        scores = new HashMap<String, Double>();
        for (Map.Entry<String, Double> entry : frequencies.entrySet())
        {
            double val =  Math.log(entry.getValue() / total);
            scores.put(entry.getKey(), val);
            if (minValue > val) minValue = val;
        }

    }

    private Map<String, Double> readNGramsFromFile(String filename) {
        Map<String, Double> map = new HashMap<String, Double>();
        total = 0;

        File file = new File(dictionaryRootFile.getAbsolutePath() + "/" + filename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // Tokens in the line are separated by " "
                String columns[] = line.split(" ");
                // First number in the line is always frequency of the word
                Double count = Double.parseDouble(columns[1]);
                total += count;
                // Put ngram in the map with its count
                map.put(columns[0].toLowerCase(), count);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public double getScore(String quadgram) {
        if (scores.containsKey(quadgram)) {
            return scores.get(quadgram);
        }
        return minValue;
    }
}
