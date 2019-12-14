package zadanie.crypto.genalg;

import java.util.Arrays;
import java.util.Random;

public class Crossing {

    private double rate = 0.85;
    private Random random;
    private int KeySize;


        public Integer[] crossover(Integer[] a, Integer[] b, double crossoverProbability) {
            Random r = new Random();
            Integer[] permutation = new Integer[a.length];
            int CROSSOVER_SEGMENT_SIZE = a.length/2;

            for (Integer i : a) {
                if (i == null) {
                    System.out.println("Fuck");
                }
            }
            for (Integer i : b) {
                if (i == null) {
                    System.out.println("Fuck");
                }
            }

            double p = r.nextDouble();
            if (p <= crossoverProbability) {
                // Acquire random segment
                int left = r.nextInt((a.length) / 2);
                //int right = r.nextInt(b.getSize());
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
            /*else if (p < 2) {
                System.out.println("Herre");
                // Mark[i] is true if value i is containde in permutation
                boolean mark[] = new boolean[permutation.length];
                boolean coin = true;
                int start = 0;
                System.out.println("Length: " + permutation.length);
                int end = permutation.length;
                int step = 1;

                if (r.nextDouble() <= 0.5) {
                    System.out.println("Fuck?");
                    end = -1;
                    start = permutation.length;
                    step = -1;
                }

                for (int i = start-1; i > end; i += step) {
                    System.out.println("HerreHerr");
                    int x = coin ? a[i]-1 : b[i]-1;
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
                System.out.println("Herre2");
                // Acquire random segment
                int left = r.nextInt(a.length);
                //int right = r.nextInt(b.getSize());
                int right = r.nextInt(a.length);

                // If right is less than left swap them
                if (right < left) {
                    int tmp = left;
                    left = right;
                    right = tmp;
                }

                for (int i = 0; i < left; i++) {
                    permutation[i] = a[i];
                }
                int k = left + 1;
                permutation[left] = a[right];
                for (int i = left; i < a.length; i++) {
                    if (i == right) continue;
                    permutation[k++] = a[i];
                }
            }*/
            else {
                for (int i = 0; i < a.length; i++) {
                    permutation[i] = a[i] - 1;
                }
            }
            System.out.println(Arrays.toString(permutation));
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
