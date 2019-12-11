/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.crypto;

/**
 *
 * @author vikto
 */
public interface Cipher {
    public String encrypt(String text, Key k);
    public String decrypt(String text, Key k);
}
