package zadanie.crypto.genalg;

import java.util.Random;

public class Crossing {

    private double rate = 0.85;
    private Random random;
    private int KeySize;


    public Integer[] crossover(Integer[] a, Integer[] b, double crossoverProbability) {
        random = new Random();
        Integer[] permutation = new Integer[a.length];
        int CROSSOVER_SEGMENT_SIZE = a.length/2;

        double p = random.nextDouble();
        if (p <= crossoverProbability) {
            // Acquire random segment
            int left = random.nextInt((a.length) / 2);
            int right = a.length - 1 - left;

            // If right is less than left swap them
            if (right < left) {
                int tmp = left;
                left = right;
                right = tmp;
            }

            // Mark[i] is true if value i is copied from the CipherKey a,
            // false otherwise
            boolean mark[] = new boolean[permutation.length];

            // Copy all from the CipherKey a that belong to the segment [left, right]
            for (int i = left; i <= right; i++) {
                permutation[i] = a[i]-1;
                mark[permutation[i]] = true;
            }

            // Go through CipherKey b and insert gene value if it is not already in permutation
            int idx = 0;
            for (int i = 0; i < b.length; i++) {
                int gene = b[i]-1;
                if (!mark[gene]) {
                    idx = fill(gene, permutation, idx, left, right);
                }
            }
        }
        else {
            for (int i = 0; i < a.length; i++) {
                permutation[i] = a[i] - 1;
            }
        }
        for(int i=0;i<permutation.length;i++){
            permutation[i]++;
        }

        return permutation;
    }

    private Integer fill(Integer gene, Integer[] permutation, Integer idx, Integer left, Integer right) {
        if (idx >= left && idx <= right) {
            idx = right + 1;
        }
        permutation[idx] = gene;
        return idx + 1;
    }
}
