package tr.NW09.Antihile.PlayerKurallari;

import java.util.ArrayList;

public class DogrulanmaSonrasi{
	private static ArrayList<Dogrula> Kurallar = new ArrayList<Dogrula>();
	
	public static void izinverildi(String Playername) {
		for (Dogrula dogrula : Kurallar) {
			dogrula.izinverildi(Playername);
		}
	}

	public static void Kuralekle(Dogrula dogrula){
		Kurallar.add(dogrula);
	}
}
