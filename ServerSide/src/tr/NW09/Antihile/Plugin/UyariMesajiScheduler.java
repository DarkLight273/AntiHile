package tr.NW09.Antihile.Plugin;

public class UyariMesajiScheduler implements Runnable{

	@Override
	public void run() {
		for (String PlayerName : ClientYonetici.GetList()) {
			Clientislemleri.UyariMesaji(PlayerName);
		}
		
	}

}
