package genetic;

import genetic.crossover.CrossoverAlgorithm;
import genetic.crossover.CrossoverAlgorithmImpl;
import genetic.fitness.FitnessFunction;
import genetic.fitness.HybridFitnessFunction;
import genetic.mutation.MutationAlgorithm;
import genetic.mutation.MutationAlgorithmImpl;
import genetic.selection.GoodWithGoodSelection;
import genetic.selection.SelectionAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cipher.CipherKey;
import cipher.Decryption;

public class GeneticAlgorithm {
    // If elitism is set then evolution keeps the best individual from the previous generation
    private boolean elitism;

    // Genetic Algorithm tries to find the best CipherKey
    // with the given keySize
    private int keySize;

    // Maximum number of iterations per key size
    private int maximumIterations;

    // Initial size of the population
    private int initialPoolSize;

    // Number of iterations the best solution didn't change after
    // which we consider the best solution so far to be the final solution
    private int maximumConvergeIterations;

    // Crossover probability
    private double crossoverProbability;

    // Probability that one individual (chromosome) mutates
    private double mutationProbability;

    // Probability that one gene mutates
    private double geneMutationProbability;

    private SelectionAlgorithm selectionAlgorithm;
    private MutationAlgorithm mutationAlgorithm;
    private CrossoverAlgorithm crossoverAlgorithm;
    private FitnessFunction fitnessFunction;

    private Random r;

    public GeneticAlgorithm(int keySize, int maximumIterations, int initialPoolSize,
                            int maximumConvergeIterations, double mutationProbability, double geneMutationProbability,
                            double crossoverProbability, boolean elitism) {
        this.keySize = keySize;
        this.maximumIterations = maximumIterations;
        this.initialPoolSize = initialPoolSize;
        this.maximumConvergeIterations = maximumConvergeIterations;
        this.mutationProbability = mutationProbability;
        this.geneMutationProbability = geneMutationProbability;
        this.crossoverProbability = crossoverProbability;
        this.elitism = elitism;

        mutationAlgorithm = new MutationAlgorithmImpl(this.geneMutationProbability);
        crossoverAlgorithm = new CrossoverAlgorithmImpl();
        //	fitnessFunction = new DictionaryFrequencyFitnessFunction();
        //	fitnessFunction = new QuadgramFitnessFunction();
        fitnessFunction = new HybridFitnessFunction();


        r = new Random();
    }

    public CipherKey attack(String cipherText) {
        cipherText = cipherText.toLowerCase();

        // Initialize population
        Population population = new Population(getInitialPopulation());

        CipherKey fittest = null;
        int convergeIterations = 0;

        // Evolve population many times
        for (int i = 0; i < maximumIterations; i++) {
            System.out.println("Current generation: " + i);
            population = evolvePopulation(population, cipherText);

            // First in the population is the fittest from the last generation
            CipherKey newFittest = population.get(0);
            if (fittest != null && fittest.equals(newFittest)) {
                convergeIterations++;
            } else {
                convergeIterations = 0;
            }
            fittest = newFittest;

            // Check convergence
            if (convergeIterations >= maximumConvergeIterations) {
                return fitnessFunction.getFittest(population, cipherText);
            }
        }

        // Return the best match from the most evolved generation
        return fitnessFunction.getFittest(population, cipherText);
    }

    private Population evolvePopulation(Population oldGeneration, String cipherText) {
        List<CipherKey> newGeneration = new ArrayList<CipherKey>();
        Set<CipherKey> optimizationSet = new HashSet<CipherKey>();

        CipherKey fittestCopy = null;
        int elitismOffset = 0;
        if (elitism) {
            // Keep the best individual
            CipherKey fittest = fitnessFunction.getFittest(oldGeneration, cipherText);
            Decryption decryption = new Decryption(fittest);
            System.out.println(decryption.decrypt(cipherText) + "   Fitness: " + fitnessFunction.calculateFitness(fittest, cipherText) + "   Key: " + fittest + "  PermutationPos: " + Arrays.toString(fittest.getPermutationPositions()));
            newGeneration.add(fittest);
            optimizationSet.add(fittest);
            fittestCopy = new CipherKey(fittest.getPermutation());
            optimizationSet.add(fittestCopy);
            newGeneration.add(fittestCopy);
            elitismOffset = 2;
        }

        // Create selection algorithm
        //	selectionAlgorithm = new TournamentSelection(3, fitnessFunction, oldGeneration, cipherText);//new GoodWithGoodSelection(fitnessFunction, oldGeneration, cipherText);
        selectionAlgorithm = new GoodWithGoodSelection(fitnessFunction, oldGeneration, cipherText);

        // Loop over the old generation size and create individuals with crossover
        int iterations = 0;
        while (newGeneration.size() < oldGeneration.size() - elitismOffset || iterations++ == 5 * oldGeneration.size()) {
            CipherKey key1 = selectionAlgorithm.select();
            CipherKey key2 = selectionAlgorithm.select();
            CipherKey newIndividual = crossoverAlgorithm.crossover(key1, key2, crossoverProbability);
            if (optimizationSet.contains(newIndividual)) {
                continue;
            }
            newGeneration.add(newIndividual);
            optimizationSet.add(newIndividual);
        }

        // Mutate population
        for (int i = elitismOffset / 2; i < newGeneration.size(); i++) {
            if (r.nextDouble() <= mutationProbability) {
                CipherKey key = newGeneration.get(i);
                key = mutationAlgorithm.mutate(key);
            }
        }

        // Create and return population
        return new Population(newGeneration);
    }

    private Collection<CipherKey> getInitialPopulation() {
        Collection<CipherKey> keys = new LinkedList<CipherKey>();
        for (int i = 0; i < initialPoolSize; i++) {
            keys.add(CipherKey.generate(keySize));
        }
        return keys;
    }
}
