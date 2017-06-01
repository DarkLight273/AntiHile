package tr.NW09.Antihile.Plugin;

import java.util.ArrayList;

import tr.NW09.Antihile.Logger.LogTur;
import tr.NW09.Antihile.Logger.Logger;
import tr.NW09.Antihile.PlayerKurallari.DogrulanmaOncesi;
import tr.NW09.Antihile.Sunucu.ClientData;
import tr.NW09.Antihile.Sunucu.SiteSession;

public class ClientKontrol implements Runnable {
	public Boolean Durum;

	public ClientKontrol() {
		Durum = true;
	}

	@Override
	public void run() {
		while (Durum) {
			ArrayList<String> list = ClientYonetici.GetList();
			if(list.isEmpty()){
				continue;
			}
			String Host = StatikDegerler.Plugin.getConfig().getString("Hesap.HOST");
			String Uniqueid = StatikDegerler.Plugin.getConfig().getString("Hesap.UNIQUEID");
			SiteSession Site = new SiteSession(Uniqueid, Host);
			for (String Player : list) {
				if(!DogrulanmaOncesi.izinlimi(Player)){
					continue;
				}
				String IP = StatikDegerler.Plugin.getServer().getPlayer(Player).getAddress().getAddress().getHostAddress();
				ClientData client = Site.ClientIste(Player, IP);
				if (client.getIzinVer() != null) {
					if(client.getIzinVer()){
					ClientYonetici.PlayerIzinver(Player);
					}else{
						ClientYonetici.PlayerKick(Player, client.getClientName());
					}
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				
			}
		}
	}
	public void kapat() {
		Durum = false;
	}
}
