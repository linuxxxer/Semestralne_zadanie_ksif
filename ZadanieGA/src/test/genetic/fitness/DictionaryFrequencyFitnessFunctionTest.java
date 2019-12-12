package test.genetic.fitness;

import genetic.fitness.DictionaryFrequencyFitnessFunction;
import genetic.fitness.FitnessFunction;
import org.junit.Assert;
import org.junit.Test;

import cipher.CipherKey;
import cipher.Decryption;
import cipher.Encryption;


public class DictionaryFrequencyFitnessFunctionTest {

    @Test
    public void test() {
        FitnessFunction fitnessFunction = new DictionaryFrequencyFitnessFunction();
        int permutation[] = new int[]{5, 0, 1, 4, 3, 2, 6};
        String plainText = "this is some kind of performance test and not unit test";

        CipherKey goodKey = new CipherKey(permutation);
        Encryption encryption = new Encryption(goodKey);
        String cipherText = encryption.encrypt(plainText);

        double fitness = fitnessFunction.calculateFitness(goodKey, cipherText);
        // Check for 10 random keys if fitness will be worse for them than for the real key
        for (int i = 0; i < 10; i++) {
            CipherKey randomKey = CipherKey.generate(permutation.length);
            Decryption decryption = new Decryption(randomKey);

            double randomKeyFitness = fitnessFunction.calculateFitness(randomKey, cipherText);
            Assert.assertTrue("Random key[" + i + "]: "
                            + randomKey + " gives better fitness than good key: " + goodKey
                            + " with result: \"" + decryption.decrypt(cipherText) + "\"",
                    fitness > randomKeyFitness);
        }
    }

    @Test
    public void testFitness() {
        FitnessFunction fitnessFunction = new DictionaryFrequencyFitnessFunction();
        int permutation[] = new int[]{5, 0, 1, 4, 3, 2, 6};
        String plainText = "this is some kind of performance test and not unit test";

        CipherKey goodKey = new CipherKey(permutation);
        Encryption encryption = new Encryption(goodKey);
        String cipherText = encryption.encrypt(plainText);

        double fitness = fitnessFunction.calculateFitness(goodKey, cipherText);

        CipherKey randomKey = new CipherKey(new int[]{0, 4, 3, 6, 5, 2, 1});
        double randomKeyFitness = fitnessFunction.calculateFitness(randomKey, cipherText);

        Decryption decryption = new Decryption(randomKey);

        Assert.assertTrue("Random key: " + randomKey + " gives better fitness than good key: " + goodKey
                        + " with result: \"" + decryption.decrypt(cipherText) + "\"",
                fitness > randomKeyFitness);
    }

    @Test
    public void testFitness2() {
        FitnessFunction fitnessFunction = new DictionaryFrequencyFitnessFunction();
        int permutation[] = new int[]{2, 5, 6, 4, 1, 3, 0};
        String plainText = "This cat likes milk a lot!!!";

        CipherKey goodKey = new CipherKey(permutation);
        Encryption encryption = new Encryption(goodKey);
        String cipherText = encryption.encrypt(plainText);

        double fitness = fitnessFunction.calculateFitness(goodKey, cipherText);

        CipherKey randomKey = new CipherKey(new int[]{0, 4, 3, 6, 5, 2, 1});
        double randomKeyFitness = fitnessFunction.calculateFitness(randomKey, cipherText);

        Decryption decryption = new Decryption(randomKey);

        Assert.assertTrue("Random key: " + randomKey + " gives better fitness than good key: " + goodKey
                        + " with result: \"" + decryption.decrypt(cipherText) + "\"",
                fitness > randomKeyFitness);
    }

    @Test
    public void testFitness3() {
        FitnessFunction fitnessFunction = new DictionaryFrequencyFitnessFunction();
        String plainText = "this baby likes crying a lot";

        CipherKey goodKey = new CipherKey(new int[]{1, 5, 4, 3, 2, 0, 6});
        Encryption encryption = new Encryption(goodKey);
        String cipherText = encryption.encrypt(plainText);

        double fitness = fitnessFunction.calculateFitness(goodKey, cipherText);
        System.out.println("Fitness: " + fitness);

        CipherKey badKey = new CipherKey(new int[]{2, 1, 5, 6, 0, 3, 4});
        double randomKeyFitness = fitnessFunction.calculateFitness(badKey, cipherText);
        System.out.println("Random Fitness: " + randomKeyFitness);

        Decryption decryption = new Decryption(badKey);

        Assert.assertTrue("Random key: " + badKey + " gives better fitness than good key: " + goodKey
                        + " with result: \"" + decryption.decrypt(cipherText) + "\"",
                fitness > randomKeyFitness);
    }
}
