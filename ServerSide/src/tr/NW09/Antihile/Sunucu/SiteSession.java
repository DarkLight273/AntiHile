package tr.NW09.Antihile.Sunucu;

import com.google.gson.JsonObject;
import tr.NW09.Antihile.Logger.LogTur;
import tr.NW09.Antihile.Logger.Logger;
import tr.NW09.Antihile.Plugin.StatikDegerler;

public class SiteSession {
	private String Site;
	private String UniqueID;

	public SiteSession(String UNIQUEID, String Site) {
		this.UniqueID = UNIQUEID;
		this.Site = Site;
	}

	public Boolean LisansKontrol() {
		Sorgu LisanSorgusu = new Sorgu(Site);
		LisanSorgusu.ParametreEkle("islem", "bXpjZ0Q0U0ZKemJHbHpZVzV6YTI5dWRISnZiQT09UG1KNWE3SUFjYg==");
		LisanSorgusu.ParametreEkle("lisans", UniqueID);
		try {
			JsonObject Cevapjson = LisanSorgusu.run();
			if (Cevapjson.get("Hata").getAsBoolean()) {
				Logger.LOG(LogTur.Uyari,Cevapjson.get("Mesaj").getAsString(),
						Cevapjson.get("Critical_Error").getAsBoolean());
				return !Cevapjson.get("Critical_Error").getAsBoolean();
			} else {
				Logger.LOG(LogTur.Bilgi,"--Anti Hile Lisans Bilgiler--",false);
				Logger.LOG(LogTur.Bilgi,"Musteri=>" + Cevapjson.get("Musteri").getAsString(),false);
				Logger.LOG(LogTur.Bilgi,"SUNUCU=>" + Cevapjson.get("SUNUCU").getAsString(),false);
				Logger.LOG(LogTur.Bilgi,"Lisans Baslangic Tarihi=>"
						+ StatikDegerler.TimeStampToDate(Cevapjson.get("START_TIME").getAsLong()),false);
				Logger.LOG(LogTur.Bilgi,"Lisans Sonlanma Tarihi=>"
						+ StatikDegerler.TimeStampToDate(Cevapjson.get("END_TIME").getAsLong()),false);
				Logger.LOG(LogTur.Bilgi, "Uygulama Link=>" + Cevapjson.get("Uygulama_URL").getAsString(), false);
				Logger.LOG(LogTur.Bilgi,"--Anti Hile Lisans Bilgiler--",false);
				if(Cevapjson.get("Uygulama_URL").getAsString() != null){
					StatikDegerler.Uygulama_Linki = Cevapjson.get("Uygulama_URL").getAsString();
				}
				return true;
			}
		} catch (Exception e) {
			Logger.LOG(LogTur.Hata,"Lisans Kontrolu icin AnaSunucuyla Siteyle Iletisim Kurulamiyor", true,e);
			return false;
		}
	}
	public LisansData LisansDataKontrol(){
		Sorgu LisanSorgusu = new Sorgu(Site);
		LisanSorgusu.ParametreEkle("islem", "bXpjZ0Q0U0ZKemJHbHpZVzV6YTI5dWRISnZiQT09UG1KNWE3SUFjYg==");
		LisanSorgusu.ParametreEkle("lisans", UniqueID);
		try {
			JsonObject Cevapjson = LisanSorgusu.run();
			if (Cevapjson.get("Hata").getAsBoolean()) {
				return null;
			}else{
				if(Cevapjson.get("Uygulama_URL").getAsString() != null){
					StatikDegerler.Uygulama_Linki = Cevapjson.get("Uygulama_URL").getAsString();
				}
				return new LisansData(Cevapjson.get("Musteri").getAsString(), Cevapjson.get("SUNUCU").getAsString(), StatikDegerler.TimeStampToDate(Cevapjson.get("START_TIME").getAsLong()), StatikDegerler.TimeStampToDate(Cevapjson.get("END_TIME").getAsLong()), Cevapjson.get("Uygulama_URL").getAsString());
			}
		} catch (Exception e) {
			return null;
		}
	}
	public ClientData ClientIste(String Username,String IP){
		Sorgu ClientSorgusu = new Sorgu(Site);
		ClientSorgusu.ParametreEkle("islem", "YmFpNnhPQ0RKelkyeHBaVzUwN0c0YW00VTcwQg==");
		ClientSorgusu.ParametreEkle("lisans", UniqueID);
		ClientSorgusu.ParametreEkle("PlayerName", Username);
		ClientSorgusu.ParametreEkle("PlayerIP", IP);
		try{
			JsonObject Cevap = ClientSorgusu.run();
			if(Cevap.get("Hata").getAsBoolean()){
				return new ClientData(null, null, null);
			}else{
				return new ClientData(Cevap.get("PlayerName").getAsString(), Cevap.get("Client").getAsString(), Cevap.get("Durum").getAsBoolean());
			}
		} catch (Exception e) {
			Logger.LOG(LogTur.Hata,"AnaSunucuyla Iletisim Kurulamiyor", false,e);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				
			}
			return new ClientData(null, null, null);
		}
	}
}