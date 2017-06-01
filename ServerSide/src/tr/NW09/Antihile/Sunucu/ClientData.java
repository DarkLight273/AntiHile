package tr.NW09.Antihile.Sunucu;

public class ClientData {
	private String KullaniciAdi;
	private String ClientName;
	private Boolean IzinVer;
	public ClientData(String kullaniciAdi, String clientName, Boolean izinVer) {
		this.KullaniciAdi = kullaniciAdi;
		this.ClientName = clientName;
		this.IzinVer = izinVer;
	}
	public String getKullaniciAdi() {
		return KullaniciAdi;
	}
	public String getClientName() {
		return ClientName;
	}
	public Boolean getIzinVer() {
		return IzinVer;
	}
	@Override
	public String toString() {
		return "ClientData [KullaniciAdi=" + KullaniciAdi + ", ClientName=" + ClientName + ", IzinVer=" + IzinVer + "]";
	}
	
	
}
