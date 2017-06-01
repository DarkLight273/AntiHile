/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tr.NW09.antihilepublic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import tr.NW09.antihilepublic.Site.Sorgu;

/**
 *
 * @author Userxxx
 */
public class ArkaPlanislemci implements Runnable {

    private Gui gui;

    public ArkaPlanislemci(Gui gui) {
        this.gui = gui;
    }

    @Override
    public void run() {
        try {
            gui.AdimBasla(Adimlar.VersionKontrol);
            if(!clientguncelmi()){
                AdımHata(Adimlar.VersionKontrol);
                return;
            }else{
                AdimGec(Adimlar.VersionKontrol);
            }
            AdimBasla(Adimlar.Adim1);
            Log(LogTur.BILGI, "Bilgisayar Üzerindeki Proccesler Inceleniyor...");
            List<ClientData> vmlistesi = ClientBul();
            if (!vmlistesi.isEmpty()) {
                Log(LogTur.DEBUG, "Bilgisayar Üzerinde " + vmlistesi.size() + " Java Procces Bulundu");
                ClientLogla(vmlistesi);
                AdimGec(Adimlar.Adim1);
                Log(LogTur.BILGI, "Veriler Ayıklanıyor...");
                AdimBasla(Adimlar.Adim2);
                List<ClientData> ClientListesi = ParametreAyikla(vmlistesi);
                if (!ClientListesi.isEmpty()) {
                    Log(LogTur.DEBUG, ClientListesi.size() + " Tane Minecraft ClientData Islendi");
                    ClientLogla(ClientListesi);
                    AdimGec(Adimlar.Adim2);
                    Log(LogTur.BILGI, "Baglantılar Kontrol Ediliyor...");
                    AdimBasla(Adimlar.Adim3);
                    List<ClientData> NetworkClientlist = BaglantiBul(ClientListesi);
                    if (!NetworkClientlist.isEmpty()) {
                        Log(LogTur.DEBUG, NetworkClientlist.size() + " Tane Internete Baglı Client Bulundu.");
                        ClientLogla(NetworkClientlist);
                        AdimGec(Adimlar.Adim3);
                        Log(LogTur.BILGI, "Calisan Clientler Inceleniyor...");
                        AdimBasla(Adimlar.Adim4);
                        List<ClientData> Md5ChecksumList = MD5AL(NetworkClientlist);
                        if (!Md5ChecksumList.isEmpty()) {
                            Log(LogTur.DEBUG, Md5ChecksumList.size() + " Tane Client Checksum Alindi.");
                            ClientLogla(Md5ChecksumList);
                            AdimGec(Adimlar.Adim4);
                            Log(LogTur.BILGI, "Sunucu Ile Iletisime Geciliyor.");
                            AdimBasla(Adimlar.Adim5);
                            SunucuyaGonder(Md5ChecksumList);

                        } else {
                            Log(LogTur.HATA, "Checksum Alinirken Hata.");
                            Log(LogTur.DEBUG, "Checksum Alinamadi.");
                            ClientLogla(Md5ChecksumList);
                            AdımHata(Adimlar.Adim4);
                        }
                    } else {
                        Log(LogTur.HATA, "Herhangi Bir Sunucuya Bagli Client Bulunamadi...");
                        Log(LogTur.DEBUG, "Internete Baglı Client Bulunamadi.");
                        ClientLogla(NetworkClientlist);
                        AdımHata(Adimlar.Adim3);
                    }
                } else {
                    Log(LogTur.HATA, "Açık Client Bulunamadi...");
                    Log(LogTur.DEBUG, "Hiçbir Client Datası İşlenmedi");
                    ClientLogla(ClientListesi);
                    AdımHata(Adimlar.Adim2);
                }
            } else {
                Log(LogTur.HATA, "Açık Islem Bulunamadi...");
                Log(LogTur.DEBUG, "Bilgisayar Üzerinde Çalışan Java Procces Bulunamadı !");
                ClientLogla(vmlistesi);
                AdımHata(Adimlar.Adim1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Log(LogTur Tur, String Message) {
        gui.LogAl(Tur, Message);
    }

    public void AdimBasla(Adimlar adim) {
        gui.AdimBasla(adim);
    }

    public void AdimGec(Adimlar adim) {
        gui.AdimGec(adim);
    }

    public void AdımHata(Adimlar adim) {
        gui.AdımHata(adim);
    }

    public List<ClientData> ClientBul() throws Exception {
        List<ClientData> donecekliste = new ArrayList<ClientData>(); //
        Runtime rt = Runtime.getRuntime();
        String commands = "cmd.exe";
        String Forcommand = "for /f \"tokens=2\" %a in ('tasklist ^| find /I /N \"javaw.exe\"') do echo %a";
        Process proc = rt.exec(commands);
        OutputStream out = proc.getOutputStream();
        out.write(("@echo off"+System.lineSeparator()).getBytes());
        out.flush();
        out.write((Forcommand + System.lineSeparator()).getBytes());
        out.flush();
        out.write(("exit" + System.lineSeparator()).getBytes());
        out.flush();
        InputStream stdin = proc.getInputStream();
        InputStreamReader isr = new InputStreamReader(stdin);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            if (Sayimi(line)) {
                ClientData client = new ClientData();
                client.setProccesID(line);
                donecekliste.add(client);
            }
        }
        return donecekliste;
    }

    /*public List<ClientData> ClientBul() {
        List<ClientData> vmlist = new ArrayList<ClientData>();
        List<VirtualMachineDescriptor> vm = VirtualMachine.list();
        for (VirtualMachineDescriptor a : vm) {
            try {
                String displayname = a.displayName();
                if (displayname.contains("--version") && displayname.contains("--username")) {
                    ClientData Client = new ClientData();
                    Client.setProccesID(a.id());
                    vmlist.add(Client);
                }
            } catch (Exception ex) {

            }
        }
        return vmlist;
    }*/
    public List<ClientData> ParametreAyikla(List<ClientData> vmlist) throws Exception { //
        List<ClientData> donecekliste = new ArrayList<ClientData>();
        for (ClientData clientData : vmlist) {
            Runtime rt = Runtime.getRuntime();
            String commands = "cmd.exe";
            String Forcommand = "wmic process where(ProcessId=\"" + clientData.getProccesID() + "\") get commandline";
            Process proc = rt.exec(commands);
            OutputStream out = proc.getOutputStream();
            out.write(("@echo off" + System.lineSeparator()).getBytes());
            out.flush();
            out.write((Forcommand + System.lineSeparator()).getBytes());
            out.flush();
            out.write(("exit"+ System.lineSeparator()).getBytes());
            out.flush();
            InputStream stdin = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.contains("--username") && line.contains("--version") && line.contains("-cp")) {
                    //username
                    String UserName = line.split("--username")[1].split(" --")[0].trim().replaceAll("\"", "");
                    clientData.setUserName(UserName);
                    //Version
                    String Version = line.split("--version")[1].split(" --")[0].trim().replaceAll("\"", "");;
                    clientData.setVersionName(Version);
                    //jarlocation
                    String Cp = line.split("-cp")[1].split(" --")[0].trim().split(" ")[0];
                    String[] JarLocation = Cp.split(";");
                    //Alternative Jar Location
                    for (String string : JarLocation) {
                        if (string.trim().endsWith(clientData.getVersionName() + ".jar")) {
                            clientData.setLibrary(string);
                            clientData.setEskiYontemJar(false);
                            break;
                        }
                    }
                    if (clientData.getLibrary() == null) {
                        String LibraryLocation = System.getenv("APPDATA") + "\\.minecraft\\versions\\" + clientData.getVersionName() + "\\" + clientData.getVersionName() + ".jar";
                        clientData.setLibrary(LibraryLocation);
                        clientData.setEskiYontemJar(true);
                    }
                    //package
                    String Package = line.split("-cp")[1].split(" --")[0].trim().split(" ")[1];
                    if(!clientData.getEskiYontemJar()){
                    clientData.setPackageName(Package);    
                    }else{
                        
                    }
                    donecekliste.add(clientData);
                }

            }
        }
        return donecekliste;
    }

    /*public List<ClientData> ParametreAyikla(List<ClientData> vmlist) throws Exception {
        List<ClientData> Clientlist = new ArrayList<ClientData>();
        for (ClientData Client: vmlist) {
            VirtualMachine virtualMachine = VirtualMachine.attach(Client.getProccesID());
            Client.setProccesID(virtualMachine.id());
            Properties prop = virtualMachine.getSystemProperties();
            /*for (Object key  : prop.keySet()) {
                gui.LogAl(LogTur.DEBUG, key+"<=>"+prop.get(key));
            }
            String CommandLine = prop.getProperty("sun.java.command");
            String Library = prop.getProperty("java.class.path");
            //CommandLine Ayıklama
            //username
            String UserName = CommandLine.split("--username")[1].split(" --")[0].trim();
            Client.setUserName(UserName);
            //version
            String Version = CommandLine.split("--version")[1].split(" --")[0].trim();
            Client.setVersionName(Version);
            String[] JarLocation = Library.split(";");
            //Alternative Jar Location
            for (String string : JarLocation) {
                if (string.trim().endsWith(Client.getVersionName() + ".jar")) {
                    Client.setLibrary(string);
                    Client.setEskiYontemJar(false);
                    break;
                }
            }
            if (Client.getLibrary() == null) {
                String LibraryLocation = System.getenv("APPDATA") + "\\.minecraft\\versions\\" + Client.getVersionName() + "\\" + Client.getVersionName() + ".jar";
                Client.setLibrary(LibraryLocation);
                Client.setEskiYontemJar(true);
            }
            //Packages
            String Package = CommandLine.split(" ")[0];
            Client.setPackageName(Package);
            Clientlist.add(Client);
        }
        return Clientlist;
    }*/
    public List<ClientData> BaglantiBul(List<ClientData> oldlist) throws Exception {
        List<ClientData> newlist = new ArrayList<ClientData>();
        for (ClientData clientData : oldlist) {
            Netstat(clientData);
            if (clientData.getSunucu_ip() != null && clientData.getSunucu_port() != null) {
                newlist.add(clientData);
            }
        }
        return newlist;
    }

    public void Netstat(ClientData client) throws Exception {
        Runtime rt = Runtime.getRuntime();
        String commands = "cmd.exe";
        String Forcommand = "for /f \"tokens=3,4,5\" %a in ('netstat -ano') do IF \"%c\"==\"" + client.getProccesID() + "\" echo %a;%b;%c)";
        Process proc = rt.exec(commands);
        OutputStream out = proc.getOutputStream();
        out.write(("@echo off"+ System.lineSeparator()).getBytes());
        out.flush();
        out.write((Forcommand + System.lineSeparator()).getBytes());
        out.flush();
        out.write(("exit"+ System.lineSeparator()).getBytes());
        out.flush();
        InputStream stdin = proc.getInputStream();
        InputStreamReader isr = new InputStreamReader(stdin);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            if (line.split(";").length == 3 && !line.contains("echo %a;%b;%c")) {
                if (!(line.split(";")[0].trim().startsWith("127.0.0.1") && line.split(";")[1].trim().equalsIgnoreCase("ESTABLISHED"))) {
                    client.setSunucu_ip(line.split(";")[0].split(":")[0].trim());
                }
                client.setSunucu_port(line.split(";")[0].split(":")[1].trim());
            }

        }
    }

