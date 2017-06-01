package tr.NW09.Antihile.Listener;

import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import tr.NW09.Antihile.Plugin.ClientYonetici;
import tr.NW09.Antihile.Plugin.Clientislemleri;
import tr.NW09.Antihile.Plugin.StatikDegerler;

public class OyuncuEngelleyici implements Listener {
	@EventHandler
	public void OyunaGirme(PlayerJoinEvent e){
		ClientYonetici.PlayerEkle(e.getPlayer().getName());
		Clientislemleri.UyariMesaji(e.getPlayer().getName());
	}
	@EventHandler
	public void Oyundancikma(PlayerQuitEvent e){
		ClientYonetici.PlayerSil(e.getPlayer().getName());
	}
	@EventHandler
	public void HareketEngelleme(PlayerMoveEvent e) {
		if (!StatikDegerler.Plugin.getConfig().getBoolean("Engelleme.Hareket_Etmesini_Engelle")) {
			return;
		}
		if (!ClientYonetici.GetList().contains(e.getPlayer().getName())) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void SohbetEngelleme(PlayerChatEvent e) {
		if (!StatikDegerler.Plugin.getConfig().getBoolean("Engelleme.Sohbet_Etmesini_Engelle")) {
			return;
		}
		if (!ClientYonetici.GetList().contains(e.getPlayer().getName())) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void KomutEngelle(PlayerCommandPreprocessEvent e) {
		if (!StatikDegerler.Plugin.getConfig().getBoolean("Engelleme.Komut_Girmesini_Engelle.durum")) {
			return;
		}
		if (!ClientYonetici.GetList().contains(e.getPlayer().getName())) {
			return;
		}
		for (String izinliKomut : (List<String>) StatikDegerler.Plugin.getConfig()
				.getList("Engelleme.Komut_Girmesini_Engelle.Izinli_Komutlar")) {
			if (e.getMessage().startsWith(izinliKomut)) {
				return;
			}
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void EnvanterEngelle(PlayerInteractEvent e) {
		if (!StatikDegerler.Plugin.getConfig().getBoolean("Engelleme.Herhangi_Bir_Yere_Tiklamasini_Engelle")) {
			return;
		}
		if (!ClientYonetici.GetList().contains(e.getPlayer().getName())) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void DropEngelle(PlayerDropItemEvent e) {
		if (!StatikDegerler.Plugin.getConfig().getBoolean("Engelleme.Envanter.Yere_Esya_Atmasini_Engelle")) {
			return;
		}
		if (!ClientYonetici.GetList().contains(e.getPlayer().getName())) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void PickupEngelle(PlayerPickupItemEvent e) {
		if (!StatikDegerler.Plugin.getConfig().getBoolean("Engelleme.Envanter.Yerden_Esya_Almasini_Engelle")) {
			return;
		}
		if (!ClientYonetici.GetList().contains(e.getPlayer().getName())) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void HasarAlmasiniEngelle(EntityDamageEvent e) {
		if (!StatikDegerler.Plugin.getConfig().getBoolean("Engelleme.Hasar_Almaz_Vermez")) {
			return;
		}
		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player pl = (Player) e.getEntity();
		if (!ClientYonetici.GetList().contains(pl.getName())) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void HasarVermesiniEngelle(EntityDamageByEntityEvent e) {
		if (!StatikDegerler.Plugin.getConfig().getBoolean("Engelleme.Hasar_Almaz_Vermez")) {
			return;
		}
		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player pl = (Player) e.getDamager();
		if (!ClientYonetici.GetList().contains(pl.getName())) {
			return;
		}
		e.setCancelled(true);
	}
}