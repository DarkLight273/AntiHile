package tr.NW09.Antihile.Plugin;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class StatikDegerler {
	public static String Uygulama_Linki = "Uygulama Linki Bulunamadi.";
	public static JavaPlugin Plugin;
	public static String Uniqueid;
	public static String Host;
	public static String Prefix;
	public static ArrayList<String> ChatColorTranslateString(ArrayList<String> Old) {
		ArrayList<String> New = new ArrayList<String>();
		for (String string : Old) {
			New.add(ChatColorTranslateString(string));
		}
		return New;
	}

	public static String ChatColorTranslateString(String Old) {
		if(Old.equalsIgnoreCase("")){
			return "";
		}
		return ChatColor.translateAlternateColorCodes('&',Prefix) + " " +ChatColor.translateAlternateColorCodes('&', Old);
	}
	public static String[] ChatColorTranslateString(String[] Old){
		String[] yeni = new String[Old.length];
		for (int i = 0; i < Old.length; i++) {
			yeni[i] = ChatColorTranslateString(Old[i]);
		}
		return yeni;
	}
	public static ArrayList<String> DegerDegistir(ArrayList<String> liste,String eskideger,String yeniDeger){
		ArrayList<String> newliste = new ArrayList<String>();
		for (String string : liste) {
			newliste.add(string.replaceAll(eskideger, yeniDeger));
		}
		return newliste;
	}

	public static String TimeStampToDate(long timestamp) {
		Timestamp stamp = new Timestamp(timestamp * 1000);
		Date date = new Date(stamp.getTime());
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return dateFormat.format(date);
	}
}
