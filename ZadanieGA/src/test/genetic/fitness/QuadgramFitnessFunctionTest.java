package test.genetic.fitness;

import genetic.fitness.QuadgramFitnessFunction;
import org.junit.Test;

import cipher.CipherKey;
import cipher.Encryption;

public class QuadgramFitnessFunctionTest {

    @Test
    public void test() {
        CipherKey goodKey = new CipherKey(new int[]{1, 5, 4, 3, 2, 0, 6});
        String plainText = "This baby likes crying a lot";
        Encryption encryption = new Encryption(goodKey);
        String cipherText = encryption.encrypt(plainText);

        CipherKey key = new CipherKey(new int[]{2, 0, 6, 1, 4, 3, 5});
        CipherKey key2 = new CipherKey(new int[]{1, 5, 6, 2, 0, 4, 3});

        QuadgramFitnessFunction func = new QuadgramFitnessFunction();
        System.out.println(func.calculateFitness(goodKey, cipherText));
        System.out.println(func.calculateFitness(key, cipherText));
        System.out.println(func.calculateFitness(key2, cipherText));

//		hs batiylikeb  ryinsc  lotgatis b hb likayscryie ga lon  ctiota ctionaeevmpnb
//		this baby likes crying a lot this baby likes crying a lot action actionebevmp

    }
}
