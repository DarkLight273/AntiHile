package tr.NW09.Antihile.Logger;

import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginLogger implements LoggerClass {
	private JavaPlugin Plugin;

	public PluginLogger(JavaPlugin Plugin) {
		this.Plugin = Plugin;
	}

	@Override
	public void LOG(LogTur Sinif, String Mesaj, Exception e) {
		switch (Sinif) {
		case Bilgi:
			Plugin.getLogger().log(Level.INFO, Mesaj, e);
			break;
		case Hata:
			Plugin.getLogger().log(Level.SEVERE, Mesaj, e);
			break;
		case Uyari:
			Plugin.getLogger().log(Level.WARNING, Mesaj, e);
			break;
		}
	}

	@Override
	public void LOG(LogTur Sinif, String Mesaj) {
		switch (Sinif) {
		case Bilgi:
			Plugin.getLogger().log(Level.INFO, Mesaj);
			break;
		case Hata:
			Plugin.getLogger().log(Level.SEVERE, Mesaj);
			break;
		case Uyari:
			Plugin.getLogger().log(Level.WARNING, Mesaj);
			break;
		}
	}

}
