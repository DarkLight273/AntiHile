package tr.NW09.Antihile.Permission;

import org.bukkit.permissions.Permission;

public class PermissionAciklama {
	private Permission perm;
	private String Aciklama;
	public PermissionAciklama(Permission perm, String aciklama) {
		this.perm = perm;
		Aciklama = aciklama;
		Permissions.Permliste.add(this);
	}
	public Permission getPerm() {
		return perm;
	}
	public String getAciklama() {
		return Aciklama;
	}
}
