package tr.NW09.Antihile.Logger;

import org.bukkit.ChatColor;

import tr.NW09.Antihile.Plugin.StatikDegerler;

public class Logger {

	public static void LOG(LogTur Sinif, String Mesaj,boolean durum, Exception e) {
		if(StatikDegerler.Plugin.getConfig().getBoolean("Logger.ConsoleLog")){
			LoggerClass logger = new PluginLogger(StatikDegerler.Plugin);
			logger.LOG(Sinif, Mesaj);
		}
		if(StatikDegerler.Plugin.getConfig().getBoolean("Logger.Filelog")){
			LoggerClass logger = new FileLogger(StatikDegerler.Plugin);
			logger.LOG(Sinif, ChatColor.stripColor(Mesaj),e);
		}
		if(durum){
			LOG(LogTur.Uyari,"Plugin Devre Disi Kaliyor...",false);
			StatikDegerler.Plugin.getServer().getPluginManager().disablePlugin(StatikDegerler.Plugin);
		}
	}
	
	public static void LOG(LogTur Sinif, String Mesaj,boolean durum) {
		if(StatikDegerler.Plugin.getConfig().getBoolean("Logger.ConsoleLog")){
			LoggerClass logger = new PluginLogger(StatikDegerler.Plugin);
			logger.LOG(Sinif, Mesaj);
		}
		if(StatikDegerler.Plugin.getConfig().getBoolean("Logger.Filelog")){
			LoggerClass logger = new FileLogger(StatikDegerler.Plugin);
			logger.LOG(Sinif, ChatColor.stripColor(Mesaj));
		}
		if(durum){
			LOG(LogTur.Uyari,"Plugin Devre Disi Kaliyor...",false);
			StatikDegerler.Plugin.getServer().getPluginManager().disablePlugin(StatikDegerler.Plugin);
		}
	}

}
