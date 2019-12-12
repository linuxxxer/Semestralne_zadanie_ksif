package utility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    static Random r = new Random();
    static char[] alphabet;
    static {
        alphabet = new char[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char letter = 'a'; letter != 'z'; letter++) {
            alphabet[Character.getNumericValue(letter) - Character.getNumericValue('a')] = letter;
        }
        alphabet[Character.getNumericValue('z') - Character.getNumericValue('a')] = 'z';
    }

    public static char nextRandomChar() {
        return alphabet[r.nextInt(alphabet.length)];
    }

    public static void main(String[] args) {

    }


    public static String prepare(String lowerCase) {
        String text = "";
        for (int i = 0; i < lowerCase.length(); i++) {
            Character c = lowerCase.charAt(i);
            if (Character.isLetter(c)) {
                text += c;
            }
        }
        return text.toLowerCase();
    }

    public static List<String> extractNGrams(String plainText, int ngramLength) {
        List<String> ngrams = new LinkedList<String>();

        Pattern pattern = Pattern.compile("[A-Za-z0-9]+");
        Matcher matcher = pattern.matcher(plainText);

        List<String> tokens = new ArrayList<String>();
        while (matcher.find()) {
            int beginIndex = matcher.start();
            int endIndex = matcher.end();
            tokens.add(plainText.substring(beginIndex, endIndex));
        }

        for (int i = 0; i < tokens.size() - ngramLength + 1; i++) {
            StringBuilder ngramString = new StringBuilder();
            for (int j = 0; j < ngramLength; j++) {
                ngramString.append(tokens.get(i + j));
                if (j < ngramLength - 1) {
                    ngramString.append(" ");
                }
            }
            ngrams.add(ngramString.toString());
        }

        return ngrams;
    }
}
