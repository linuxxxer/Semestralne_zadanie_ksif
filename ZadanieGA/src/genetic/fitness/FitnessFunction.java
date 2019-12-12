package genetic.fitness;

import genetic.Population;
import cipher.CipherKey;

public interface FitnessFunction {
    public double calculateFitness(CipherKey key, String cipherText);
    public CipherKey getFittest(Population population, String cipherText);
}
