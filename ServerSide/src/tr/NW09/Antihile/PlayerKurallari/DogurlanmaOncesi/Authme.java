package tr.NW09.Antihile.PlayerKurallari.DogurlanmaOncesi;

import org.bukkit.entity.Player;

import tr.NW09.Antihile.Listener.AuthmeDinleyici;
import tr.NW09.Antihile.PlayerKurallari.Dogrula;
import tr.NW09.Antihile.Plugin.StatikDegerler;

public class Authme extends Dogrula {
	public Authme() {
		StatikDegerler.Plugin.getServer().getPluginManager().registerEvents(new AuthmeDinleyici(), StatikDegerler.Plugin);
	}
	@Override
	public boolean izinlimi(String PlayerName) {
		if(AuthmeDinleyici.GirisYaptimi(PlayerName)){
			return true;
		}else{
			String Mesaj = StatikDegerler.Plugin.getConfig().getString("Authme.GirisYapmadiniz");
			Mesaj = StatikDegerler.ChatColorTranslateString(Mesaj);
			Player pl = StatikDegerler.Plugin.getServer().getPlayer(PlayerName);
			if(pl != null){
				pl.sendMessage(Mesaj);
			}
			return false;
		}
	}
	

}
