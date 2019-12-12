package genetic.fitness;

import utility.StringUtils;
import cipher.CipherKey;
import cipher.Decryption;
import dictionary.QuadgramDictionary;

public class QuadgramFitnessFunction extends AbstractFitnessFunction {

    private QuadgramDictionary dictionary;

    public QuadgramFitnessFunction() {
        dictionary = QuadgramDictionary.getInstance();
    }

    @Override
    public double calculateFitness(CipherKey key, String cipherText) {
        // Decrypt cipher text using the given cipher key
        Decryption decryption = new Decryption(key);
        String plainText = decryption.decrypt(cipherText);

        // Calculate fitness
        double fitness = 0.0;

        String preptext = StringUtils.prepare(plainText.toLowerCase());
        for (int i=0;i<preptext.length()-3;i++) {
            String quadgram = preptext.substring(i, i+4);
            fitness += dictionary.getScore(quadgram);
        }

        if (preptext.startsWith(" ")) {
            //fitness -= 100000;
        }

        for (int i = 0; i < preptext.length() - 1; i++) {
            if (preptext.subSequence(i, i + 2).equals("  ")) {
                //	fitness -= 100000;
            }
        }

        return fitness;
    }


}
