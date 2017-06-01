package tr.NW09.antihilepublic.Site;

import com.google.gson.JsonObject;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import tr.NW09.antihilepublic.Berti.BertiKey;
import tr.NW09.antihilepublic.Berti.BertiYontemi;
import tr.NW09.antihilepublic.Cevap;
import tr.NW09.antihilepublic.Gui;
import tr.NW09.antihilepublic.LogTur;

public class Sorgu {

    String Site;
    Gui gui;

    public Sorgu(String Site, Gui gui) {
        this.Site = Site;
        this.gui = gui;
    }
    public boolean SurumGuncelmi() {
        SiteSession sorgu = new SiteSession(Site);
        try {
            JsonObject js = sorgu.run();
            if (js.get("version").getAsString().equalsIgnoreCase(gui.Version)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean ClientIstek(String Name, String Package, String Version, String Md5, String IP, String Port) {         try { //hkhk:main:1.7.2:21214124:37.157.255.157:25565
        BertiKey BertiKeyim = new Sorgu(Gui.Domain + "/Client_Key.php",gui).KeyAl();
        SiteSession sorgu = new SiteSession(Site);
        sorgu.ParametreEkle("islem", "yeni");
        sorgu.ParametreEkle("Sira",BertiKeyim.getSira() + "");
        sorgu.ParametreEkle("GuvenlikKodu", BertiKeyim.getGuvenlikKodu() + "");
        String Data = Name + ":" + Package + ":" + Version + ":" + Md5 + ":" + IP + ":" + Port;
        gui.LogAl(LogTur.DEBUG, "Sifresiz Data => " + Data);
        gui.LogAl(LogTur.DEBUG, "BertiKeyi => " + BertiKeyim.toString());
        Data = BertiYontemi.encryptWithBertiYontemi(BertiKeyim.getKey(), Data);
        gui.LogAl(LogTur.DEBUG, "Sifreli Data => " + Data);
        sorgu.ParametreEkle("data", URLEncoder.encode(Data,"UTF-8"));
        try {
            JsonObject obj = sorgu.run();
            if (obj.get("Hata").getAsBoolean()) {
                gui.LogAl(LogTur.DEBUG, "Sunucudan Gelen Mesaj=>" + obj.get("Mesaj").getAsString());
                gui.LogAl(LogTur.HATA, obj.get("Mesaj").getAsString());
                return false;
            } else {
                String Sunucu = obj.get("Sunucu").getAsString();
                int Time = obj.get("Time").getAsInt();
                return true;
            }
        } catch (Exception ex) {
            gui.LogAl(LogTur.HATA, "Siteyle Baglanti Hatasi.");
            StringWriter errors = new StringWriter();
            ex.printStackTrace(new PrintWriter(errors));
            gui.LogAl(LogTur.DEBUG, errors.toString());
            return false;
        }
    }   catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Sorgu.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public BertiKey KeyAl(){
        try {
            SiteSession sorgu = new SiteSession(Site);
            JsonObject obj = sorgu.run();
             if (obj.get("Hata").getAsBoolean()) {
                gui.LogAl(LogTur.DEBUG, "Sunucudan Gelen Mesaj=>" + obj.get("Mesaj").getAsString());
                gui.LogAl(LogTur.HATA, obj.get("Mesaj").getAsString());
                return null;
            }else{
                 return new BertiKey(obj.get("Sira").getAsInt(), obj.get("Anahtar").getAsInt(), obj.get("GuvenlikKodu").getAsString());
             }
           
        } catch (Exception ex) {
            Logger.getLogger(Sorgu.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }
}
