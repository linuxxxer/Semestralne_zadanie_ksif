package genetic.crossover;

import java.util.Random;

import cipher.CipherKey;

public class CrossoverAlgorithmImpl implements CrossoverAlgorithm {
    private Random r;

    private static final int CROSSOVER_SEGMENT_SIZE = 4;

    public CrossoverAlgorithmImpl() {
        r = new Random();
    }

    @Override
    public CipherKey crossover(CipherKey a, CipherKey b,
                               double crossoverProbability) {
        int[] permutation = new int[a.getSize()];

        double p = r.nextDouble();
        if (p <= crossoverProbability) {
            // Acquire random segment
            int left = r.nextInt((a.getSize() - 1) / 2);
            //int right = r.nextInt(b.getSize());
            int right = a.getSize() - 1 - left;

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
                permutation[i] = a.get(i);
                mark[permutation[i]] = true;
            }

            // Go through CipherKey b and insert gene value if it is not already in permutation
            int idx = 0;
            for (int i = 0; i < b.getSize(); i++) {
                int gene = b.get(i);
                if (!mark[gene]) {
                    idx = fill(gene, permutation, idx, left, right);
                }
            }
        } else if (p < 2) {
            // Mark[i] is true if value i is containde in permutation
            boolean mark[] = new boolean[permutation.length];
            boolean coin = true;
            int start = 0;
            int end = permutation.length;
            int step = 1;

            if (r.nextDouble() <= 0.5) {
                end = -1;
                start = permutation.length - 1;
                step = -1;
            }

            for (int i = start; i != end; i += step) {
                int x = coin ? a.get(i) : b.get(i);
                if (!mark[x]) {
                    mark[x] = true;
                    permutation[i] = x;
                } else {
                    while (mark[x]) {
                        x = r.nextInt(mark.length);
                    }
                    mark[x] = true;
                    permutation[i] = x;
                }

                if (i % CROSSOVER_SEGMENT_SIZE == CROSSOVER_SEGMENT_SIZE - 1) {
                    coin = !coin;
                }
            }
        } else {
            // Acquire random segment
            int left = r.nextInt(a.getSize());
            //int right = r.nextInt(b.getSize());
            int right = r.nextInt(a.getSize());

            // If right is less than left swap them
            if (right < left) {
                int tmp = left;
                left = right;
                right = tmp;
            }

            for (int i = 0; i < left; i++) {
                permutation[i] = a.get(i);
            }

            int k = left + 1;
            permutation[left] = a.get(right);
            for (int i = left; i < a.getSize(); i++) {
                if (i == right) continue;
                permutation[k++] = a.get(i);
            }
        }

        // Create and return CipherKey for the crossovered permutation
        return new CipherKey(permutation);
    }

    private int fill(int gene, int[] permutation, int idx, int left, int right) {
        if (idx >= left && idx <= right) {
            idx = right + 1;
        }
        permutation[idx] = gene;
        return idx + 1;
    }
}
