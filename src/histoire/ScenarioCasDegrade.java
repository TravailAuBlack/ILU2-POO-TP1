package histoire;

import villagegaulois.Etal;

public class ScenarioCasDegrade {
	
	public static void main(String[] args) throws IllegalArgumentException{
		try {
			Etal etal = new Etal();
			etal.acheterProduit(10, null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Fin du test");
		}
	}


}
