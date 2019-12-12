package test.cipher;

import cipher.CipherKey;
import cipher.Decryption;
import cipher.Encryption;
import org.junit.Assert;
import org.junit.Test;

public class DecryptionTest {

    @Test
    public void test() {
        // Input
        String cipherText = " XTN N OJTBVVACPNTI!OL IKKETOIETIONEE   PIRT JKSEEIA".toLowerCase();
        int[] permutationKey = {5, 3, 7, 1, 2, 12, 0, 4, 9, 6, 8, 11, 10};

        // Output
        String expectedPlainText = "OVO JE NEKI PLAINTEXT KOJI CE BITI ENKRIPTOVAN! TEST".toLowerCase();

        // Processing
        CipherKey key = new CipherKey(permutationKey);
        Decryption decryption = new Decryption(key);
        String actualPlainText = decryption.decrypt(cipherText);

        // Verification
        Assert.assertEquals("Plain text is not as expected!", expectedPlainText, actualPlainText);
    }

    @Test
    public void testDecryptEncrypt() {
        // Input
        String plainText = "OVO JE NEKI ENCRYPTION TEXT. VIDECEMO!!!".toLowerCase();
        CipherKey key = CipherKey.generate(10);

        // Expected output
        String expected = plainText;

        // Processing
        Decryption decryption = new Decryption(key);
        Encryption encryption = new Encryption(key);
        String actual = encryption.encrypt(decryption.decrypt(plainText));

        // Verification
        Assert.assertEquals("Plain text is not as expected", expected, actual);
    }

    @Test
    public void testDifferentOutputs() {
        // Input
        CipherKey key1 = new CipherKey(new int[]{3, 0, 5, 6, 4, 1, 2});
        CipherKey key2 = new CipherKey(new int[]{3, 0, 5, 1, 4, 6, 2});
        CipherKey goodKey = new CipherKey(new int[]{1, 5, 4, 3, 2, 0, 6});

        String cipherText = "bkiotbsg iylslr i cahy  aent";

        // Expected output
        String expected = "";

        // Processing
        Decryption decryption1 = new Decryption(key1);
        Decryption decryption2 = new Decryption(key2);
        Decryption goodDecryption = new Decryption(goodKey);

        // Output
        System.out.println(decryption1.decrypt(cipherText));
        System.out.println(decryption2.decrypt(cipherText));
        System.out.println(goodDecryption.decrypt(cipherText));
    }
}
