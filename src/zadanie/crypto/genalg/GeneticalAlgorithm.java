package zadanie.crypto.genalg;

import zadanie.crypto.BigramFitness;
import zadanie.crypto.Implementation.TranspositionCipher;
import zadanie.crypto.Implementation.TranspositionKey;
import zadanie.helpers.Text;
import zadanie.helpers.TextStatistics;

import java.util.*;

public class GeneticalAlgorithm {

    private String cipherText = "";
    private String openText = "";
//    default keySize = 5
    private int keySize = 5;
    private int iterationNo = 2000;

    private double mutationProbability = 0.01;

    private int DEF_POP = 12;

    ArrayList<Integer[]> population = new ArrayList<>();

    HashMap<Integer[], Double> fitness;

    public GeneticalAlgorithm(String CT, int KS) {
        this.cipherText = Text.convertToTSA(CT, false);
        this.keySize = KS;

//        for (int i = 0; i < 100; i++) {
//            System.out.println(geneticAlgorithmRun());
//        }
    }

    public GeneticalAlgorithm(String cipherText, int keySize, int iterationNo) {
        this.cipherText = Text.convertToTSA(cipherText, false);
        this.keySize = keySize;
        this.iterationNo = iterationNo;
    }

    private void genPop() {
        for (int i = 0; i < DEF_POP; i++) {
            this.population.add(genIndividual());
        }
    }

    private boolean contains(Integer[] x, int a){
        for(int i=0;i<x.length;i++){
            if(x[i] == null)
                x[i] = -1;
            if(x[i] == a)
                return true;
        }
        return false;
    }

    private Integer[] genIndividual() {
        Random random = new Random();
        Integer[] ret = new Integer[keySize];
        for(int j=0;j<keySize;j++) {
            int rand;
            do {
                rand = random.nextInt(keySize) + 1;
            } while (contains(ret, rand));
            ret[j] = rand;
        }
        return ret;
    }

    public String geneticAlgorithmRun() {
        fitness = new HashMap<>();
        HashMap<String, Double> frek;

        ArrayList<Integer[]> bests;

        double[][] bigramIn;

        double bigramFitness;

        Mutation mutate = new Mutation();
        Crossing cross = new Crossing();

        TranspositionCipher transCipher = new TranspositionCipher();
        TranspositionKey transKey;

        genPop();

        for (int i = 0; i < iterationNo; i++) {
            for (Integer[] pop : population) {
                transKey = new TranspositionKey(pop);
                openText = transCipher.decrypt(cipherText, transKey);
                frek = TextStatistics.readNgram(openText, 2, true);
                bigramIn = TextStatistics.convertMap(frek);
                bigramFitness = BigramFitness.BigramFit(bigramIn);
//                some bullshit... TODO TODO
                if (fitness.containsKey(pop)) {
                    if (!(fitness.get(pop) < bigramFitness)) {
                        continue;
                    }
                }
                fitness.put(pop, bigramFitness);
            }

            bests = selectSixBest();

            for(int j=1;j<bests.size();j+=2) {
                Integer[] a = bests.get(j - 1);
                Integer[] b = bests.get(j);
                bests.set(j - 1, cross.crossover(a, b, 0.85));
                bests.set(j , cross.crossover(a, b, 0.85));
            }

//                mutation
            for (Integer[] individual : bests) {
                individual = mutate.mutate(individual, mutationProbability);
            }

            fitness.clear();
            population.clear();
            for (Integer[] individual : bests) {
                population.add(individual);
            }

            for (int k = population.size(); k < DEF_POP; k++) {
                population.add(genIndividual());
            }


        }

//        get the fitness for the last population
        for (Integer[] pop : population) {
            transKey = new TranspositionKey(pop);
            openText = transCipher.decrypt(cipherText, transKey);
            frek = TextStatistics.readNgram(openText, 2, true);
            bigramIn = TextStatistics.convertMap(frek);
            bigramFitness = BigramFitness.BigramFit(bigramIn);
//                some bullshit... TODO TODO
            if (fitness.containsKey(pop)) {
                if (!(fitness.get(pop) < bigramFitness)) {
                    continue;
                }
            }
            fitness.put(pop, bigramFitness);
        }
        return transCipher.decrypt(cipherText, new TranspositionKey(getBest()));
    }

//    method for selecting the best 6 individuals by their fitness value
//    tato cast boli... ale funguje
    private ArrayList<Integer[]> selectSixBest() {
        ArrayList<Integer[]> bests = new ArrayList<Integer[]>(6);
        List<Double> values = new LinkedList(fitness.values());
        values.sort(Comparator.naturalOrder());
        for (int i = 0; i < 6; i++) {   //TODO Preco 6 ??????????????
                                                   //  Lebo vyberame 6 najlepsich (je to polovica populacie)
            for (Map.Entry<Integer[], Double> entry : fitness.entrySet()) {
                if (entry.getValue().equals(values.get(i))) {
                    bests.add(entry.getKey());
                    break;
                }
            }
        }
        return bests;
    }

    private Integer[] getBest() {
        List<Double> values = new LinkedList<>(fitness.values());
        values.sort(Comparator.naturalOrder());
        Integer[] best = new Integer[keySize];
        for (Map.Entry<Integer[], Double> entry : fitness.entrySet()) {
            if (entry.getValue().equals(values.get(0))) {
                best = entry.getKey();
            }
        }
        return best;
    }

}
