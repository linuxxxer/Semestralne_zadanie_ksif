package test.cipher;

import cipher.CipherKey;
import cipher.Decryption;
import cipher.Encryption;
import org.junit.Assert;
import org.junit.Test;

public class EncryptionTest {

    @Test
    public void test() {
        // Input
        String plainText = "Ovo je neki plaintext koji ce biti enkriptovan! Test";
        int[] permutationKey = {5, 3, 7, 1, 2, 12, 0, 4, 9, 6, 8, 11, 10};

        // Expected output
        String expected = " XTN N OJTBVVACPNTI!OL IKKETOIETIONEE   PIRT JKSEEIA".toLowerCase();

        // Processing
        CipherKey key = new CipherKey(permutationKey);
        Encryption encryption = new Encryption(key);
        String actual = encryption.encrypt(plainText);

        // Verification
        Assert.assertEquals("Cipher text is not as expected!", expected, actual);
    }

    @Test
    public void testDecryptEncrypt() {
        // Input
        String cipherText = "DJLAHAKASWJPOASDHALKZXCNAVZXLCDJQWESDASD";
        CipherKey key = CipherKey.generate(10);

        // Expected output
        String expected = cipherText.toLowerCase();

        // Processing
        Decryption decryption = new Decryption(key);
        Encryption encryption = new Encryption(key);
        String actual = encryption.encrypt(decryption.decrypt(cipherText));

        // Verification
        Assert.assertEquals("Cipher text is not as expected", expected, actual);
    }
}