    public List<ClientData> MD5AL(List<ClientData> list) {
        List<ClientData> donecekliste = new ArrayList<ClientData>();
        for (ClientData clientData : list) {
            try {
                clientData.setJarMD5(MD5(clientData.getLibrary()));
                if (clientData.getJarMD5() != null) {
                    donecekliste.add(clientData);
                }
            } catch (Exception ex) {
                Log(LogTur.DEBUG, "Client Algoritma Hesaplanırken Hata =>" + clientData.getLibrary());
                ex.printStackTrace();
            }
        }
        return donecekliste;
    }

    public void SunucuyaGonder(List<ClientData> list) {
        boolean ab = false;
        for (ClientData clientData : list) {
            ab = new Sorgu(Gui.Domain + "/Client_Sorgu.php", gui).ClientIstek(clientData.getUserName(), clientData.getPackageName(), clientData.getVersionName(), clientData.getJarMD5(), clientData.getSunucu_ip(), clientData.getSunucu_port());
        }
        if (!ab) {
            AdımHata(Adimlar.Adim5);
        } else {
            AdimGec(Adimlar.Adim5);
        }
    }

    public boolean Sayimi(String line) {
        try {
            Integer.parseInt(line);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String MD5(String File) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        FileInputStream fis = new FileInputStream(File);

        byte[] dataBytes = new byte[1024];

        int nread = 0;
        while ((nread = fis.read(dataBytes)) != -1) {
            md.update(dataBytes, 0, nread);
        };
        byte[] mdbytes = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < mdbytes.length; i++) {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString().toUpperCase();
    }
public boolean clientguncelmi(){
    Sorgu sorgu = new Sorgu(Gui.Domain + "/version.php", gui);
    return sorgu.SurumGuncelmi();
}
    public void ClientLogla(List<ClientData> list) {
        for (ClientData clientData : list) {
            Log(LogTur.DEBUG, clientData.toString());
        }
    }
}
