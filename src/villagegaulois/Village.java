package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
		
	}
	
	// classe interne 
	
	private static class Marche{
		private Etal[] etals;
		private int nbEtals;
		
		
		 public Marche(int nbEtals){
			 etals = new Etal[nbEtals];
		     for (int i = 0; i < etals.length; i++) {
		    	 etals[i] = new Etal(); 
		    }
		 }
		
		public void utiliserEtal(int indiceEtal, Gaulois vendeur, String produit, int nbProduit) {
				etals[indiceEtal].occuperEtal(vendeur, produit, nbProduit); 
			 
		}
		
		public int trouverEtalLibre() {
			for(int i= 0; i<=etals.length;i++) {
				if(!etals[i].isEtalOccupe())
					return i;
			}
			return -1;
		}
		
		public Etal[] trouverEtals(String produit) {
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
			for (int i = 0; i < etals.length; i++) {
				Etal etal = etals[i];
				if (etal.getVendeur().equals(gaulois)) {
					return etal;
				}
			}
			return null;
		}
		
		public String afficherMarche() {
			int nbEtalOccupe = 0;
			int nbEtalLibre = 0;
			for(int i=0; i<= nbEtals; i++) {
				if(etals[i].isEtalOccupe())
					nbEtalOccupe++;
				nbEtalLibre++;
			}
			return "Il reste " + nbEtalLibre + " �tals non utilis�s dans le march�.\n";

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
					+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	
	public String installerVendeur(Gaulois vendeur, String produit ,int nbProduit){
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit +" " + produit + " .\n");
		int etalLibre = marche.trouverEtalLibre();
		if(etalLibre != -1) {
			marche.utiliserEtal(etalLibre, vendeur, produit, nbProduit);
			chaine.append("Le vendeur " + vendeur.getNom() + " vend des "+ produit + " à l'etal n°" + etalLibre +".\n");
			etalLibre++;
		}
		return chaine.toString();
	}
	
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		
		Etal[] etalsAvecProduit = marche.trouverEtals(produit);
		if(etalsAvecProduit.length == 0) {
			chaine.append("Il n'y a pas de vendeur qui propose des " + produit + " au marché.\n");
		} else {
			chaine.append("Les vendeurs qui proposent des fleurs sont :\n" );
			for(Etal etal : etalsAvecProduit) {
				if(etal != null)
					chaine.append("-" + etal.getVendeur().getNom()+"\n");
			}
		}
		return chaine.toString();
	}
	
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		Etal etals = marche.trouverVendeur(vendeur);
		return etals.libererEtal();
	}
	
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village \"" + nom + "\" possède plusieurs étals :\n" + marche.afficherMarche());
		return chaine.toString();
	}
	
	
	
	
	
	
}