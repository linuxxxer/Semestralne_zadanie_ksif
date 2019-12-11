/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.crypto;

import zadanie.helpers.Text;

/**
 *
 * @author vikto
 */
public class BigramFitness {
    public static Double BigramFit(double[][] input)
    {
         Character abc[] = new Character[26];
        for (int i = 0; i < abc.length; i++) {
            abc[i] = (char) (i + 'a');
        }
        
        double[][] ref = (double[][]) Text.readFromFile("_bigrams.bin");
        
        double suma = 0.0;
        //Map<String,Double> bigram = new HashMap<String,Double>();
                
        for(int i=0; i<26; i++)
        {
            for(int j=0; j<26; j++)
            {
                 suma+= Math.abs(ref[i][j] - input[i][j]);
            }
        }
        return suma;
    }
}
