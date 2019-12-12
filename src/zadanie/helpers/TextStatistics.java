/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.helpers;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vikto
 */
public class TextStatistics {
     public double[] ref = {0.08167, 0.01492, 0.02782, 0.04253, 0.12702, 0.02228, 0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025, 0.02406, 0.06749, 0.07507, 0.01929, 9.5E-4, 0.05987, 0.063269, 0.0905599, 0.02758, 0.00978, 0.0236, 0.0015, 0.01974, 7.4E-4};
    
    public static HashMap readNgram(String txt, int n, boolean relativeFr)
    {
        HashMap<String,Double> frek = new HashMap<String,Double>();
        
        
        for (int i = 0; i < (txt.length()-1); i++) 
        {
            String subText = (String) txt.subSequence(i, i + n);
            //System.out.println(subText);
            if (frek.get(subText) == null)   // GET
            {
                frek.put(subText, 1.0 );    // PUT
            } 
            else 
            {
                frek.put(subText, frek.get(subText) + 1.0 );  
            }
        }
        if (relativeFr)
        {
            for (Map.Entry<String, Double> entry : frek.entrySet())
            {
                entry.setValue(entry.getValue()/txt.length() );
            }
        }
        return frek;
    }
    public static double[][] convertMap(HashMap<String,Double> m)
    {
        double [][] retVal = new double[26][26];
        for (HashMap.Entry<String, Double> entry : m.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            retVal[key.charAt(0)-'a'][key.charAt(1)-'a']=value;
        }
        return retVal;
    }
    
     public static Double[] Read1Grams(String txt, boolean relativeFr)
    {
        Double[] abc = new Double[26];
                      
            for(int j = 0; j <26; j++)
            {
                abc[j] = 0.0;
                for (int i = 0; i < (txt.length()-1); i++) 
                {
                    char letter = (char)('a'+j);
                    char letter2 = Character.toLowerCase(txt.charAt(i));
                    
                    if( letter == letter2)
                    {
                        abc[j] += 1.0;
                    }
                }
            }
        if (relativeFr)
        {
            for (int k = 0; k < 26; k++)
            {
                abc[k] = abc[k] / txt.length();
            }
        }
            
        return abc;
    }
     
    public static double IndexOfCoincidence(Double stat[], double n)
    {
        double ic=0;
       // n=n-1;
        double possibleDoubles=n*(n-1);
        for (int i=0; i<stat.length;i++)
        {
            ic= ic+(stat[i]*(stat[i]-1));
        }
        ic=ic/possibleDoubles;

        return ic;
    }
   
    public TextStatistics(){
    	
    }
  
    public static double calculateIC(String s){
    	
    	int i;
    	int N = 0;
    	double sum = 0.0;
    	double total = 0.0;
    	s = s.toUpperCase();
    	
    	//initialize array of values to count frequency of each letter
    	int[] values = new int[26];
    	for(i=0; i<26; i++){
    		values[i] = 0;
    	}
    	
    	//calculate frequency of each letter in string
    	int ch;
    	for(i=0; i<s.length(); i++){
    		ch = s.charAt(i)-65;
    		if(ch>=0 && ch<26){
    			values[ch]++;
    			N++;
    			}	
    	}
    	
    	//calculate the sum of each frequency
    	for(i=0; i<26; i++){
    		ch = values[i];
    		sum = sum + (ch * (ch-1));
    		}
    	
    	//divide by N(N-1)	
    	total = sum/(N*(N-1));
    	
    	//return the result
    	return total;
    	
    }
    
    public static boolean search(String Text, String word)
    {
        int len = Text.length();
        int lenword = word.length();
        int k = 0;
        for(int i =0; i < (len-lenword); i++)
        {
            if(Text.charAt(i) == word.charAt(k))
                k++;
            else k = 0;
            
            if( k == lenword)  return true;
        }
        return false;
    }
}
