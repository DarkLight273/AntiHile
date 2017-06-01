package tr.NW09.Antihile.Sunucu;


import java.util.Base64;

public class Decrypter {
	public static String LocalDecrypt(String Key) {
		return new String(Base64.getDecoder()
				.decode(new StringBuilder(new String(Base64.getDecoder().decode(Key)))
						.delete(new String(Base64.getDecoder().decode(Key)).toCharArray().length - 10,
								new String(Base64.getDecoder().decode(Key)).toCharArray().length)
						.delete(0, 10).toString()));
	}
}
