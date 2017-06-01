/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.NW09.antihilepublic.Berti;

/**
 *
 * @author Halil
 */
import java.nio.charset.StandardCharsets;

import javax.xml.bind.DatatypeConverter;

public class Base64 {

    public static byte[] Encrypt(String Data) {
        String encoded = DatatypeConverter.printBase64Binary(Data.getBytes(StandardCharsets.UTF_8));
        return encoded.getBytes(StandardCharsets.UTF_8);
    }

    public static byte[] Encrypt(byte[] Data) {
        String encoded = DatatypeConverter.printBase64Binary(Data);
        return encoded.getBytes(StandardCharsets.UTF_8);
    }

    public static String EncryptToString(String Data) {
        String encoded = DatatypeConverter.printBase64Binary(Data.getBytes(StandardCharsets.UTF_8));
        return encoded;
    }

    public static String EncryptToString(byte[] Data) {
        String encoded = DatatypeConverter.printBase64Binary(Data);
        return encoded;
    }

    public static byte[] Decrypt(String Data) {
        byte[] decoded = DatatypeConverter.parseBase64Binary(Data);
        return decoded;

    }

    public static byte[] Decrypt(byte[] Data) {
        byte[] decoded = DatatypeConverter.parseBase64Binary(new String(Data, StandardCharsets.UTF_8));
        return decoded;
    }

    public static String DecryptToString(String Data) {
        byte[] decoded = DatatypeConverter.parseBase64Binary(Data);
        return new String(decoded, StandardCharsets.UTF_8);

    }

    public static String DecryptToString(byte[] Data) {
        byte[] decoded = DatatypeConverter.parseBase64Binary(new String(Data, StandardCharsets.UTF_8));
        return new String(decoded, StandardCharsets.UTF_8);
    }

}
