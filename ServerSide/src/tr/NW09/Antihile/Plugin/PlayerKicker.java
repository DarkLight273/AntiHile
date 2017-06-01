package tr.NW09.Antihile.Plugin;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayerKicker implements Runnable{
	public static ArrayList<String> liste = new ArrayList<String>();
	@Override
	public void run() {
		for (String string : liste) {
			Player pl = StatikDegerler.Plugin.getServer().getPlayer(string);
			if(pl != null){
				String KickMesaji = StatikDegerler.Plugin.getConfig().getString("Mesajlar.Oyuncuya_Gonderilecek_Mesajlar.BasarisizKickMesaji");
				KickMesaji = StatikDegerler.ChatColorTranslateString(KickMesaji);
				pl.kickPlayer(KickMesaji);
			}
		}
		liste.clear();
	}

}
