package tr.NW09.Antihile.PlayerKurallari.DogurlanmaSonrasi;

import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

import tr.NW09.Antihile.PlayerKurallari.Dogrula;
import tr.NW09.Antihile.Plugin.StatikDegerler;

public class MetaData extends Dogrula{

	@Override
	public void izinverildi(String Playername) {
		Player pl = StatikDegerler.Plugin.getServer().getPlayer(Playername);
		pl.setMetadata("Anti-Hile", new FixedMetadataValue(StatikDegerler.Plugin, true));
	}
	
}
