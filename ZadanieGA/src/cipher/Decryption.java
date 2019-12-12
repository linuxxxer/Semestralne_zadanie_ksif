package cipher;

public class Decryption {
    private CipherKey key;

    public Decryption(CipherKey key) {
        this.key = key;
    }

    public String decrypt(String cipherText) {
        if (cipherText.length() % key.getSize() != 0) {
            throw new IllegalArgumentException("Cipher text length is not divisible by key length, therefore key is invalid!");
        }

        cipherText = cipherText.toLowerCase();

        int rowCount = cipherText.length() / key.getSize();
        // Create columns
        StringBuilder[] columns = new StringBuilder[key.getSize()];
        for (int i = 0; i < cipherText.length(); i++) {
            int colIdx = key.getPermutationPositions()[i / rowCount];
            if (columns[colIdx] == null) {
                columns[colIdx] = new StringBuilder();
            }
            columns[colIdx].append(cipherText.charAt(i));
        }

        StringBuilder plainTextBuilder = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < key.getSize(); j++) {
                plainTextBuilder.append(columns[j].charAt(i));
            }
        }

        return plainTextBuilder.toString();
    }
}
