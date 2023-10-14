package server;

public class ProduitEntity  {
	private int refProduit;
	private String intitule;
	private int prixUn;
	private int tauxRemise;
	private int qtedispo;
	
	
	public int getRefProduit() {
		return refProduit;
	}

	public void setRefProduit(int refProduit) {
		this.refProduit = refProduit;
	} 

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public int getPrixUn() {
		return prixUn;
	}

	public void setPrixUn(int prixUn) {
		this.prixUn = prixUn;
	}

	public int getTauxRemise() {
		return tauxRemise;
	}

	public void setTauxRemise(int tauxRemise) {
		this.tauxRemise = tauxRemise;
	}

	public int getQtedispo() {
		return qtedispo;
	}

	public void setQtedispo(int qtedispo) {
		this.qtedispo = qtedispo;
	}
	
	
	
}
