package tr.NW09.Antihile.Komut;


import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import tr.NW09.Antihile.Listener.AuthmeDinleyici;
import tr.NW09.Antihile.Permission.PermissionAciklama;
import tr.NW09.Antihile.Permission.Permissions;
import tr.NW09.Antihile.Plugin.ClientYonetici;
import tr.NW09.Antihile.Plugin.StatikDegerler;
import tr.NW09.Antihile.Sunucu.LisansData;
import tr.NW09.Antihile.Sunucu.SiteSession;

public class AntiHile implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		switch (args.length) {
		case 0:
			Komut_Help(sender);
			break;
		case 1:
			if(args[0].equalsIgnoreCase("Yardim")){
				Komut_Help(sender);
			}else if(args[0].equalsIgnoreCase("Listele")){
				Komut_Listele(sender);
			}else if(args[0].equalsIgnoreCase("Reload")){
				Komut_Reload(sender);
			}else if(args[0].equalsIgnoreCase("Lisans")){
				Komut_Lisans(sender);
			}else if(args[0].equalsIgnoreCase("Yetkiler")){
				Komut_Permission(sender);
			}else{
				Komut_Help(sender);
			}
			break;
		case 2:
			if(args[0].equalsIgnoreCase("Listele")){
				Komut_Listele(sender, args[1]);
			}else if(args[0].equalsIgnoreCase("izinver")){
				Komut_izinver(sender, args[1]);
			}else{
				Komut_Help(sender);
			}
			break;
		default:
			Komut_Help(sender);
			break;
		}
		return false;
	}
	public void YetkinYok(CommandSender sender){
		String YetkinYok = "&aBu Komut Için Gerekli Yetkiye &cSahip Degilsin !";
		YetkinYok = StatikDegerler.ChatColorTranslateString(YetkinYok);
		sender.sendMessage(YetkinYok);
	}
	public boolean YetkisiYok(CommandSender sender,Permission perm){
		if(sender.hasPermission(perm) || sender.isOp()){
			return false;
		}else{
			YetkinYok(sender);
			return true;
		}
	}
	public void Komut_Help(CommandSender sender){
		if(YetkisiYok(sender, Permissions.getAntiHile_Komut_Yardim().getPerm())){
			return;
		}
		String[] HelpMsg = new String[]{
				"&c========= &dAntiHile Yardým &c=========",
				"&c=> &eAntihile &dVersion &2V" + StatikDegerler.Plugin.getDescription().getVersion() + " &c<=",
				"&c/antihile &aYardim &c=> &aAntiHile Yardým Menüsünü Açar.",
				"&c/antihile &aListele &c=> &aOyuncu Durumlarýný Gösterir.",
				"&c/antihile &aListele &bplayername &c=> &aBelirli Bir Oyuncunun Durumunu Gösterir.",
				"&c/antihile &aReload &c=> &aMesajlarý Yeniden Yükler",
				"&c/antihile &aizinver &bplayername &c=> &aOyuncuyu AntiHile Doðrulamasýndan &cTek Seferlik Geçirir.",
				"&c/antihile &aLisans &c=> &aLisans Bilginizi Gösterir.",
				"&c/antihile &aYetkiler &c=> &aYetkiler Gösterir."
		};
		HelpMsg = StatikDegerler.ChatColorTranslateString(HelpMsg);
		sender.sendMessage(HelpMsg);
	}
	public void Komut_Listele(CommandSender sender){
		if(YetkisiYok(sender, Permissions.getAntiHile_Komut_Listele().getPerm())){
			return;
		}
	ArrayList<String> PlayerList = ClientYonetici.GetList();
	String[] ListeMsg = new String[PlayerList.size() + 1];
	
	int i = 0;
	for (String string : PlayerList) {
		if(!AuthmeDinleyici.GirisYaptimi(string)){
			ListeMsg[i] = "&a"+string + " &c=> &c" + "Authme Giris Bekleniyor.";
		}else{
			ListeMsg[i] = "&a"+string + " &c=> &c" + "Client Dogrulamasi Bekleniyor.";
		}
		i++;
	}
	ListeMsg[i] = "&aToplam &c" + PlayerList.size() + " &aTane Oyuncu Listelendi.";
	ListeMsg = StatikDegerler.ChatColorTranslateString(ListeMsg);
	sender.sendMessage(ListeMsg);
	}
	public void Komut_Listele(CommandSender sender,String PlayerName){
		if(YetkisiYok(sender, Permissions.getAntiHile_Komut_Listele().getPerm())){
			return;
		}
		ArrayList<String> PlayerList = ClientYonetici.GetList();
		String Mesaj = "&aOyuncu &cBulunamadý.";
		for (String string : PlayerList) {
			if(string.equalsIgnoreCase(PlayerName)){
				if(AuthmeDinleyici.GirisYaptimi(string)){
				Mesaj = "&a"+string + " &c=> &c" + "Client Dogrulamasi Bekleniyor.";	
				}else{
				Mesaj = "&a"+string + " &c=> &c" + "Authme Giris Bekleniyor.";
				}
				break;
			}
		}
		Mesaj = StatikDegerler.ChatColorTranslateString(Mesaj);
		sender.sendMessage(Mesaj);
	}
	public void Komut_Reload(CommandSender sender){
		if(YetkisiYok(sender, Permissions.getAntiHile_Komut_Reload().getPerm())){
			return;
		}
		String[] Msg = new String[]{
			"&aMesajlar Ve Log Ayarlari Yeniden Yuklendi.",
			"&aBunun Dýþýndaki Deðiþiklikler Ýçin Server Bazlý Reload Gereklidir."
		};
		Msg = StatikDegerler.ChatColorTranslateString(Msg);
		sender.sendMessage(Msg);
		StatikDegerler.Plugin.reloadConfig();
	}
	public void Komut_Lisans(CommandSender sender){
		if(YetkisiYok(sender, Permissions.getAntiHile_Komut_Lisans().getPerm())){
			return;
		}
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				LisansData Lisans = new SiteSession(StatikDegerler.Uniqueid, StatikDegerler.Host).LisansDataKontrol();
				if(Lisans == null){
					String Mesaj = "&aLisans Bilgisi Çekilemiyor.";
					Mesaj = StatikDegerler.ChatColorTranslateString(Mesaj);
					sender.sendMessage(Mesaj);
				}else{
					String[] Mesaj = new String[]{
						"&cMusteri &e=> &a" +Lisans.getMusteri(),
						"&cSunucu &e=> &a" +Lisans.getSunucu(),
						"&cLisans Baþlangýc Tarihi &e=> &a" +Lisans.getLisansBaslangic(),
						"&cLisans Sonlanma Tarihi &e=> &a" +Lisans.getLisansSon(),
						"&cUygulama Linki &e=> &a" +Lisans.getUrl()
					};
					Mesaj = StatikDegerler.ChatColorTranslateString(Mesaj);
					sender.sendMessage(Mesaj);
				}
			}
		});
		t.start();
	}
	public void Komut_Permission(CommandSender sender){
		if(YetkisiYok(sender, Permissions.getAntiHile_Komut_Yetkiler().getPerm())){
			return;
		}
		String[] PermMsg = new String[Permissions.Permliste.size() + 1];
		int i = 0;
		for (PermissionAciklama string : Permissions.Permliste) {
			PermMsg[i] = "&a" + string.getPerm().getName() + " &e=> &a" + string.getAciklama();
			i++;
		}
		PermMsg[i] = "&aToplam &c" + Permissions.Permliste.size() + " &aTane Yetki Listeleniyor.";
		PermMsg = StatikDegerler.ChatColorTranslateString(PermMsg);
		sender.sendMessage(PermMsg);
	}
	public void Komut_izinver(CommandSender sender,String PlayerName){
		if(YetkisiYok(sender, Permissions.getAntiHile_Komut_izin().getPerm())){
			return;
		}
		ArrayList<String> PlayerList = ClientYonetici.GetList();
		String Mesaj = "&aOyuncu &cBulunamadý.";
		if(PlayerList.contains(PlayerName)){
			if(AuthmeDinleyici.GirisYaptimi(PlayerName)){
				ClientYonetici.PlayerIzinver(PlayerName);
				Mesaj = "&c"+ PlayerName + "&a Adlý Oyuncu Doðrulamadan Baþarýyla Geçti.";
			}else{
				Mesaj = "&aOyuncunun Giriþ Yapmasý Gerekiyor.";
			}
		}
		Mesaj = StatikDegerler.ChatColorTranslateString(Mesaj);
		sender.sendMessage(Mesaj);
	}
}
