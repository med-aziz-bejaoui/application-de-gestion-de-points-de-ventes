package client;

import java.sql.SQLException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

public class Controleur {

	InterfaceVendre interface1;
	//ProduitDAO produitdao;
	InterfaceModifier modif;
	InterfaceConsulter cons;

	public Controleur(InterfaceVendre interface1) {
		this.interface1 = interface1;
	}
	/*public Controleur(InterfaceModifier modif, ProduitDAO produit2) {
		this.produitdao = produit2;
		this.modif=modif;
	}

	public Controleur(InterfaceConsulter cons, ProduitDAO produit4) {
		this.produitdao = produit4;
		this.cons=cons;
	}*/
	public void AfficherMontantVente(int montant) {
		String mnt=String.valueOf(montant);
		interface1.getLblMontant_value().setText(mnt);
 
	}
	/*
	public void setLabelsModifier() {
		modif.getIntituleproduit().setText(produitdao.entity.getIntitule());
		modif.getQuantitedispo().setText(String.valueOf(produitdao.entity.getQtedispo()));
		modif.getTauxremise().setText(String.valueOf(produitdao.entity.getTauxRemise()));
		modif.getPrixunitaire().setText(String.valueOf(produitdao.entity.getPrixUn()));
		
		
	}
	
	public void setLabelsConsulter() {
		cons.getIntituleproduit().setText(produitdao.entity.getIntitule());
		cons.getQuantitedispo().setText(String.valueOf(produitdao.entity.getQtedispo()));
		cons.getTauxremise().setText(String.valueOf(produitdao.entity.getTauxRemise()));
		cons.getPrixunitaire().setText(String.valueOf(produitdao.entity.getPrixUn()));
		
		
	}*/

}
