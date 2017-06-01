/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.NW09.antihilepublic.Berti;

/**
 *
 * @author Halil
 */
public class BertiKey {
    private int Sira;
    private int Key;
    private String GuvenlikKodu;

    public BertiKey(int Sira, int Key, String GuvenlikKodu) {
        this.Sira = Sira;
        this.Key = Key;
        this.GuvenlikKodu = GuvenlikKodu;
    }

    public int getSira() {
        return Sira;
    }

    public int getKey() {
        return Key;
    }

    public String getGuvenlikKodu() {
        return GuvenlikKodu;
    }

    @Override
    public String toString() {
        return "BertiKey{" + "Sira=" + Sira + ", Key=" + Key + ", GuvenlikKodu=" + GuvenlikKodu + '}';
    }
    
}
