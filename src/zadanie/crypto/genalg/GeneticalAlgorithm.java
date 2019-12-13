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
    private int iterationNo = 500;

    private double mutationProbability = 0.1;

    private int DEF_POP = 12;

    ArrayList<Integer[]> population = new ArrayList<>();

    HashMap<Integer[], Double> fitness;

    public GeneticalAlgorithm(String CT, int KS) {
        this.cipherText = Text.convertToTSA(CT, false);
        this.keySize = KS;
        geneticAlgorithmRun();
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
        int recent;
        int prev = 0;
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

    public void geneticAlgorithmRun() {
        fitness = new HashMap<>();
        HashMap<String, Double> frek;

        ArrayList<Integer[]> bests = new ArrayList<>();

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

//            TODO call the crossing algorithm and the mutation for the best 6
//            TODO do we need to generate 6 more individuals?
//            TODO              if so, modify the genIndividual() method!!!

//                TODO crossing ???
            //System.out.println();
            for(int j=1;j<bests.size();j+=2) {
                bests.set(j - 1,cross.crossover(bests.get(j-1), bests.get(j), 0.85));
            }

//                mutation
            for (Integer[] individual : bests) {
                individual = mutate.mutate(individual, mutationProbability);
            }

            population.clear();
            for (Integer[] individual : bests) {
                population.add(individual);
            }



        }


    }

//    method for selecting the best 6 individuals by their fitness value
//    tato cast boli... ale funguje
    private ArrayList<Integer[]> selectSixBest() {
        ArrayList<Integer[]> bests = new ArrayList<Integer[]>(6);
        List<Integer[]> keys = new LinkedList(fitness.keySet());
        List<Double> values = new LinkedList(fitness.values());
        values.sort(Comparator.naturalOrder());
        for (int i = 0; i < 6; i++) {   //TODO Preco 6 ??????????????
            for (Map.Entry<Integer[], Double> entry : fitness.entrySet()) {
                if (entry.getValue().equals(values.get(i))) {
                    bests.add(entry.getKey());
                    break;
                }
            }
        }
        return bests;
    }

}
