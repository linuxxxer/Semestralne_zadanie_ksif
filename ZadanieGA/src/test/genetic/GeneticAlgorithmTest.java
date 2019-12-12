package test.genetic;

import genetic.GeneticAlgorithm;
import org.junit.Assert;
import org.junit.Test;

import utility.StringUtils;
import cipher.CipherKey;
import cipher.Decryption;
import cipher.Encryption;

public class GeneticAlgorithmTest {
    private static final int maximumIterations = 100000;
    private static final int initialPoolSize = 400;
    private static final int maximumConvergeIterations = 500;
    private static final double mutationProbability = 0.9;
    private static final double geneMutationProbability = 0.3;
    private static final double crossoverProbability = 0.5;
    private static final boolean elitism = true;

    @Test
    public void test() {
        CipherKey key = new CipherKey(new int[]{1, 5, 4, 3, 2, 0, 6});
        String plainText = "This baby likes crying a lot This baby likes crying a lot";
        Encryption encryption = new Encryption(key);
        String cipherText = encryption.encrypt(plainText);

        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);

        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }

    @Test
    public void test2() {
        CipherKey key = new CipherKey(new int[]{1, 5, 4, 3, 2, 0, 6});
        String plainText = "Jimmy Wales and Larry Sanger launched Wikipedia on January 15, 2001. Sanger[10] coined its name,[11] a portmanteau of wiki (from the Hawaiian word for \"quick\")[12] and encyclopedia. Although Wikipedia's content was initially only in English, it quickly became multilingual, through the launch of versions in different languages. All versions of Wikipedia are similar, but differences exist in content and in editing practices. The English Wikipedia is now one of more than 200 Wikipedias and is the largest with over 4.6 million articles. As of February 2014, it had 18 billion page views and nearly 500 million unique visitors each month.[13] Wikipedia has more than 22 million accounts, out of which there were over 73,000 active editors globally as of May 2014.[2]";
        Encryption encryption = new Encryption(key);
        String cipherText = encryption.encrypt(plainText);
        System.out.println(cipherText);
        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);
        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }

    @Test
    public void test3() {
        CipherKey key = CipherKey.generate(7);
        System.out.println(key);
        String plainText = "Jimmy Wales and Larry Sanger launched Wikipedia on January 15, 2001. Sanger[10] coined its name,[11] a portmanteau of wiki (from the Hawaiian word for \"quick\")[12] and encyclopedia. Although Wikipedia's content was initially only in English, it quickly became multilingual, through the launch of versions in different languages. All versions of Wikipedia are similar, but differences exist in content and in editing practices. The English Wikipedia is now one of more than 200 Wikipedias and is the largest with over 4.6 million articles. As of February 2014, it had 18 billion page views and nearly 500 million unique visitors each month.[13] Wikipedia has more than 22 million accounts, out of which there were over 73,000 active editors globally as of May 2014.[2] Over time, the English Wikipedia and some other Wikipedias gradually restricted modifications. For example, in the English Wikipedia and some other language editions, only registered users may create a new article.[21] On the English Wikipedia and some others, some particularly sensitive and/or vandalism-prone pages are now \"protected\" to some degree.[22] A frequently vandalized article can be semi-protected, meaning that only certain editors are able to modify it.[23] A particularly contentious article may be locked so that only administrators are able to make changes; however, unregistered and registered editors can still propose changes on the Talk page.[24] In certain cases, all editors are allowed to submit modifications, but review is required for some editors. For example, the German Wikipedia maintains \"stable versions\" of articles,[25] which have passed certain reviews. Following protracted trials and community discussion, the English Wikipedia introduced the \"pending changes\" system in December 2012.[26] Under this system, new users' edits to certain controversial or vandalism-prone articles are \"subject to review from an established Wikipedia editor before publication\".[27]";
        Encryption encryption = new Encryption(key);
        String cipherText = encryption.encrypt(plainText);
        System.out.println(cipherText);

        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);
        System.out.println(decryptedText);
        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }

    @Test
    public void test4() {
        CipherKey key = CipherKey.generate(6);
        System.out.println(key);
        String plainText = "this is text that is going to be encrypted and noone will ever be able to decrypt it this is text that is going to be encrypted and noone will ever be able to decrypt it this is text that is going to be encrypted and noone will ever be able to decrypt it";
        Encryption encryption = new Encryption(key);
        String cipherText = encryption.encrypt(plainText);
        System.out.println(cipherText);

        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);
        System.out.println(decryptedText);
        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }

    @Test
    public void test5() {
        CipherKey key = CipherKey.generate(6);
        System.out.println(key);
        String plainText = "Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.";
        Encryption encryption = new Encryption(key);
        String cipherText = encryption.encrypt(plainText);
        System.out.println(cipherText);

        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);
        System.out.println(decryptedText);
        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }

    @Test
    public void test6() {
        CipherKey key = CipherKey.generate(6);
        System.out.println(key);
        String plainText = "One study found that the contributor base to Wikipedia \"was barely 13% women; the average age of a contributor was in the mid-20s\".[81] A 2011 study by researchers from the University of Minnesota found that females comprised 16.1% of the 38,497 editors who started editing Wikipedia during 2009.[82] In a January 2011 New York Times article, Noam Cohen observed that just 13% of Wikipedia's contributors are female according to a 2009 Wikimedia Foundation survey.[83] Sue Gardner, a former executive director of the Wikimedia Foundation, hopes to see female contributions increase to twenty-five percent by 2015.[84] Linda Basch, president of the National Council for Research on Women, noted the contrast in these Wikipedia editor statistics with the percentage of women currently completing bachelor's degrees, master's degrees and PhD programs in the United States (all at rates of 50 percent or greater).[85] In response, various universities have hosted edit-a-thons to encourage more women to participate in the Wikipedia community. In fall 2013, 15 colleges and universities, including Yale, Brown, and Pennsylvania State, offered college credit for students to \"write feminist thinking\" about technology into Wikipedia.[86] In August 2014, Wikipedia co-founder Jimmy Wales announced in a BBC interview the Wikimedia Foundation's plans for \"doubling down\" on the issue of gender bias on Wikipedia. Wales agreed that Sue Gardner's goal of 25% women enrollment by 2015 had not been met. Wales said the foundation would be open to more outreach, more software changes,[87] and more women administrators. Software changes were left open to explore ways of increasing the appeal of Wikipedia to attract women readers to register as editors, and to increase the potential of existing editors to nominate more women administrators[clarify] to enhance the 'management' presence of women at Wikipedia.[88]";
        Encryption encryption = new Encryption(key);
        String cipherText = encryption.encrypt(plainText);
        System.out.println(cipherText);

        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);
        System.out.println(decryptedText);
        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }

    @Test
    public void test7() {
        CipherKey key = CipherKey.generate(26);
        System.out.println(key);
        String plainText = "One study found that the contributor base to Wikipedia \"was barely 13% women; the average age of a contributor was in the mid-20s\".[81] A 2011 study by researchers from the University of Minnesota found that females comprised 16.1% of the 38,497 editors who started editing Wikipedia during 2009.[82] In a January 2011 New York Times article, Noam Cohen observed that just 13% of Wikipedia's contributors are female according to a 2009 Wikimedia Foundation survey.[83] Sue Gardner, a former executive director of the Wikimedia Foundation, hopes to see female contributions increase to twenty-five percent by 2015.[84] Linda Basch, president of the National Council for Research on Women, noted the contrast in these Wikipedia editor statistics with the percentage of women currently completing bachelor's degrees, master's degrees and PhD programs in the United States (all at rates of 50 percent or greater).[85] In response, various universities have hosted edit-a-thons to encourage more women to participate in the Wikipedia community. In fall 2013, 15 colleges and universities, including Yale, Brown, and Pennsylvania State, offered college credit for students to \"write feminist thinking\" about technology into Wikipedia.[86] In August 2014, Wikipedia co-founder Jimmy Wales announced in a BBC interview the Wikimedia Foundation's plans for \"doubling down\" on the issue of gender bias on Wikipedia. Wales agreed that Sue Gardner's goal of 25% women enrollment by 2015 had not been met. Wales said the foundation would be open to more outreach, more software changes,[87] and more women administrators. Software changes were left open to explore ways of increasing the appeal of Wikipedia to attract women readers to register as editors, and to increase the potential of existing editors to nominate more women administrators[clarify] to enhance the 'management' presence of women at Wikipedia.[88]";
        Encryption encryption = new Encryption(key);
        plainText = StringUtils.prepare(plainText);
        String cipherText = encryption.encrypt(plainText);
        System.out.println(cipherText);

        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);
        System.out.println(decryptedText);
        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }

    @Test
    public void test8() {
        CipherKey key = CipherKey.generate(10);
        System.out.println(key);
        String plainText = "Medicine can involve art, science, or both. It has existed for thousands of years, during most of which it was an art (an area of skill and knowledge) that frequently had connections to the religious and philosophical beliefs of each culture. For example, a medicine man would apply herbs and say prayers for healing, or an ancient philosopher and physician would apply bloodletting according to the theories of humorism. In recent centuries, since the advent of science, most medicine has become a combination of art and science (both basic and applied, under the umbrella of medical science). Thus, while the perfect stitching technique for suturing an artery is still an art that surgeons learn by practicing, the knowledge of what happens at the cellular and molecular level in the tissues being stitched comes from science. The older, prescientific forms of medicine are now known as traditional medicine and folk medicine. Although they are no longer the sole type of medicine, they are still used to complement scientific medicine and are thus called complementary and alternative medicine (CAM). For example, although acupuncture and herbal medicine are ancient arts that include unscientific components, they can still sometimes provide relief of pain, symptoms, or anxiety and are thus still valued by many patients regardless of the chemical or physical mechanisms by which they work. Thus they continue to have value in health care, within the limits of safety and efficacy. (In contrast, medicine outside the bounds of safety and efficacy is called quackery.) Contemporary medicine applies biomedical sciences, biomedical research, genetics and medical technology to diagnose, treat, and prevent injury and disease, typically through medication or surgery, but also through therapies as diverse as psychotherapy, external splints and traction, prostheses, biologics, pharmaceuticals, ionizing radiation among others.";
        plainText = StringUtils.prepare(plainText);
        Encryption encryption = new Encryption(key);
        String cipherText = encryption.encrypt(plainText);
        System.out.println(cipherText);

        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);
        System.out.println(decryptedText);
        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }

    @Test
    public void test9() {
        CipherKey key = new CipherKey(new int[]{6, 2, 15, 16, 5, 7, 8, 24, 10, 17, 21, 9, 1, 22, 18, 13, 19, 11, 3, 23, 20, 4, 12, 0, 14});
        System.out.println(key);
        String plainText = "Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.";
        plainText = StringUtils.prepare(plainText);
        Encryption encryption = new Encryption(key);
        String cipherText = encryption.encrypt(plainText);
        System.out.println(cipherText);

        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);
        System.out.println(decryptedText);
        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }

    @Test
    public void tmptest() {
        CipherKey key = new CipherKey(new int[]{2, 0, 6, 8, 9, 5, 7, 1, 4, 3});

        String plainText = "Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.";
        plainText = StringUtils.prepare(plainText);
        Encryption encryption = new Encryption(new CipherKey(new int[]{0, 6, 8, 9, 5, 7, 1, 4, 3, 2}));
        String cipherText = encryption.encrypt(plainText);

        Decryption decryption = new Decryption(key);
        System.out.println(decryption.decrypt(cipherText));
    }

    @Test
    public void test10() {
        CipherKey key = new CipherKey(new int[]{0, 6, 8, 9, 5, 7, 1, 4, 3, 2});
        System.out.println(key);
        String plainText = "Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.Please reduce your mailbox size. Delete any items you don't need from your mailbox and empty your Deleted Items folder.";
        plainText = StringUtils.prepare(plainText);
        Encryption encryption = new Encryption(key);
        String cipherText = encryption.encrypt(plainText);
        System.out.println(cipherText);


        GeneticAlgorithm ga = new GeneticAlgorithm(
                key.getSize(),
                maximumIterations,
                initialPoolSize,
                maximumConvergeIterations,
                mutationProbability,
                geneMutationProbability,
                crossoverProbability,
                elitism);

        CipherKey foundKey = ga.attack(cipherText);
        Decryption decryption = new Decryption(foundKey);
        String decryptedText = decryption.decrypt(cipherText);
        System.out.println(decryptedText);
        Assert.assertTrue("Expected: \"" + plainText + "\" but got \"" + decryptedText + "\"",
                decryptedText.toLowerCase().startsWith(plainText.toLowerCase()));
    }
}
