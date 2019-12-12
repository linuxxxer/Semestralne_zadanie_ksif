package cipher;

import java.util.Arrays;
import java.util.Random;

public class CipherKey {
    static Random r;

    int[] permutation;
    int[] permutationPositions;

    public CipherKey(int[] permutation) {
        setPermutation(permutation);
    }

    public void setPermutation(int[] permutation) {
        this.permutation = permutation.clone();
        this.permutationPositions = new int[permutation.length];

        for (int i = 0; i < permutation.length; i++) {
            permutationPositions[ permutation[i] ] = i;
        }

        int count[] = new int[permutation.length];
        for (int i = 0; i < permutation.length; i++) {
            count[permutation[i]]++;
        }

        for (int i = 0; i < permutation.length; i++) {
            if (count[i] != 1) {
                throw new IllegalArgumentException("Permutation: " + toString() + " is not valid permutation!");
            }
        }
    }

    public int get(int idx) {
        return permutation[idx];
    }

    public void set(int idx, int value) {
        permutation[idx] = value;
        permutationPositions[value] = idx;
    }

    public int[] getPermutation() {
        return permutation;
    }

    public int[] getPermutationPositions() {
        return permutationPositions;
    }

    public static CipherKey generate(int length) {
        if (r == null) {
            r = new Random();
        }

        int[] permutation = new int[length];
        for (int i = 0; i < length; i++) {
            permutation[i] = i;
        }

        // Swap random indices
        for (int i = 0; i < length; i++) {
            int idx = r.nextInt(length);
            int p = permutation[i];
            permutation[i] = permutation[idx];
            permutation[idx] = p;
        }

        return new CipherKey(permutation);
    }

    public int getSize() {
        return permutation.length;
    }

    @Override
    public String toString() {
        return "Length: " + permutation.length + "; " + Arrays.toString(permutation);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + Arrays.hashCode(permutation);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CipherKey other = (CipherKey) obj;
        if (!Arrays.equals(permutation, other.permutation))
            return false;
        return true;
    }


}
