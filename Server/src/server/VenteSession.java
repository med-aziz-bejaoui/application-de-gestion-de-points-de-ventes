package server;


public class VenteSession {

	private ProduitSession unproduit;
	VenteDAO venteDAO;
 
	// VenteEntity venteEntity;
	public VenteSession(ProduitSession prod1) {

		// this.venteEntity= new VenteEntity();
		this.venteDAO = new VenteDAO();

		unproduit = prod1;
		this.venteDAO.setMaxNumVente();
	}

	public boolean VerificationPossibiliteVente(int qtedesireevl) {
		if (qtedesireevl > unproduit.dao.entity.getQtedispo()) {
			

			return false;
		}
		venteDAO.venteEntity.setQteVendue(qtedesireevl);
		venteDAO.venteEntity.setQteDispo(unproduit.dao.entity.getQtedispo());
		return true;
	}

	public int CalculerMontantVente() {
		int montant = 0;

		montant = (unproduit.dao.entity.getPrixUn() * venteDAO.venteEntity.getQteVendue()
				* (1 - (unproduit.dao.entity.getTauxRemise() / 100)));
		venteDAO.venteEntity.setMontantVente(montant);
		System.out.println("montant vente " + venteDAO.venteEntity.getMontantVente());
		return montant;
	}


	public boolean Validationvente() {
		return venteDAO.Validationvente(unproduit);
	}
}
