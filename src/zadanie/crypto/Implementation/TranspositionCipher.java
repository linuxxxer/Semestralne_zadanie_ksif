/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.crypto.Implementation;

import zadanie.crypto.Cipher;
import zadanie.crypto.Key;

public class TranspositionCipher implements Cipher {

    @Override
    public String encrypt(String pt, Key k) {
        if (!(k instanceof TranspositionKey)) {
            throw new IllegalArgumentException("Vstupom musí byť inštancia TranspositionKey");
        }

        TranspositionKey mk = (TranspositionKey) k;
        // doplnime aby sme mali vstup velkosti nasobku kluca
        StringBuilder pt2 = new StringBuilder(pt);
        int missing =  mk.blockSize - (pt.length() % mk.blockSize);

        for (int i = 0; i < missing; i++) {
            pt2.append('x');
        }
        //System.out.println("Dlzka OT: "+pt.length());
        //System.out.println("Dlzka kluca: "+mk.blockSize);
       // System.out.println("Pocet doplnenych znakov:" + missing);

        // sifrovanie
        StringBuilder ct = new StringBuilder();
        for (int i = 0; i < pt2.length(); i += mk.blockSize) {
            String block = pt2.substring(i, i + mk.blockSize);
            block = applyPermutation(block, mk.encPerm);
            ct.append(block);
        }

        return ct.toString();
    }

    @Override
    public String decrypt(String ct, Key k) {
        if (!(k instanceof TranspositionKey)) {
            throw new IllegalArgumentException("Vstupom musí byť inštancia TranspositionKey");
        }
        TranspositionKey mk = (TranspositionKey) k;

        // desifrovanie
        StringBuilder pt = new StringBuilder();
        for (int i = 0; i < ct.length(); i += mk.blockSize) {
            String block = ct.substring(i, i + mk.blockSize);
            block = applyPermutation(block, mk.decPerm);
            pt.append(block);
        }

        return pt.toString();
    }

	/*
	perm z M={1,..,n}
	*/
    private String applyPermutation(String input, Integer[] perm) {
            char output[] = new char[perm.length];
            for (int i=0; i < perm.length; i++) {
                output[perm[i]-1] = input.charAt(i);
            }
            return new String(output);
    }
}