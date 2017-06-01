package tr.NW09.Antihile.Sunucu;

public class LisansData {
	private String Musteri;
	private String Sunucu;
	private String LisansBaslangic;
	private String LisansSon;
	private String Url;
	public LisansData(String musteri, String sunucu, String lisansBaslangic, String lisansSon, String url) {
		Musteri = musteri;
		Sunucu = sunucu;
		LisansBaslangic = lisansBaslangic;
		LisansSon = lisansSon;
		Url = url;
	}
	public String getMusteri() {
		return Musteri;
	}
	public String getSunucu() {
		return Sunucu;
	}
	public String getLisansBaslangic() {
		return LisansBaslangic;
	}
	public String getLisansSon() {
		return LisansSon;
	}
	public String getUrl() {
		return Url;
	}
	
}
