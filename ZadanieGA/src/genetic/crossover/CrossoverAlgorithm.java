package genetic.crossover;

import cipher.CipherKey;

public interface CrossoverAlgorithm {
    public CipherKey crossover(CipherKey a, CipherKey b, double crossoverProbability);
}
