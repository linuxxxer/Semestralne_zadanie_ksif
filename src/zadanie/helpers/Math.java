/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.helpers;

/**
 *
 * @author vikto
 */
public class Math {
    

     public static boolean isPrime(int input) 
    {   
        if (input == 1) return false;
        if (input == 2) return true;
        if (input % 2 == 0) 
        {
            return false;
        }
        for (int i = 3; i * i <= input; i += 2) 
        {
            if (input % i == 0) 
            {
                return false;
            }
        }
        return true;
    }
    
    public static int factorial(int n) 
    {
        int value = 1;
        for (int i = 2; i <= n; i++) 
        {
            value *= i;
        }
        return value;
    }
}