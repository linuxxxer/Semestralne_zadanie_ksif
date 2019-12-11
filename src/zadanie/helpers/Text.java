/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.Normalizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author vikto
 */
public class Text {
  public static void stringToFile( String text, String fileName )
     {
        try
        {
            File file = new File( fileName + ".txt" );

            // if file doesnt exists, then create it 
            if ( ! file.exists( ) )
            {
                file.createNewFile( );
            }

            FileWriter fw = new FileWriter( file.getAbsoluteFile( ) );
            BufferedWriter bw = new BufferedWriter( fw );
            bw.write( text );
            bw.close( );
            //System.out.println("Done writing to " + fileName); //For testing 
        }

        catch( IOException e )
        {
        System.out.println("Error: " + e);
        e.printStackTrace( );
        }
    }

    public static String convertToTSA(String in, boolean keepSpace)
    {
        in=in.toLowerCase();
        in=Normalizer.normalize(in,Normalizer.Form.NFD);
        in=in.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        if(!keepSpace)
        {
            in=in.replaceAll("[^a-z]","");
        }
        else
        {
            in=in.replaceAll("[^a-z]", " ");
        }
        return in;
    }
    
    public static String[] splitByNumber(String text, int number) {

        int inLength = text.length();
        int arLength = inLength / number;
        
        int left = inLength%number;
        
        if(left>0)
            {
                ++arLength;
            }
        String ar[] = new String[arLength];
        String tempText=text;
        for (int x = 0; x < arLength; ++x) 
            {

                if(tempText.length()>number)
                {
                    ar[x]=tempText.substring(0, number);
                    tempText=tempText.substring(number);
                }else{
                    ar[x]=tempText;
                }
            }

        return ar;
    }
    
    public static String giveCharsInN(String text, int number)
    {
        int inLength = text.length();

        String tempText = "";
         
        for (int x = 0; x < inLength; x += number) 
            {
                tempText += text.charAt(x);
            }

        return tempText;
    }
    
    public static File pickFromFileChooser() {
       JFileChooser fc = new JFileChooser();
       fc.setCurrentDirectory(new File("."));
       fc.setDialogTitle("Select the file to open... ");
       fc.setMultiSelectionEnabled(false);
       fc.setFileSelectionMode(JFileChooser.FILES_ONLY);

       int returnVal = fc.showOpenDialog(null);

       if (returnVal == JFileChooser.APPROVE_OPTION) {
           File f = fc.getSelectedFile();
           return f;
       }
       return null;
   }

   public static String readText(File file) {
       
       StringBuilder sb = new StringBuilder();
       if (file.exists()) {
           BufferedReader br = null;
           try {
               br = new BufferedReader(new FileReader(file));
               String line;
               while ((line = br.readLine()) != null) {
                   sb.append(line).append("\n");
               }
           } catch (Exception ex) {
               sb = null;
               Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
           } finally {
               try {
                   br.close();
               } catch (IOException ex) {
                   Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       return sb.toString();
   }

   public static boolean writeText(File file, String content) {
       if (file.exists()) {
           BufferedWriter bw = null;
           try {
               bw = new BufferedWriter(new FileWriter(file));
               bw.write(content);
               bw.flush();
               return true;
           } catch (IOException ex) {
               Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
           } finally {
               try {
                   bw.close();
               } catch (IOException ex) {
                   Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
       return false;
   }
    public static void printToFile(String data,String menoSuboru) {
        FileWriter fw= null;
        File file =null;
        try {
            file=new File(menoSuboru + ".txt");
            if(!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file);
            fw.write(data);
            fw.flush();
            fw.close();
            //System.out.println("File written Succesfully");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
   public static void saveToFile(Object o, String path) {
       try {
           FileOutputStream fos = new FileOutputStream(path);
           ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(o);
           oos.close();
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException ex) {
           Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
       }
   }

   public static Object readFromFile(String path) {
       try {
           FileInputStream fis = new FileInputStream(path);
           ObjectInputStream ois = new ObjectInputStream(fis);
           Object o = ois.readObject();
           return o;
       } catch (FileNotFoundException ex) {
           Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
       } catch (IOException | ClassNotFoundException ex) {
           Logger.getLogger(Text.class.getName()).log(Level.SEVERE, null, ex);
       }
       return null;
   } 
}
