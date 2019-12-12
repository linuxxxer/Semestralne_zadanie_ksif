package zadanie.graphics.listeners;

import zadanie.crypto.BigramFitness;
import zadanie.crypto.Cryptosystem;
import zadanie.crypto.Implementation.TranspositionCipher;
import zadanie.crypto.Implementation.TranspositionKey;
import zadanie.helpers.Math;
import zadanie.helpers.Permutations;
import zadanie.helpers.Text;
import zadanie.helpers.TextStatistics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
//        System.out.println("input Bigram = " + bigram);
//        System.out.println();
//        System.out.println();
        // DESIFROVANIE
        // int kolkoX = Cryptosystem.kolkoX(ZT);
        // System.out.println("X = "+kolkoX);

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
                // System.out.println("OT "+(f+1)+" = "+OT);

                frek = TextStatistics.readNgram(OT, 2, true);
                BigramFrekInput = TextStatistics.convertMap(frek);
                bigram = BigramFitness.BigramFit(BigramFrekInput);
                // System.out.println("input Bigram = "+bigram);

                if (bigram < BestBigram) {
//                    System.out.println("OT " + (f + 1) + " = " + OT);

                    BestBigram = bigram;
//                    System.out.println("BestBigram = " + BestBigram);

                    SaveDlzkaHesla = deleneS[miesto];
//                    System.out.println("Dlzka Hesla = " + deleneS[miesto]);


                    kolkataPerm = f + 1;
//                    System.out.println("Permutacia = " + (kolkataPerm));


                    SaveOT = OT;
                    SavePerm = FromListToInt;

//                    System.out.print("Permutacia = ");
//                    for (int p = 0; p < SavePerm.length; p++) {
//                        System.out.print(SavePerm[p] + " ");
//                    }
//                    System.out.println();

//                    System.out.println();
//                    System.out.println();
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
//        System.out.println();
//        System.out.println("BestBigram = " + BestBigram);
//        System.out.println("Dlzka Hesla = " + SaveDlzkaHesla);
//        System.out.println("Kolkata Permutacia = " + (kolkataPerm));


//        System.out.print("Permutacia = ");
//        for (int p = 0; p < SavePerm.length; p++) {
//            System.out.print(SavePerm[p] + " ");
//        }
//        System.out.println();
//        System.out.println("OT " + kolkataPerm + " = " + SaveOT);
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
