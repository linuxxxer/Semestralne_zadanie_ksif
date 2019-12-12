package genetic.fitness;

import java.util.List;

import utility.StringUtils;
import cipher.CipherKey;
import cipher.Decryption;
import dictionary.Dictionary;

public class DictionaryFrequencyFitnessFunction extends AbstractFitnessFunction {

    private Dictionary dictionary;

    public DictionaryFrequencyFitnessFunction() {
        dictionary = Dictionary.getInstance();
    }

    @Override
    public double calculateFitness(CipherKey key, String cipherText) {
        // Decrypt cipher text using the given cipher key
        Decryption decryption = new Decryption(key);
        String plainText = decryption.decrypt(cipherText);

        // Calculate fitness
        double fitness = 0.0;

        // Extract n-grams from plain text of each length
        for (int ngramLength = 1; ngramLength <= 2; ngramLength++) {
            List<String> ngrams = StringUtils.extractNGrams(plainText, ngramLength);
            for (String ngram : ngrams) {
                // For each ngram calculate log probability for it to be in the dictionary
                // and add the value to the fitness
                double probability = dictionary.getProbability(ngram, ngramLength) * ngramLength;
                //fitness += probability;
                fitness += ngram.length() > 2 && dictionary.exists(ngram, ngramLength) ? ngramLength : 0;
            }
        }
        return fitness;
    }

}
