package tr.NW09.Antihile.Listener;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.xephi.authme.events.LoginEvent;
import tr.NW09.Antihile.Plugin.Clientislemleri;

public class AuthmeDinleyici implements Listener {
	private static ArrayList<String> PlayerList = new ArrayList<String>();
	@EventHandler
	public void GirisYapti(PlayerJoinEvent e) {
		PlayerList.add(e.getPlayer().getName());
	}
	@EventHandler
	public void CikisYapti(PlayerQuitEvent e){
		PlayerList.remove(e.getPlayer().getName());
	}
	@EventHandler
	public void AuthmeGiris(LoginEvent e){
		if(e.isLogin()){
			PlayerList.remove(e.getPlayer().getName());
			Clientislemleri.UyariMesaji(e.getPlayer().getName());
		}
	}
	public static boolean GirisYaptimi(String Player){
		if(PlayerList.contains(Player)){
			return false;
		}else{
			return true;
		}
	}
	public static void AddPlayer(String PlayerName){
		PlayerList.add(PlayerName);
	}
}
