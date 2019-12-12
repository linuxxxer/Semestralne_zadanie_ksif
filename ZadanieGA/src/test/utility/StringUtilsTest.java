package test.utility;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import utility.StringUtils;

public class StringUtilsTest {

    @Test
    public void test() {
        String text = "this is test string of length 124";
        String[] expected = {
                "this is test",
                "is test string",
                "test string of",
                "string of length",
                "of length 124"
        };

        List<String> ngrams = StringUtils.extractNGrams(text, 3);
        Assert.assertEquals("Number of ngrams is not as expected", expected.length, ngrams.size());
        for (int i = 0; i < expected.length; i++) {
            String assertionMessage = "Ngrams are not as expected: "
                    + Arrays.toString(expected) + " but got: "
                    + Arrays.toString(ngrams.toArray());
            Assert.assertEquals(assertionMessage, expected[i], ngrams.get(i));
        }
    }

}
