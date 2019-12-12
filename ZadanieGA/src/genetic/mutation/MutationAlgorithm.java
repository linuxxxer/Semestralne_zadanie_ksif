package genetic.mutation;

import cipher.CipherKey;

public interface MutationAlgorithm {
    public CipherKey mutate(CipherKey key);
}
