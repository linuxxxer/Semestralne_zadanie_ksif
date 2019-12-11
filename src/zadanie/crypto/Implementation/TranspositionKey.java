
package zadanie.crypto.Implementation;


/**
 *
 * @author vikto
 */
import zadanie.crypto.Key;
import zadanie.helpers.Permutations;

public class TranspositionKey implements Key{

    int blockSize;
    Integer[] encPerm;
    Integer[] decPerm;

    public TranspositionKey(Integer[] encPerm) {
        this.blockSize = encPerm.length;
        this.encPerm = encPerm;
        this.decPerm = Permutations.inverse(encPerm);
    }

    public int getBlockSize() {
        return blockSize;
    }

    public Integer[] getDecPerm() {
        return decPerm;
    }

    public Integer[] getEncPerm() {
        return encPerm;
    }
}
