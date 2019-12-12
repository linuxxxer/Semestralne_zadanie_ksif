package genetic;

import java.util.Collection;

import cipher.CipherKey;

public class Population {
    private Collection<CipherKey> keys;
    private CipherKey array[];

    public Population(Collection<CipherKey> keys) {
        this.keys = keys;
        this.array = new CipherKey[keys.size()];

        int idx = 0;
        for (CipherKey key : keys) {
            array[idx++] = key;
        }
    }

    public Collection<CipherKey> getKeys() {
        return keys;
    }

    public int size() {
        return keys.size();
    }

    public CipherKey get(int idx) {
        return array[idx];
    }

    public int collisionCount() {
        int cnt = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].equals(array[j])) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}
