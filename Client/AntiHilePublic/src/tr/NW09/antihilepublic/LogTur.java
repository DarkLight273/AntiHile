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
public enum LogTur {
    HATA("[HATA]"), BILGI("[BILGI]"), DEBUG("[DEBUG]");
    private String OnEk;

    private LogTur(String OnEk) {
        this.OnEk = OnEk;
    }
    public String OnEk(){
        return OnEk;
    }
    
}
