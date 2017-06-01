package tr.NW09.Antihile.PlayerKurallari.DogurlanmaSonrasi;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import tr.NW09.Antihile.PlayerKurallari.Dogrula;
import tr.NW09.Antihile.Plugin.StatikDegerler;

public class BungeCoord extends Dogrula {
	private static tr.NW09.Antihile.Plugin.main main;
	public BungeCoord(tr.NW09.Antihile.Plugin.main main) {
		this.main = main;
	}
	@Override
	public void izinverildi(String Playername) {
		OyuncuAktar(Playername);
	}

	private void OyuncuAktar(String PlayerName) {
		yonlendir(PlayerName);
	}

	public static void yonlendir(String Oyuncu) {
		final String coyuncu = Oyuncu;
		if (Bukkit.getServer().getPlayer(Oyuncu) == null) {
			return;
		}
		ArrayList<String> YonlendirmeMesaji = (ArrayList<String>) StatikDegerler.Plugin.getConfig()
				.getList("BungeCoord.YonlendirmeMesaji");
		YonlendirmeMesaji = StatikDegerler.DegerDegistir(YonlendirmeMesaji, "%lobi%",
				StatikDegerler.Plugin.getConfig().getString("BungeCoord.HedefLobi"));
		YonlendirmeMesaji = StatikDegerler.ChatColorTranslateString(YonlendirmeMesaji);
		for (String string : YonlendirmeMesaji) {
			Bukkit.getServer().getPlayer(Oyuncu).sendMessage(string);
		}
		Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(StatikDegerler.Plugin, new Runnable() {
			public void run() {
				sendToServer(Bukkit.getServer().getPlayer(coyuncu),
						StatikDegerler.Plugin.getConfig().getString("BungeCoord.HedefLobi"));
			}
		}, StatikDegerler.Plugin.getConfig().getInt("BungeCoord.YonlendirmeSuresi") * 20);
	}

	public static void sendToServer(Player player, String targetServer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("Connect");
			out.writeUTF(targetServer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		player.sendPluginMessage(main, "BungeeCord", b.toByteArray());
	}

}
