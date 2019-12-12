package zadanie.crypto.genalg;

import zadanie.crypto.BigramFitness;
import zadanie.crypto.Implementation.TranspositionCipher;
import zadanie.crypto.Implementation.TranspositionKey;
import zadanie.helpers.Text;
import zadanie.helpers.TextStatistics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GeneticalAlgorithm {

    private String cipherText = "";
    private String openText = "";
//    default keySize = 5
    private int keySize = 5;
    private int iterationNo = 500;

    private int DEF_POP = 12;

    ArrayList<Integer[]> population;

    HashMap<Integer[], Double> fitness;

    public GeneticalAlgorithm(String CT, int KS) {
        this.cipherText = Text.convertToTSA(CT, false);
        this.keySize = KS;
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

    private Integer[] genIndividual() {
        Random random = new Random();
        int recent;
        int prev = 0;
        Integer[] ret = new Integer[keySize];
        for (int i = 0; i < keySize; i++) {
            if (i == 0) {
                recent = random.nextInt(keySize);
                prev = recent;
            } else {
                while ( (recent = random.nextInt(keySize)) == prev ) {
                    ;
                }
                prev = recent;
            }
            ret[i] = recent;
        }
        return ret;
    }

    public void geneticAlgorithmRun() {

        HashMap<String, Double> frek;

        double[][] bigramIn;

        double bigramFitness;

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
//            TODO call the crossing algorithm and the mutation for the best 6
//            TODO do we need to generate 6 more individuals?
//            TODO              if so, modify the genIndividual() method!!!
        }


    }

    private void mutate() {
//      TODO make the mutation algorithm
    }

    private void cross() {
//        TODO make the crossing algorithm
    }

}
