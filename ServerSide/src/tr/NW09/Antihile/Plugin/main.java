package tr.NW09.Antihile.Plugin;import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import tr.NW09.Antihile.Komut.AntiHile;
import tr.NW09.Antihile.Listener.AuthmeDinleyici;
import tr.NW09.Antihile.Listener.OyuncuEngelleyici;
import tr.NW09.Antihile.Logger.LogTur;
import tr.NW09.Antihile.Logger.Logger;
import tr.NW09.Antihile.PlayerKurallari.DogrulanmaOncesi;
import tr.NW09.Antihile.PlayerKurallari.DogrulanmaSonrasi;
import tr.NW09.Antihile.PlayerKurallari.DogurlanmaOncesi.Authme;
import tr.NW09.Antihile.PlayerKurallari.DogurlanmaSonrasi.BungeCoord;
import tr.NW09.Antihile.PlayerKurallari.DogurlanmaSonrasi.MetaData;
import tr.NW09.Antihile.Sunucu.SiteSession;

public class main extends JavaPlugin{
	public ClientKontrol clientKontrol;
	PluginDescriptionFile PluginYML = getDescription();
	public boolean enable;
	@Override
	public void onEnable() {
		saveDefaultConfig();
		StatikDegerler.Plugin = this;
		Logger.LOG(LogTur.Bilgi, "AntiHile V" + getDescription().getVersion() +" Açýlýyor.", false);
		StatikDegerler.Uniqueid = getConfig().getString("Hesap.UNIQUEID");
		StatikDegerler.Host = getConfig().getString("Hesap.HOST");
		StatikDegerler.Prefix = getConfig().getString("Prefix");
		enable = new SiteSession(StatikDegerler.Uniqueid,StatikDegerler.Host).LisansKontrol();
		if(!enable){
			return;
		}
		getCommand("antihile").setExecutor(new AntiHile());
		if(getConfig().getBoolean("Authme.GirisYapmasiniBekle")){
		DogrulanmaOncesi.Kuralekle(new Authme());
		}
		if(getConfig().getBoolean("BungeCoord.durum")){
			getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
			DogrulanmaSonrasi.Kuralekle(new BungeCoord(this));
		}
		getServer().getPluginManager().registerEvents(new OyuncuEngelleyici(), this);
		//lisans
		DogrulanmaSonrasi.Kuralekle(new MetaData());
		for(Player pl : getServer().getOnlinePlayers()){
			if(!pl.hasMetadata("Anti-Hile")){
				if(getConfig().getBoolean("Authme.GirisYapmasiniBekle")){
				AuthmeDinleyici.AddPlayer(pl.getName());
				}
				ClientYonetici.PlayerEkle(pl.getName());
			}
			}
		clientKontrol = new ClientKontrol();
		Thread thread = new Thread(clientKontrol);
		thread.start();
		BukkitScheduler scheduler = Bukkit.getScheduler();
		scheduler.scheduleSyncRepeatingTask(this, new UyariMesajiScheduler(), 0L,getConfig().getInt("Uyari_Mesaji.TekrarSaniye") *20L);
		scheduler.scheduleSyncRepeatingTask(this, new PlayerKicker(), 0L, 20L);
	}
	@Override
	public void onDisable() {
		Logger.LOG(LogTur.Bilgi, "AntiHile V" + getDescription().getVersion() +" Kapanýyor.", false);
		if(!enable){
			return;
		}
		clientKontrol.kapat();
	}
}
