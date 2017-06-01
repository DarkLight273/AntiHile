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
public class ClientData {

    private String ProccesID = null;
    private String PackageName = null;
    private String VersionName = null;
    private String UserName = null;
    private String Sunucu_ip = null;
    private String Sunucu_port = null;
    private String Library = null;
    private Boolean EskiYontemJar = null;
    private String JarMD5 = null;

    public String getLibrary() {
        return Library;
    }

    public String getPackageName() {
        return PackageName;
    }

    public String getSunucu_ip() {
        return Sunucu_ip;
    }

    public String getSunucu_port() {
        return Sunucu_port;
    }

    public String getUserName() {
        return UserName;
    }

    public String getVersionName() {
        return VersionName;
    }

    public String getProccesID() {
        return ProccesID;
    }

    public Boolean getEskiYontemJar() {
        return EskiYontemJar;
    }

    public String getJarMD5() {
        return JarMD5;
    }

    public void setLibrary(String Library) {
        this.Library = Library;
    }

    public void setPackageName(String PackageName) {
        this.PackageName = PackageName;
    }

    public void setSunucu_ip(String Sunucu_ip) {
        this.Sunucu_ip = Sunucu_ip;
    }

    public void setSunucu_port(String Sunucu_port) {
        this.Sunucu_port = Sunucu_port;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setVersionName(String VersionName) {
        this.VersionName = VersionName;
    }

    public void setProccesID(String ProccesID) {
        this.ProccesID = ProccesID;
    }

    public void setEskiYontemJar(Boolean EskiYontemJar) {
        this.EskiYontemJar = EskiYontemJar;
    }

    public void setJarMD5(String JarMD5) {
        this.JarMD5 = JarMD5;
    }

    @Override
    public String toString() {
        return "ClientData{" + "ProccesID=" + ProccesID + ", PackageName=" + PackageName + ", VersionName=" + VersionName + ", UserName=" + UserName + ", Sunucu_ip=" + Sunucu_ip + ", Sunucu_port=" + Sunucu_port + ", Library=" + Library + ", EskiYontemJar=" + EskiYontemJar + ", MD5Checksum=" + JarMD5+ '}';
    }

}
