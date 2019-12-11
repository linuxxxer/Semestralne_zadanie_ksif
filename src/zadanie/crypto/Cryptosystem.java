/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.crypto;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import zadanie.helpers.TextStatistics;

/**
 *
 * @author vikto
 */
public class Cryptosystem {
     public static String guess(String txt)
    {
        String ret = ""; 
     
        TextStatistics ic = new TextStatistics();
        double ic_cislo = ic.calculateIC(txt);
        
        if (ic_cislo < 0.05) {
         //   System.out.println(ic_cislo +"< 0.05");
            ret = "Polyalphabeticka substitucia";
        }
        else
        {
            TextStatistics tx = new TextStatistics();
            Collection <Double> frek = TextStatistics.readNgram(txt,1,true).values();
            
            Double s[] = new Double[frek.size()];
            frek.toArray(s);
            
          //  System.out.println(Math.abs(s[0])+ " - " + tx.ref[0] + " = " + (Math.abs(s[0] - tx.ref[0])) + " < 0.05");
            
            if( (Math.abs(s[0] - tx.ref[0])) < 0.05)
            {
                ret = "Tranzpotzicna substitucia";
            }
            else
            {
                ret = "Monoalphabeticka substitucia";
            }
        }
        
        
        return ret;
    }
    public static String guess2(String txt)
    {
        String ret = ""; 
     
        TextStatistics ic = new TextStatistics();
        double ic_cislo = ic.calculateIC(txt);
        
        System.out.println("IC textu = "+ic_cislo);
               
        if (0.03 < ic_cislo && ic_cislo < 0.05) {
            System.out.println("0.03 < "+ic_cislo +" < 0.05");
            ret = "Polyalphabeticka substitucia";
        }
        else
        {
            TextStatistics tx = new TextStatistics();
            Collection <Double> frek = TextStatistics.readNgram(txt,1,true).values();
            
            Double abc[] = new Double[frek.size()];
            frek.toArray(abc);
                                 
            int tr =0;
            int ma =0;
            for(int i=0; i < 22; i++)
            {
                if( (Math.abs(abc[i] - tx.ref[i])) < 0.01)
                    tr++;
                else ma++;
            }
            if(tr < ma)
                ret = "Monoalphabeticka substitucia";
            else ret = "Tranzpotzicna substitucia";
            
        }
        
        
        return ret;
    }
    
    public static Integer[] DeleneS(String text,int maxdlzkakluca)
    {        
        int len = text.length();
        Integer[] retVal = new Integer[maxdlzkakluca];
        int miesto = 0;
        
        for(int j = 0; j <= maxdlzkakluca; j++)
        {
            if(len%(j+2) == 0)
            {
                retVal[miesto] = j+2;
                miesto++;
            }
        }
                
        return retVal;
    } 
    
    public static int[] convertIntegers(List<Integer> integers)
{
    int[] ret = new int[integers.size()];
    Iterator<Integer> iterator = integers.iterator();
    for (int i = 0; i < ret.length; i++)
    {
        ret[i] = iterator.next().intValue();
    }
    return ret;
}
    
    public static int kolkoX(String text)
    {
        int len = text.length();
        //pomocne
        int kolkoX = 0;
        //kolko X na konci
        for(int i = 0; i<len; i++)
        {
            if(text.charAt(i) == 'x')
                kolkoX++;
           
        }
        return kolkoX;
        
    }
}
