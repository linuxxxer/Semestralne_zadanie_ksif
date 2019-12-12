package genetic.fitness;

import java.util.HashMap;
import java.util.Map;

import cipher.CipherKey;

public class HybridFitnessFunction extends AbstractFitnessFunction {

    private DictionaryFrequencyFitnessFunction dictionaryFitnessFunction;
    private QuadgramFitnessFunction quadgramFitnessFunction;
    private TrigramFitnessFunction trigramFitnessFunction;

    public HybridFitnessFunction() {
        dictionaryFitnessFunction = new DictionaryFrequencyFitnessFunction();
        quadgramFitnessFunction = new QuadgramFitnessFunction();
        trigramFitnessFunction = new TrigramFitnessFunction();
    }

    private Map<CipherKeyTextWrapper, Double> cache = new HashMap<CipherKeyTextWrapper, Double>();

    @Override
    public double calculateFitness(CipherKey key, String cipherText) {
        CipherKeyTextWrapper wrapper = new CipherKeyTextWrapper(key, cipherText);
        if (!cache.containsKey(wrapper)) {
            double fitness = trigramFitnessFunction.calculateFitness(key, cipherText) + quadgramFitnessFunction.calculateFitness(key, cipherText);
            cache.put(wrapper, fitness);
        }
        //return dictionaryFitnessFunction.calculateFitness(key, cipherText) + quadgramFitnessFunction.calculateFitness(key, cipherText);
        return cache.get(wrapper);
    }

    private static class CipherKeyTextWrapper {
        public CipherKey key;
        public String cipherText;

        public CipherKeyTextWrapper(CipherKey key, String cipherText) {
            super();
            this.key = key;
            this.cipherText = cipherText;
        }
        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((cipherText == null) ? 0 : cipherText.hashCode());
            result = prime * result + ((key == null) ? 0 : key.hashCode());
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
            CipherKeyTextWrapper other = (CipherKeyTextWrapper) obj;
            if (cipherText == null) {
                if (other.cipherText != null)
                    return false;
            } else if (!cipherText.equals(other.cipherText))
                return false;
            if (key == null) {
                if (other.key != null)
                    return false;
            } else if (!key.equals(other.key))
                return false;
            return true;
        }


    }
}
