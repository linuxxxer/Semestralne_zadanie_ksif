package genetic.selection;

import genetic.Population;
import cipher.CipherKey;

public interface SelectionAlgorithm {
    public CipherKey select();
}
