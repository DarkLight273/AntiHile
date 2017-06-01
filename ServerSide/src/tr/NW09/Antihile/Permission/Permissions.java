package tr.NW09.Antihile.Permission;

import java.util.ArrayList;

import org.bukkit.permissions.Permission;

public class Permissions {
	public static ArrayList<PermissionAciklama> Permliste = new ArrayList<PermissionAciklama>();
	private static PermissionAciklama AntiHile_Komut_Yardim = new PermissionAciklama(new Permission("AntiHile.Komut.Yardim"),"/antihile yardim komutu için gerekli yetki");
	private static PermissionAciklama AntiHile_Komut_Listele = new PermissionAciklama(new Permission("AntiHile.Komut.Listele"),"/antihile listele komutu için gerekli yetki");
	private static PermissionAciklama AntiHile_Komut_Reload = new PermissionAciklama(new Permission("AntiHile.Komut.Reload"),"/antihile reload komutu için gerekli yetki");
	private static PermissionAciklama AntiHile_Komut_izin = new PermissionAciklama(new Permission("AntiHile.Komut.izin"),"/antihile izinver komutu için gerekli yetki");
	private static PermissionAciklama AntiHile_Komut_Lisans = new PermissionAciklama(new Permission("AntiHile.Komut.Lisans"),"/antihile komut komutu için gerekli yetki");
	private static PermissionAciklama AntiHile_Komut_Yetkiler = new PermissionAciklama(new Permission("AntiHile.Komut.Yetkiler"),"/antihile yetkiler komutu için gerekli yetki");
	private static PermissionAciklama AntiHile_Bypass = new PermissionAciklama(new Permission("AntiHile.Bypass"),"AntiHile Doðrulamasýndan Etkilenmemek için gereken yetki");
	public static PermissionAciklama getAntiHile_Komut_Yardim() {
		return AntiHile_Komut_Yardim;
	}
	public static PermissionAciklama getAntiHile_Komut_Listele() {
		return AntiHile_Komut_Listele;
	}
	public static PermissionAciklama getAntiHile_Komut_Reload() {
		return AntiHile_Komut_Reload;
	}
	public static PermissionAciklama getAntiHile_Komut_izin() {
		return AntiHile_Komut_izin;
	}
	public static PermissionAciklama getAntiHile_Komut_Lisans() {
		return AntiHile_Komut_Lisans;
	}
	public static PermissionAciklama getAntiHile_Komut_Yetkiler() {
		return AntiHile_Komut_Yetkiler;
	}
	public static PermissionAciklama getAntiHile_Bypass() {
		return AntiHile_Bypass;
	}
	
}
