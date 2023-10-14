package server;

import java.sql.Connection;

public class ProduitSession {
	ProduitDAO dao;
	

	public ProduitSession() {
		ProduitDAO dao = new ProduitDAO();
		this.dao = dao;
	}

	public boolean VerificationExactitudeRefProdETRechercheDonneesProduit(int refProdVL) {
		boolean test = false;

		test = dao.verificationExactitudeProd(refProdVL);

		return test; 

	}

	public boolean VerificationDisponibiliteProduit(int refProdVL) {
		boolean test = false;

		int qte = dao.verificationDispoProduit(refProdVL);

		if (qte > 0) {
			test = true;
		}

		return test;

	}

	public int modifierQteDispodanslatableproduit(Connection connection,int qtevendu) {

		return dao.modifierQteDispoproduit(connection,qtevendu);
	}
	
}
