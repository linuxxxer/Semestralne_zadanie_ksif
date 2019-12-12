package genetic.selection;

import java.util.Arrays;

import genetic.Population;
import genetic.fitness.FitnessFunction;
import cipher.CipherKey;

public class GoodWithGoodSelection implements SelectionAlgorithm {

    private static class SortWrapper implements Comparable<SortWrapper> {
        public double fitness;
        public CipherKey key;

        public SortWrapper(double fitness, CipherKey key) {
            this.fitness = fitness;
            this.key = key;
        }

        @Override
        public int compareTo(SortWrapper o) {
            return new Double(o.fitness).compareTo(fitness);
        }
    }

    private int buffer;
    private int idx;
    private SortWrapper array[];
    private FitnessFunction fitnessFunction;
    private String cipherText;

    public GoodWithGoodSelection(FitnessFunction fitnessFunction, Population population, String cipherText) {
        this.fitnessFunction = fitnessFunction;
        this.cipherText = cipherText;
        setPopulation(population);
    }

    public void setPopulation(Population population) {
        idx = 0;
        buffer = 1;
        int i = 0;
        array = new SortWrapper[population.size()];
        for (CipherKey key : population.getKeys()) {
            double fitness = fitnessFunction.calculateFitness(key, cipherText);
            array[i++] = new SortWrapper(fitness, key);
        }

        Arrays.sort(array);
    }

    @Override
    public CipherKey select() {
        CipherKey key = array[idx % array.length].key;

        buffer++;
        if (buffer == 2) {
            buffer = 0;
            idx++;
        }

        return key;
    }

}
