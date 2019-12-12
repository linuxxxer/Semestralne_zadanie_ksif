package genetic.mutation;

import java.util.Arrays;
import java.util.Random;

import cipher.CipherKey;

public class MutationAlgorithmImpl implements MutationAlgorithm {
    private static final int MAX_NUMBER_OF_SWAPS = 6;

    private double mutationProbability;
    private Random r;

    public MutationAlgorithmImpl(double mutationProbability) {
        r = new Random();
        this.mutationProbability = mutationProbability;
    }

    @Override
    public CipherKey mutate(CipherKey key) {

        if (r.nextDouble() <= 0.5) {

            int left = r.nextInt(key.getSize());
            int right = r.nextInt(key.getSize());
            if (left > right) {
                int tmp = left;
                left = right;
                right = tmp;
            }

            int pos = r.nextInt(key.getSize());

            int[] permutation = new int[key.getSize()];
            Arrays.fill(permutation, -1);
            for (int i = left; i <= right; i++) {
                int idx = (i + pos - left) % key.getSize();
                permutation[idx] = key.get(i);
            }

            int m = -1;
            for (int i = 0; i < key.getSize(); i++) {
                if (permutation[i] == -1) {
                    m++;
                    if (m == left) {
                        m = (right + 1) % key.getSize();
                    }
                    permutation[i] = key.get(m);
                }
            }

            key = new CipherKey(permutation);
        } else {
            int maxNumberOfSwaps = r.nextInt(MAX_NUMBER_OF_SWAPS) + 1;
            for (int i = 0, numberOfSwaps = 0; i < key.getSize() && numberOfSwaps < maxNumberOfSwaps; i++) {
                if (r.nextDouble() <= mutationProbability) {
                    int idx = r.nextInt(key.getSize());

                    int p1 = key.get(i);
                    int p2 = key.get(idx);

                    key.set(i, p2);
                    key.set(idx, p1);
                }
            }
        }

        return key;
    }

}
