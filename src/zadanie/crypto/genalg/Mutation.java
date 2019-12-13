package zadanie.crypto.genalg;

import zadanie.crypto.Implementation.TranspositionKey;
import zadanie.crypto.Key;

import java.util.Arrays;
import java.util.Random;

public class Mutation {
    private static final int max_swap = 6;

    private double mutationProb;
    private Random random;

    public void setMutationProb(double mutationProb) {
        this.mutationProb = mutationProb;
    }

    public int genenerateNumber(int range) {
        return (random.nextInt(range));
    }

    public Integer[] mutate(Integer[] key, double probability) {
        random = new Random();
        setMutationProb(probability);
        System.out.println(key.length);
        int index1 = genenerateNumber(key.length);
        int index2;
        double mutt;
        while ( ( index2 = genenerateNumber(key.length) ) == index1 ) {
            ;
        }
        if ( random.nextDouble() < this.mutationProb ) {
            key = mutateGens(key, index1, index2);
        }
        return key;
    }

    private Integer[] mutateGens(Integer[] key, int i1, int i2) {
        Integer temp;

        Integer[] perm = key;

        temp = perm[i1];
        perm[i1] = perm[i2];
        perm[i2] = temp;

        return perm;

    }
}
