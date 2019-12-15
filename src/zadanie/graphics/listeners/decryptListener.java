package zadanie.graphics.listeners;

import zadanie.crypto.BigramFitness;
import zadanie.crypto.Cryptosystem;
import zadanie.crypto.Implementation.TranspositionCipher;
import zadanie.crypto.Implementation.TranspositionKey;
import zadanie.helpers.Math;
import zadanie.helpers.Permutations;
import zadanie.helpers.Text;
import zadanie.helpers.TextStatistics;

import java.util.HashMap;
import java.util.List;

public class decryptListener {

    private int MAXDLZKAKLUCA = 10;
    private int[] SavePerm = new int[1];

    public String decrypt(String ZT) {
        if (ZT == null) {
            return null;
        }



        String OT = "";
        TranspositionCipher tc = new TranspositionCipher();

        ZT = Text.convertToTSA(ZT, false);

        if (!checkZatvorenyText(ZT) ) {
            return "Error: nie je to transpozicna sifra";
        }

        HashMap<String, Double> frek = new HashMap<String, Double>();
        frek = TextStatistics.readNgram(ZT, 2, true);

        double[][] BigramFrekInput = new double[26][26];
        BigramFrekInput = TextStatistics.convertMap(frek);

        double bigram = BigramFitness.BigramFit(BigramFrekInput);
        // DESIFROVANIE
        // int kolkoX = Cryptosystem.kolkoX(ZT);

        Integer[] deleneS = new Integer[MAXDLZKAKLUCA];  //
        deleneS = Cryptosystem.DeleneS(ZT, MAXDLZKAKLUCA);

        double BestBigram = bigram;
        int miesto = 0;
        int kolkataPerm = 0;
        String SaveOT = "";
        int SaveDlzkaHesla = 0;
//        int[] SavePerm = new int[1];


        boolean podmienka = true;

        while (podmienka) {

            int[] number = Permutations.intPole(deleneS[miesto]);
            List<int[]> permutacie = Permutations.allPerm(number);

            for (int f = 0; f < (Math.factorial(deleneS[miesto])); f++) {
                Integer[] TransKlucPerm = new Integer[deleneS[miesto]];
                int[] FromListToInt = permutacie.get(f);
                for (int ctr = 0; ctr < FromListToInt.length; ctr++) {
                    TransKlucPerm[ctr] = Integer.valueOf(FromListToInt[ctr]);
                }

                TranspositionKey tkey = new TranspositionKey(TransKlucPerm);

                OT = tc.decrypt(ZT, tkey);

                frek = TextStatistics.readNgram(OT, 2, true);
                BigramFrekInput = TextStatistics.convertMap(frek);
                bigram = BigramFitness.BigramFit(BigramFrekInput);

                if (bigram < BestBigram) {

                    BestBigram = bigram;

                    SaveDlzkaHesla = deleneS[miesto];


                    kolkataPerm = f + 1;


                    SaveOT = OT;
                    SavePerm = FromListToInt;

                }
            }

            miesto++;


            if (deleneS[miesto] == null) {
                podmienka = false;
                break;
            }
            if (deleneS[miesto] > MAXDLZKAKLUCA) {
                podmienka = false;
                break;
            }
        }
        return SaveOT;
    }

    public String getPerm() {
        StringBuilder perm = new StringBuilder();
        for (int i : SavePerm) {
            perm.append(i);
        }
        return perm.toString();
    }

    private boolean checkZatvorenyText(String ZT) {
        if (Math.isPrime(ZT.length())) {
            return false;
        } else {
            return true;
        }
    }

}
