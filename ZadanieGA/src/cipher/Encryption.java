package cipher;

import utility.StringUtils;

public class Encryption {

    private static int DEFAULT_KEY_LENGTH = 6;

    private CipherKey key;

    public Encryption() {
        key = CipherKey.generate(DEFAULT_KEY_LENGTH);
    }

    public Encryption(CipherKey key) {
        this.key = key;
    }

    public String encrypt(String inputPlainText) {
        StringBuilder plainTextBuilder = new StringBuilder(inputPlainText.toLowerCase());

        while (plainTextBuilder.length() % key.getSize() != 0) {
            plainTextBuilder.append(StringUtils.nextRandomChar());
        }

        String plainText = plainTextBuilder.toString();
        int rowCount = plainText.length() / key.getSize();

        // Create matrix
        StringBuilder stringBuilders[] = new StringBuilder[rowCount];
        int idx = 0;
        int colIdx = 0;
        int rowIdx = 0;
        while (idx < plainText.length()) {
            if (stringBuilders[rowIdx] == null) {
                stringBuilders[rowIdx] = new StringBuilder();
            }

            stringBuilders[rowIdx].append(plainText.charAt(idx));

            idx++;
            colIdx++;
            if (colIdx == key.getSize()) {
                colIdx = 0;
                rowIdx++;
            }
        }

        // Create matrix of characters
        String rows[] = new String[stringBuilders.length];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = stringBuilders[i].toString();
        }

        // Encrypt using permutation
        StringBuilder cipherTextBuilder = new StringBuilder();
        for (int i = 0; i < key.getPermutationPositions().length; i++) {
            colIdx = key.getPermutationPositions()[i];
            for (int row = 0; row < rows.length; row++) {
                cipherTextBuilder.append(rows[row].charAt(colIdx));
            }
        }
        return cipherTextBuilder.toString().toLowerCase();
    }

    public CipherKey getKey() {
        return key;
    }

    public void setKey(CipherKey key) {
        this.key = key;
    }
}
