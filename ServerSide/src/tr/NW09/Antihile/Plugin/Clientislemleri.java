package tr.NW09.Antihile.Plugin;

import java.util.ArrayList;
import org.bukkit.entity.Player;

import tr.NW09.Antihile.Listener.AuthmeDinleyici;
import tr.NW09.Antihile.Logger.LogTur;
import tr.NW09.Antihile.Logger.Logger;

public class Clientislemleri {
	public static void Dogrulandi(String PlayerName){
		if(StatikDegerler.Plugin.getServer().getPlayer(PlayerName) == null){
			return;
		}
		Player pl = StatikDegerler.Plugin.getServer().getPlayer(PlayerName);
		if(StatikDegerler.Plugin.getConfig().getBoolean("Mesajlar.Oyuncuya_Gonderilecek_Mesajlar.durum")){
		ArrayList<String> Mesaj = (ArrayList<String>) StatikDegerler.Plugin.getConfig().getList("Mesajlar.Oyuncuya_Gonderilecek_Mesajlar.Basarili");
		Mesaj = StatikDegerler.ChatColorTranslateString(Mesaj);
		pl.sendMessage(Mesaj.toArray(new String[Mesaj.size()]));
		}
		if(StatikDegerler.Plugin.getConfig().getBoolean("Mesajlar.Duyuru_Mesajlar.durum")){
			ArrayList<String> Mesaj = (ArrayList<String>) StatikDegerler.Plugin.getConfig().getList("Mesajlar.Duyuru_Mesajlar.Basarili");
			Mesaj = StatikDegerler.DegerDegistir(Mesaj, "%player%", PlayerName);
			Mesaj = StatikDegerler.ChatColorTranslateString(Mesaj);
			for(String string : Mesaj){
			StatikDegerler.Plugin.getServer().broadcastMessage(string);
			}
		}
		if(StatikDegerler.Plugin.getConfig().getBoolean("Mesajlar.Log_Bilgilendirme.durum")){
			String IP = pl.getAddress().getHostName();
			ArrayList<String> Mesaj = (ArrayList<String>) StatikDegerler.Plugin.getConfig().getList("Mesajlar.Log_Bilgilendirme.Basarili");
			Mesaj = StatikDegerler.DegerDegistir(Mesaj, "%player%", PlayerName);
			Mesaj = StatikDegerler.DegerDegistir(Mesaj, "%IP%", IP);
			for(String string : Mesaj){
			Logger.LOG(LogTur.Bilgi, string, false);
			}
		}
	}
	public static void Dogrulanmadi(String PlayerName,String ClientName){
		if(StatikDegerler.Plugin.getServer().getPlayer(PlayerName) == null){
			return;
		}
		Player pl = StatikDegerler.Plugin.getServer().getPlayer(PlayerName);
		if(StatikDegerler.Plugin.getConfig().getBoolean("Mesajlar.Duyuru_Mesajlar.durum")){
			ArrayList<String> Mesaj = (ArrayList<String>) StatikDegerler.Plugin.getConfig().getList("Mesajlar.Duyuru_Mesajlar.Basarisiz");
			Mesaj = StatikDegerler.DegerDegistir(Mesaj, "%player%", PlayerName);
			Mesaj = StatikDegerler.ChatColorTranslateString(Mesaj);
			for(String string : Mesaj){
			StatikDegerler.Plugin.getServer().broadcastMessage(string);
			}
		}
		if(StatikDegerler.Plugin.getConfig().getBoolean("Mesajlar.Log_Bilgilendirme.durum")){
			String IP = pl.getAddress().getHostName();
			ArrayList<String> Mesaj = (ArrayList<String>) StatikDegerler.Plugin.getConfig().getList("Mesajlar.Log_Bilgilendirme.Basarisiz");
			Mesaj = StatikDegerler.DegerDegistir(Mesaj, "%player%", PlayerName);
			Mesaj = StatikDegerler.DegerDegistir(Mesaj, "%IP%", IP);
			Mesaj = StatikDegerler.DegerDegistir(Mesaj, "%Client%", ClientName);
			for(String string : Mesaj){
			Logger.LOG(LogTur.Bilgi, string, false);
			}
		}
	}
	public static void UyariMesaji(String PlayerName){
		if(!AuthmeDinleyici.GirisYaptimi(PlayerName)){
			return;
		}
		if(StatikDegerler.Plugin.getServer().getPlayer(PlayerName) == null){
			return;
		}
		Player pl = StatikDegerler.Plugin.getServer().getPlayer(PlayerName);
		ArrayList<String> Mesaj = (ArrayList<String>) StatikDegerler.Plugin.getConfig().getList("Uyari_Mesaji.Mesaj");
		Mesaj = StatikDegerler.DegerDegistir(Mesaj, "%player%", PlayerName);
		Mesaj = StatikDegerler.DegerDegistir(Mesaj, "%link%", StatikDegerler.Uygulama_Linki);
		Mesaj = StatikDegerler.ChatColorTranslateString(Mesaj);
		pl.sendMessage(Mesaj.toArray(new String[Mesaj.size()]));
	}
}