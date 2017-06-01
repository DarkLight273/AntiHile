/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.NW09.antihilepublic.Berti;

import java.nio.charset.StandardCharsets;

/**
 *
 * @author Halil
 */


public class BertiYontemi {
	public static String encryptWithBertiYontemi(Integer key , String data){
		byte[] sifreli = new byte[data.length()];
		Integer x = 0;
		for(byte b : data.getBytes()){
			int a = (int)(b + key);
			byte c = (byte)a;
			sifreli[x] = c;
			x++;
		}
		String b64 = Base64.EncryptToString(sifreli);
		return b64;
	}
	public static String decryptWithBertiYontemi(Integer key , String data){
		byte[] encrypted = Base64.Decrypt(data);
		byte[] sifresiz = new byte[encrypted.length];
		Integer x = 0;
		for(byte b : encrypted){
			int a = (int)(b - key);
			byte c = (byte)a;
			sifresiz[x] = c;
			x++;
		}
		return new String(sifresiz);
	}
}
