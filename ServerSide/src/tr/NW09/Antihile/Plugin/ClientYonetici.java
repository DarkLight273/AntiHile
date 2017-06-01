package tr.NW09.Antihile.Plugin;

import java.util.ArrayList;

import tr.NW09.Antihile.PlayerKurallari.DogrulanmaSonrasi;

public class ClientYonetici {
	private static ArrayList<String> PlayerList = new ArrayList<String>();
	public static void PlayerIzinver(String PlayerName){
		if(PlayerName == null){
			return;
		}
		PlayerSil(PlayerName);
		Clientislemleri.Dogrulandi(PlayerName);
		DogrulanmaSonrasi.izinverildi(PlayerName);
	}
	public static void PlayerKick(String PlayerName,String ClientName){
		if(PlayerName == null){
			return;
		}
		PlayerKicker.liste.add(PlayerName);
		Clientislemleri.Dogrulanmadi(PlayerName, ClientName);
	}
	public static void PlayerEkle(String PlayerName){
		if(PlayerName == null){
			return;
		}
		if(!PlayerList.contains(PlayerName)){
			PlayerList.add(PlayerName);
		}
	}
	public static void PlayerSil(String PlayerName){
		if(PlayerName == null){
			return;
		}
		if(PlayerList.contains(PlayerName)){
		PlayerList.remove(PlayerName);
		}
	}
	public static ArrayList<String> GetList(){
		ArrayList<String> Yeni = new ArrayList<String>(PlayerList);
		return Yeni;
	}
}
