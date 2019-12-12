package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dictionary {
    private static Dictionary instance = null;
    private static File dictionaryRootFile;
    private static Logger logger;

    static {
        dictionaryRootFile = new File(Dictionary.class.getResource("ngrams").getFile());
        logger = Logger.getLogger("DictionaryLogger");
    }

    private static final double minProbability = 0.000000000001;
    private static final double minLogProbability = Math.log(minProbability);

    public static Dictionary getInstance() {
        if (instance == null) {
            instance = new Dictionary();
        }
        return instance;
    }

    private Map<String, Integer> wordCount;
    private Map<String, Integer> bigramCount;
    private Map<String, Integer> trigramCount;
    private Map<String, Integer> quadgramCount;
    private Map<String, Integer> fivegramCount;

    private int totalWordCount;
    private int totalBigramsCount;
    private int totalTrigramsCount;
    private int totalQuadgramsCount;
    private int totalFivegramsCount;

    private Dictionary() {
        readDictionary();
    }

    public double getLogProbability(String ngramString, int ngramCount) {
        return getProbability(ngramString, ngramCount, true);
    }

    public boolean exists(String ngramString, int ngramCount) {
        return getProbability(ngramString, ngramCount) > minProbability;
    }

    public double getProbability(String ngramString, int ngramCount) {
        return getProbability(ngramString, ngramCount, false);
    }

    public double getProbability(String ngramString, int ngramCount, boolean logProbability) {
        int freq = 0;
        int total = 0;

        if (ngramCount == 1) {
            freq = getFrequency(wordCount, ngramString);
            total = totalWordCount;
        }
        else if (ngramCount == 2) {
            freq = getFrequency(bigramCount, ngramString);
            total = totalBigramsCount;
        } else if (ngramCount == 3) {
            freq = getFrequency(trigramCount, ngramString);
            total = totalTrigramsCount;
        } else if (ngramCount == 4) {
            freq = getFrequency(quadgramCount, ngramString);
            total = totalQuadgramsCount;
        } else if (ngramCount == 5) {
            freq = getFrequency(fivegramCount, ngramString);
            total = totalFivegramsCount;
        } else {
            throw new IllegalArgumentException("ngrams of length: " + ngramCount + " are not supported!");
        }

        if (!logProbability) {
            if (freq == 0) {
                return minProbability;
            }
            return (double)(freq) / (double)(total);
        } else {
            if (freq == 0) {
                return minLogProbability;
            }
            return Math.log(freq) - Math.log(total);
        }
    }

    private int getFrequency(Map<String, Integer> countMap, String ngram) {
        Integer freq = countMap.get(ngram.toLowerCase());
        if (freq == null) {
            return 0;
        }
        return freq;
    }

    private void readDictionary() {
        logger.log(Level.INFO, "Reading dictionaries");

        //wordCount = readNGramsFromFile("w1_.txt");
        bigramCount = readNGramsFromFile("w2_.txt");
        trigramCount = readNGramsFromFile("w3_.txt");
        //	quadgramCount = readNGramsFromFile("w4_.txt");
        //	fivegramCount = readNGramsFromFile("w5_.txt");
        wordCount = createWordCount(bigramCount);

        totalWordCount = getTotalCounts(wordCount);
        totalBigramsCount = getTotalCounts(bigramCount);
        totalTrigramsCount = getTotalCounts(trigramCount);
        //	totalQuadgramsCount = getTotalCounts(quadgramCount);
        //	totalFivegramsCount = getTotalCounts(fivegramCount);
    }

    private Map<String, Integer> createWordCount(
            Map<String, Integer> ngramCount) {
        Map<String, Integer> wordCount = new HashMap<String, Integer>();

        for (String key : ngramCount.keySet()) {
            int count = ngramCount.get(key);
            String[] words = key.split(" ");
            for (String word : words) {
                if (wordCount.containsKey(word)) {
                    wordCount.put(word, wordCount.get(word) + count);
                } else {
                    wordCount.put(word, count);
                }
            }
        }

        return wordCount;
    }

    private int getTotalCounts(Map<String, Integer> countMap) {
        int total = 0;

        for (int x : countMap.values()) {
            total += x;
        }

        return total;
    }

    private Map<String, Integer> readNGramsFromFile(String filename) {
        logger.log(Level.INFO, "Reading dictionary: " + filename);
        Map<String, Integer> ngramCount = new HashMap<String, Integer>();

        File file = new File(dictionaryRootFile.getAbsolutePath() + "/" + filename);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                // Tokens in the line are separated by \t
                String columns[] = line.split("\t");
                // First number in the line is always frequency of the word
                int count = Integer.parseInt(columns[0]);

                // Other tokens represent ngram
                StringBuilder ngramBuilder = new StringBuilder();
                for (int i = 1; i < columns.length; i++) {
                    ngramBuilder.append(columns[i]);
                    if (i < columns.length - 1) {
                        // If this is not the last token, add space
                        ngramBuilder.append(" ");
                    }
                }

                // Put ngram in the map with its count
                ngramCount.put(ngramBuilder.toString().toLowerCase(), count);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ngramCount;
    }
}
