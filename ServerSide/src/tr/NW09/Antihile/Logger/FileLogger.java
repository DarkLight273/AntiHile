package tr.NW09.Antihile.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.plugin.java.JavaPlugin;

public class FileLogger implements LoggerClass {
	private File Dosya;
	JavaPlugin Plugin;
	private boolean durum = true;

	public FileLogger(JavaPlugin Plugin) {
		this.Plugin = Plugin;
		File Path = new File(Plugin.getDataFolder().getPath() + "\\LOG");
		if (!Path.exists()) {
			if (!Path.mkdir()) {
				durum = false;
			}
		}
		Dosya = new File(Path, "log-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".txt");
		if (!Dosya.exists()) {
			try {
				Dosya.createNewFile();
			} catch (IOException e) {
				durum = false;
			}
		}
	}

	@Override
	public void LOG(LogTur Sinif, String Mesaj, Exception e) {
		String Date = "[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]";
		switch (Sinif) {
		case Bilgi:
			Append(Date + "[BILGI] " + Mesaj, e);
			break;
		case Hata:
			Append(Date + "[HATA] " + Mesaj, e);
			break;
		case Uyari:
			Append(Date + "[UYARI] " + Mesaj, e);
			break;
		}
	}

	@Override
	public void LOG(LogTur Sinif, String Mesaj) {
		String Date = "[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "]";
		switch (Sinif) {
		case Bilgi:
			Append(Date + "[BILGI] " + Mesaj);
			break;
		case Hata:
			Append(Date + "[HATA] " + Mesaj);
			break;
		case Uyari:
			Append(Date + "[UYARI] " + Mesaj);
			break;
		}
	}

	private void Append(String Mesaj, Exception e) {
		Append(Mesaj);
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		Append(errors.toString());
	}

	private void Append(String Mesaj) {
		if (!durum) {
			return;
		}
		try (FileWriter fw = new FileWriter(Dosya, true);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter out = new PrintWriter(bw)) {
			out.println(Mesaj);
			out.close();
			bw.close();
			fw.close();
		} catch (IOException e) {
			Logger.LOG(LogTur.Hata, "Append Fonksiyonunda Hata", false,e);
		}
	}
}
