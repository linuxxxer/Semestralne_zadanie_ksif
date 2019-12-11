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
public class AphabeticalSequence {
    
    public static Integer[] Alpha(String word)
    {
        int len = word.length();
        int counter = 0;
        Integer[] retVal = new Integer[len];
        
        for(int i = 0; i < 26; i++)
        {
            char letter = (char) (i + 'a');
            
            for(int j = 0; j< len; j++)
            {
                if( letter == word.charAt(j))
                {
                    retVal[counter] = j+1;
                    counter++;
                }
            }
        }
        
        return retVal;
    }
}
