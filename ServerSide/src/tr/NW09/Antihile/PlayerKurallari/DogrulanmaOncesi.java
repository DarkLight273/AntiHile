package tr.NW09.Antihile.PlayerKurallari;

import java.util.ArrayList;

public class DogrulanmaOncesi{
	private static ArrayList<Dogrula> Kurallar = new ArrayList<Dogrula>();
	public static boolean izinlimi(String PlayerName) {
		for (Dogrula dogrula : Kurallar) {
			if(!dogrula.izinlimi(PlayerName)){
				return false;
			}
		}
		return true;
	}
	
	public static void Kuralekle(Dogrula dogrula){
		Kurallar.add(dogrula);
	}
	
}
