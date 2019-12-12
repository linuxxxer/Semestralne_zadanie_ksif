package genetic.fitness;

import genetic.Population;
import cipher.CipherKey;

public abstract class AbstractFitnessFunction implements FitnessFunction {

    @Override
    public CipherKey getFittest(Population population, String cipherText) {
        CipherKey fittest = null;
        double bestFitness = Double.NEGATIVE_INFINITY;

        for (CipherKey key : population.getKeys()) {
            double fitness = calculateFitness(key, cipherText);
            if (fittest == null || fitness > bestFitness) {
                fittest = key;
                bestFitness = fitness;
            }
        }
        return fittest;
    }

    public abstract double calculateFitness(CipherKey key, String cipherText);
}
