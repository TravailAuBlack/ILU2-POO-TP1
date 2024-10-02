package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import villagegaulois.Etal;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		Marche marche = new Marche(nbEtals);
		
	}
	
	// classe interne 
	
	private static class Marche{
		private Etal[] etals;
		private int nbEtals;
		
		
		private Marche(int nbEtalsMax) {
			this.nbEtals = nbEtalsMax;
			etals = new Etal[nbEtalsMax];
			for(int i =0; i<= nbEtalsMax; i++) {
				etals[i] = new Etal();
			}
		}
		
		private void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit); 
			 
		}
		
		private int trouverEtalLibre() {
			for(int i=0; i<=nbEtals;i++) {
				if(etals[i].isEtalOccupe())
					return -1;
			}
			return -1;
		}
		
		private Etal[] trouverEtals(String produit) {
			int nbProdEtal =0;
			for(Etal etal : etals) {
				if(etal.isEtalOccupe()) {
					if(etal.contientProduit(produit)) 
						nbProdEtal++;
				}	
			}
			Etal[] produitEtal = new Etal[nbProdEtal];
			for(int i = 0; i<=nbEtals; i++) {
				if (etals[i].contientProduit(produit))
					produitEtal[i] = etals[i];
			}
			return produitEtal;
			
		}
		
		
		private Etal trouverVendeur(Gaulois gaulois) {
			int nbEtal =0;
			for(Etal etal : etals) {
				if(etal.isEtalOccupe()) {
						nbEtal++;
				}
			}
			Etal[] trouverEtal = new Etal[nbEtal];
			for(int i = 0; i<=nbEtals; i++) {
				if(trouverEtal[i].getVendeur().equals(gaulois))
					return trouverEtal[i];
			}
			return null;
			
		}
		
		
		private String afficherMarche() {
			int nbEtalOccupe = 0;
			int nbEtalLibre = 0;
			for(int i=0; i<= nbEtals; i++) {
				if(etals[i].isEtalOccupe())
					nbEtalOccupe++;
				nbEtalLibre++;
			}
			return "Il reste " + nbEtalLibre + " étals non utilisés dans le marché.\n";

		}
		
	}
	// fin classe interne 

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
}