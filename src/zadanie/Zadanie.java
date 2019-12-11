
package zadanie;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.Scanner;
import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import zadanie.crypto.BigramFitness;
import zadanie.crypto.Cryptosystem;
import zadanie.crypto.Key;
import zadanie.crypto.Implementation.TranspositionCipher;
import zadanie.crypto.Implementation.TranspositionKey;
import zadanie.helpers.AphabeticalSequence;
import zadanie.helpers.Language;
import zadanie.helpers.Permutations;
import zadanie.helpers.Text;
import zadanie.helpers.TextStatistics;
import zadanie.helpers.Math;
/**
 *
 * @author vikto
 */
public class Zadanie {
    
    public static void main(String[] args) {
        
/*
        JFrame frame = new JFrame("Transposition Cipher");
        
        JButton button = new JButton("Desifruj");
        button.setBounds(250,100,100,40);
        
        JLabel label = new JLabel();
        label.setText("Zadaj Zasifrovany Text: ");
        label.setBounds(10,10,150,100);  
        
        JLabel label2 = new JLabel();
        label2.setBounds(10,110,200,100);
              
        JTextField textfield = new JTextField();
        textfield.setBounds(170,50,330,30);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        frame.add(label);
        frame.add(textfield);
        frame.add(label2);
        frame.add(button);
        frame.setLayout(null);
        frame.setSize(600,600);
        frame.setVisible(true);
        */
        
        int MAXDLZKAKLUCA = 10;
        boolean skontrolovat = true; //false;
        
        Scanner ZasifrovanyText = new Scanner(System.in); 
        System.out.println("Zadaj Zasifrovany Text: ");
        String ZT = ZasifrovanyText.nextLine();
        
       
       
        //String ZT = "TANRAPSIOSITCONPIRHESIEAMHTOODEFRNCPYOTIBNHYWCIHHTPEIOSITSONEHBLDUYTNIOSLFPIAENTTXIWHHCEAROCOMMLNHYCRATACRERSORGPOUOSHFCRATACRERSASEFHIETCDAOCIRDGNATOERLGURASSYETXMX";  
       String heslo = "heslo";  // 2 1 5 4 3

        // String ZT = "EHTVONWLEFSASRIUPTILBEHSNIDHYBDDOSREUOTTHGINOHTNNUEETIIKDDGNTMOOEHGIRANIOBLCKOTNONIAFASNORSITEIPAECHDNFLANOTLPEETAYBSTRATSI";
        //String heslo = "nie";    // 3 2 1
        
        //String ZT = "EWHHNIEAFNYLELRAVSELMHEISHLAFEHNODOEKIBGHNCTEMLCIACTAIBLTWTECHPIHEEETRIAYSLNWHISIEKKCOSKHOTITNOJHWEAFSHOTAETWIGIRNCOOICDALDENOHDOKSIIEWTTEHHTSSAICFIATTOANHETEPTARLHDEIATRYLILKECHKDOIFMFETHHSWIIPHHCOHOOKSCNEISDRDBOAFTREMHENEPTARETKOSTCNLRFOOETHHSAIDPNISSALETEHSBACSAOKOTLONXDN";
        //String heslo = "peter";    // 2 4 1 5 3
       
       // String ZT = "HTHEEREAVEEBRNSEEVIALDADTTISONROPEETTPASNSCORNYIGLUNDITTHUEAEHOSRIEDSUEQLLNEOVPPERTECANSINAARTLEHNDETHOIGRHPEFISLEIQUFELTLMESRNURONTAEVLERHNDDANROOAKVHIOCUSEARTACMRSOFROTHTESERYVHARAPAPETEDOINAHELRPPCESSELECLIAEYTKINARBLELCSASMACOTDANTHACRAIERDOFHSNTEYAECRHAACTSERHRETINIEPLUBICDAOMMNIONSSEJIURODIICTDNSALEUINOGTONAHUTERIDSEIXTSENTONOSTHHETMYUOSDANTSEFSORHEACHSACRTEFSOOMEHTHEESEAVEEBRNCTONIOVSERHALCSURASEASPIEFSOLREEQUVSBAYDYEBRARDANIDRALEEYPNRSAONGDLTOSSIRALSLEXLUAIYELXPACIRTGOPHNICAVEYLBOLAONMMREDANAELDINEGEIBBRFEUATNINEGWLDYRDADINNGARTHEEHOOISNEOFTWHEUNDFERRLWAIZADOZFOCNDIALEESVADSNTEURDINNWODERNLA";
     //   String heslo = "viktor";    // 2 3 5 6 4 1
       
       // String ZT = "NJRKAOLAKNSDAIRLAOA2LPAR14I28ODDOI2RMNAARSAYIRARCAMJKAAALKARANAHJNOYZZEEIODEEVLFZIASEKZANILVVVOOJASROSOKDUMIIVVPSTKLOMOLIAKSMUAILZAADNKJNLSEK1EO8841V8R23OO8C3H5K131V7SNT8AOEGVYAMVLZNVISUAAMVJMSGKEAORMEIP4TESBE8R3A7M1ZSSAAPLAILNUYMCVAEVLKODCEEIUSADLODTVOO8K4U1R1OZOHBTDTOIOZAASCBAVHDAVLEOIHJTOILNEAESCPERZKAHTDONJAOESBLEENTAAKAEOSTTZUOEENTKNKROEIVTOOHRHJSONBIEAYCRCVHOKPNTZAKCRIHOAHVLYELCCVCSHKUOYTCVETLRIOAEMSEPNBOPUAATVOTLSMMKIACAALHAAVHAALACKVORCKO8H8441211USADLONTVYAVCKEELUAZKRDKEMUUHJCAINOLNBKETDIEI";
//        String heslo = "jankokral";    // 2 8 1 4 6 9 3 5 7
        
        ZT = Text.convertToTSA(ZT, false);
        System.out.println("ZT = " +ZT);
        //System.out.println();
        //JAZYK
       // System.out.println(Language.guessLanguage(ZT));
       //SIFRA
       // System.out.println(Cryptosystem.guess(ZT));
        
        String OT = "";
        TranspositionCipher tc = new TranspositionCipher();  
        
        
        
        if(skontrolovat == true)
        {
            int len = heslo.length();
            Integer[] tkp = new Integer[len];
            tkp = AphabeticalSequence.Alpha(heslo);

            TranspositionKey tkeySkontrol = new TranspositionKey(tkp);
            System.out.println("OT = "+tc.decrypt(ZT,tkeySkontrol));


            System.out.print("Permutacia = ");
            for(int p = 0; p < tkp.length; p++)
            {   
                System.out.print(tkp[p] + " " );
            }
            System.out.println();
        }
        
        //frekvencia ZT
        HashMap<String,Double> frek = new HashMap<String,Double>();       
        frek = TextStatistics.readNgram(ZT, 2, true);
        
        double[][] BigramFrekInput = new double[26][26];
        BigramFrekInput = TextStatistics.convertMap(frek);    
       
        double bigram = BigramFitness.BigramFit(BigramFrekInput);
        System.out.println("input Bigram = "+bigram);
        System.out.println();
        System.out.println();
                                                                                      // DESIFROVANIE
       // int kolkoX = Cryptosystem.kolkoX(ZT);
       // System.out.println("X = "+kolkoX);
        
        Integer[] deleneS = new Integer[MAXDLZKAKLUCA];  //
        deleneS = Cryptosystem.DeleneS(ZT,MAXDLZKAKLUCA);
        
       double BestBigram = bigram;
       int miesto = 0;
       int kolkataPerm = 0;
       String SaveOT = "";
       int SaveDlzkaHesla = 0;
       int[] SavePerm = new int[1];
       
       
       
       boolean podmienka = true;
      
       while(podmienka)
        {
            
            int[] number = Permutations.intPole(deleneS[miesto]);        
            List<int[]> permutacie = Permutations.allPerm(number);
            
            for(int f = 0; f < (Math.factorial(deleneS[miesto])); f++)
            {
                Integer[] TransKlucPerm = new Integer[deleneS[miesto]];
                int[] FromListToInt = permutacie.get(f);
                for(int ctr = 0; ctr < FromListToInt.length; ctr++) {
                    TransKlucPerm[ctr] = Integer.valueOf(FromListToInt[ctr]); 
                }
                
                TranspositionKey tkey = new TranspositionKey(TransKlucPerm);
                
                OT = tc.decrypt(ZT,tkey);
               // System.out.println("OT "+(f+1)+" = "+OT);
                
                frek = TextStatistics.readNgram(OT, 2, true);
                BigramFrekInput = TextStatistics.convertMap(frek); 
                bigram = BigramFitness.BigramFit(BigramFrekInput);
               // System.out.println("input Bigram = "+bigram);
               
               if(bigram < BestBigram)
               {   
                   System.out.println("OT "+(f+1)+" = "+OT);
                   
                   BestBigram = bigram;
                   System.out.println("BestBigram = "+BestBigram);
                   
                   SaveDlzkaHesla = deleneS[miesto];
                   System.out.println("Dlzka Hesla = "+deleneS[miesto]);
                   
                   
                   kolkataPerm = f+1;
                   System.out.println("Permutacia = "+(kolkataPerm));
                   
                   
                   SaveOT = OT;
                   SavePerm = FromListToInt;
                   
                   System.out.print("Permutacia = ");
                   for(int p = 0; p < SavePerm.length; p++)
                   {   
                       System.out.print(SavePerm[p] + " " );
                   }
                   System.out.println();
                   
                   System.out.println();
                   System.out.println();
               }
            }
            
            miesto++;
            
            
            if(deleneS[miesto] == null)
            {   podmienka = false;
                break;
            }
            if(deleneS[miesto] > MAXDLZKAKLUCA)
            {
                podmienka = false;
                break;
            }
        }
        System.out.println();
        System.out.println("BestBigram = "+BestBigram);
        System.out.println("Dlzka Hesla = "+SaveDlzkaHesla);
        System.out.println("Kolkata Permutacia = "+(kolkataPerm));
        
        
        System.out.print("Permutacia = ");
        for(int p = 0; p < SavePerm.length; p++)
        {   
            System.out.print(SavePerm[p] + " " );
        }
        System.out.println();
        System.out.println("OT "+kolkataPerm+" = "+SaveOT);
        
       
        // SLOVNIK ENG
        /*
        File file = new File( "eng_slovnik.txt" );
        String slovnik = Text.readText(file);
        String slovniklines[] = slovnik.split("\\r?\\n", -1);
        */
        
       
    }
}
