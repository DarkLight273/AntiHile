/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.NW09.antihilepublic;

/**
 *
 * @author Userxxx
 */
public enum Adimlar {
    VersionKontrol("Versiyon Kontrol Ediliyor...","Versiyon Güncel..",new String[]{"Anti-Hileyi Güncelleyin","Sunucu Yöneticinizle Veya Kurucuyla Iletisime Gecin."}),
    Adim1("Islemler Inceleniyor...","Islemler Incelendi.",new String[]{"Programın Yeterli Izınlere Sahip Oldugundan Emin Olun.","Yonetici Olarak Calistirin."}),
    Adim2("Veriler Ayiklaniyor...","Veriler Ayiklandi.",new String[]{"Minecraft Clientinin Acik Oldugundan Emin Olun."}),
    Adim3("Baglantilar Kontrol Ediliyor...","Baglantilar Kontrol Edildi.",new String[]{"Herhangi Bir Sunucuya Baglı Oldugunuzdan Emin Olun.","Tekrar Deneyin.","Yonetici Olarak Calistirin."}), 
    Adim4("Client Inceleniyor...","Client Incelendi.",new String[]{"Yonetici Olarak Calistirin."}), 
    Adim5("Sunucu Ile Iletisime Geciliyor...","Sunucu Ile Iletisime Gecildi.",new String[]{"Internet Baglantinizi Kontrol Edin","Guvenlik Duvarınızı Kontrol Edin.","Hata Bircok Kiside Varsa Sunucu Adminine Veya Kurucusuna Ekran Görüntüsü Ile Durumu Bildirin.","Yonetici Olarak Calistirin."});
    private String once;
    private String sonra;
    private String[] hint;

    private Adimlar(String once, String sonra, String[] hint) {
        this.once = once;
        this.sonra = sonra;
        this.hint = hint;
    }

    public String getOnce() {
        return once;
    }

    public String getSonra() {
        return sonra;
    }

    public String getHint(String Hata) {
        String hinta = "";
        for (String as : hint) {
            hinta += as + System.lineSeparator();
        }
        hinta += "Hata=>" + Hata;
        return hinta;
    }

}
