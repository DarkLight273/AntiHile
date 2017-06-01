/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.NW09.antihilepublic.Site;

/**
 *
 * @author Userxxx
 */
import com.sun.xml.internal.messaging.saaj.util.Base64;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.DatatypeConverter;

public class Decrypter {
	public static String LocalDecrypt(String Key) {
            try {
                return new String(DatatypeConverter.parseBase64Binary(new StringBuilder(new String(DatatypeConverter.parseBase64Binary(Key)))
                        .delete(new String(DatatypeConverter.parseBase64Binary(Key)).toCharArray().length - 10,
                                new String(DatatypeConverter.parseBase64Binary(Key)).toCharArray().length)
                        .delete(0, 10).toString()),"UTF-8");
            } catch (UnsupportedEncodingException ex) {
                return new String(DatatypeConverter.parseBase64Binary(new StringBuilder(new String(DatatypeConverter.parseBase64Binary(Key)))
                        .delete(new String(DatatypeConverter.parseBase64Binary(Key)).toCharArray().length - 10,
                                new String(DatatypeConverter.parseBase64Binary(Key)).toCharArray().length)
                        .delete(0, 10).toString()));
            }
	}
}
