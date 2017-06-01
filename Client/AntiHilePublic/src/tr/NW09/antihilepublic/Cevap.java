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
public class Cevap {
    private boolean Hata;
    private String HataMesaji;
    private String Sunucu;
    private int Zaman;

    public boolean isHata() {
        return Hata;
    }

    public String getHataMesaji() {
        return HataMesaji;
    }

    public String getSunucu() {
        return Sunucu;
    }

    public int getZaman() {
        return Zaman;
    }

    public void setHata(boolean Hata) {
        this.Hata = Hata;
    }

    public void setHataMesaji(String HataMesaji) {
        this.HataMesaji = HataMesaji;
    }

    public void setSunucu(String Sunucu) {
        this.Sunucu = Sunucu;
    }

    public void setZaman(int Zaman) {
        this.Zaman = Zaman;
    }
    
}
