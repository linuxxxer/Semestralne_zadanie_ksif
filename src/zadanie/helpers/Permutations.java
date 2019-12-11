/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.helpers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import zadanie.helpers.Math;

/**
 *
 * @author vikto
 */
public class Permutations {
     private static Random rnd = new Random(System.currentTimeMillis());
    
    public static void rndPerm(Object input[]) {
        
        int size = input.length;
        for (int i = 0; i < (size - 1); i++) 
        {
            int j = rnd.nextInt(size - i) + i;
            // swap
            Object tmp = input[i];
            input[i] = input[j];
            input[j] = tmp;
        }
    }
    
     public static List allPerm(int input[]) {
        List<int[]> retVal = new ArrayList(Math.factorial(input.length));
        allPerm(0, input, retVal);
        return retVal;
    }

     private static void allPerm(int fixed, int input[], List output) {
        int in[] = input.clone();
        if (fixed == input.length) {
            output.add(input);
        } else {
            for (int i = fixed; i < input.length; i++) {
                // swap
                int tmp = in[i];
                in[i] = in[fixed];
                in[fixed] = tmp;
                // recursion
                allPerm(fixed + 1, in, output);
            }
        }
    }
     public static int[] intPole(int input)
     {
         int[] numbers = new int[input];
         for(int n=0;n<input;n++) { numbers[n] = n+1;}
         return numbers;
     }
     
     public static Character[] swap(Character abc[])
  {
      int i = rnd.nextInt(26);
     // System.out.print(i);
      int j = rnd.nextInt(26);
    //  System.out.print(j);
      while(i == j )
      {
          j = rnd.nextInt(26);
      }
      
      Character tmp = abc[i];
      abc[i] = abc[j];
      abc[j] = tmp;
      
      return abc;
  }
     public static void swapabc(Character abc[])
  {
      int i = rnd.nextInt(26);
      int j = rnd.nextInt(26);
      while(i != j )
      {
          j = rnd.nextInt(26);
      }
      
      Character tmp = abc[i];
      abc[i] = abc[j];
      abc[j] = tmp;
      
  }

     public static Integer[] inverse(Integer[] p) 
     {
        Integer[] inverse = new Integer[p.length];
        
        for (int i = 0; i < p.length; i++) 
        {
            inverse[p[i]-1] = i+1;
        }
         
        return inverse;
    }
     
    public static Character[] inverse(Character[] p) 
    {
        Character[] inverse = new Character[p.length];
        
        for (int i = 0; i < p.length; i++) 
        {
            inverse[p[i] - 'a'] = (char) (i + 'a');
        }
        return inverse;
    }
    
    public static Integer[] inverse(Object[] perm){
         List sorted = Arrays.asList(perm.clone()); 
         Collections.sort(sorted); 
         
         Integer[] tmp = new Integer[perm.length];
         for(int i=0; i < perm.length; i++){
             tmp[i] = sorted.indexOf(perm[i])+1;
	 }
         return inverse(tmp);
     }
    
   }
