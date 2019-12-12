package genetic.selection;

import genetic.Population;
import genetic.fitness.FitnessFunction;

import java.util.Random;

import cipher.CipherKey;

public class TournamentSelection implements SelectionAlgorithm {
    private static final int DEFAULT_TOURNAMENT_SIZE = 3;

    private int tournamentSize;
    private Random r;
    private FitnessFunction fitnessFunction;
    private Population population;
    private String cipherText;

    public TournamentSelection(FitnessFunction fitnessFunction, Population population, String cipherText) {
        this.tournamentSize = DEFAULT_TOURNAMENT_SIZE;
        this.fitnessFunction = fitnessFunction;
        this.population = population;
        this.cipherText = cipherText;
        init();
    }

    public TournamentSelection(int tournamentSize, FitnessFunction fitnessFunction, Population population, String cipherText) {
        this.tournamentSize = tournamentSize;
        this.fitnessFunction = fitnessFunction;
        this.population = population;
        this.cipherText = cipherText;
        init();
    }

    private void init() {
        r = new Random();
    }

    @Override
    public CipherKey select() {
        CipherKey best = null;
        double bestFitness = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < tournamentSize; i++) {
            int idx = r.nextInt(population.size());
            CipherKey tmp = population.get(idx);
            double tmpFitness = fitnessFunction.calculateFitness(tmp, cipherText);
            if (best == null || tmpFitness > bestFitness) {
                tmpFitness = bestFitness;
                best = tmp;
            }
        }
        return best;
    }
}
