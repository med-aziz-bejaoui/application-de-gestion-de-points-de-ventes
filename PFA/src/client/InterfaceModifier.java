package client;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InterfaceModifier extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField refproduit;
	private JTextField intituleproduit;
	private JTextField prixunitaire;
	private JTextField tauxremise;
	private JTextField quantitedispo;

	private JLabel lblref;
	private JLabel lblintitule;
	private JLabel lblprixunitaire;
	private JLabel lbltauxremise;
	private JLabel lblquantitedispo; 

	private JButton btnNewButton1;
	private JButton btnNewButton2;

	public InterfaceModifier() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVendreProduit = new JLabel("Modifier Un Produit");
		lblVendreProduit.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblVendreProduit.setBounds(340, 52, 380, 50);
		contentPane.add(lblVendreProduit);

		lblref = new JLabel("Reference du Produit");
		lblref.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblref.setBounds(58, 155, 200, 20);
		contentPane.add(lblref);

		refproduit = new JTextField();
		refproduit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		refproduit.setBounds(300, 151, 228, 30);
		contentPane.add(refproduit);
		refproduit.setColumns(10);

		lblintitule = new JLabel("Intitule du Produit");
		lblintitule.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblintitule.setBounds(58, 218, 200, 20);
		//contentPane.add(lblintitule);

		intituleproduit = new JTextField();
		intituleproduit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		intituleproduit.setColumns(10);
		intituleproduit.setBounds(300, 215, 228, 30);
		//contentPane.add(intituleproduit);

		lblprixunitaire = new JLabel("Prix Unitaire");
		lblprixunitaire.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblprixunitaire.setBounds(58, 279, 200, 20);
		//contentPane.add(lblprixunitaire);

		prixunitaire = new JTextField();
		prixunitaire.setFont(new Font("Tahoma", Font.PLAIN, 15));
		prixunitaire.setColumns(10);
		prixunitaire.setBounds(300, 275, 228, 30);
		//contentPane.add(prixunitaire);
		lbltauxremise = new JLabel("Taux de Remise");
		lbltauxremise.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbltauxremise.setBounds(58, 340, 200, 20);
		//contentPane.add(lbltauxremise);

		tauxremise = new JTextField();
		tauxremise.setFont(new Font("Tahoma", Font.PLAIN, 15));
		tauxremise.setColumns(10);
		tauxremise.setBounds(300, 336, 228, 30);
		//contentPane.add(tauxremise);

		lblquantitedispo = new JLabel("Quantité disponible");
		lblquantitedispo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblquantitedispo.setBounds(58, 401, 200, 20);
		//contentPane.add(lblquantitedispo);

		quantitedispo = new JTextField();
		quantitedispo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		quantitedispo.setColumns(10);
		quantitedispo.setBounds(300, 397, 228, 30);
		//contentPane.add(quantitedispo);

		btnNewButton1 = new JButton("Valider");
		btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton1.setBounds(120, 465, 228, 30);
		//contentPane.add(btnNewButton1);

		btnNewButton2 = new JButton("Annuler");
		btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton2.setBounds(380, 465, 228, 30); 
		//contentPane.add(btnNewButton2);

	}
	public int DemandeSaisieRefProduit() {
		String refProduit = refproduit.getText();
		int ref;
		try {
			ref = Integer.parseInt(refProduit);
		} catch (Exception e) {
			ref = 0;
		}

		return ref;
	}

	public JTextField getRefproduit() {
		return refproduit;
	}

	public void setRefproduit(JTextField refproduit) {
		this.refproduit = refproduit;
	}

	public JTextField getIntituleproduit() {
		return intituleproduit;
	}

	public void setIntituleproduit(JTextField intituleproduit) {
		this.intituleproduit = intituleproduit;
	}

	public JTextField getPrixunitaire() {
		return prixunitaire;
	}

	public void setPrixunitaire(JTextField prixunitaire) {
		this.prixunitaire = prixunitaire;
	}

	public JTextField getTauxremise() {
		return tauxremise;
	}

	public void setTauxremise(JTextField tauxremise) {
		this.tauxremise = tauxremise;
	}

	public JTextField getQuantitedispo() {
		return quantitedispo;
	}

	public void setQuantitedispo(JTextField quantitedispo) {
		this.quantitedispo = quantitedispo;
	}

	public JLabel getLblref() {
		return lblref;
	}

	public void setLblref(JLabel lblref) {
		this.lblref = lblref;
	}

	public JLabel getLblintitule() {
		return lblintitule;
	}

	public void setLblintitule(JLabel lblintitule) {
		this.lblintitule = lblintitule;
	}

	public JLabel getLblprixunitaire() {
		return lblprixunitaire;
	}

	public void setLblprixunitaire(JLabel lblprixunitaire) {
		this.lblprixunitaire = lblprixunitaire;
	}

	public JLabel getLbltauxremise() {
		return lbltauxremise;
	}

	public void setLbltauxremise(JLabel lbltauxremise) {
		this.lbltauxremise = lbltauxremise;
	}

	public JLabel getLblquantitedispo() {
		return lblquantitedispo;
	}

	public void setLblquantitedispo(JLabel lblquantitedispo) {
		this.lblquantitedispo = lblquantitedispo;
	}

	public JButton getBtnNewButton1() {
		return btnNewButton1;
	}

	public void setBtnNewButton1(JButton btnNewButton1) {
		this.btnNewButton1 = btnNewButton1;
	}

	public JButton getBtnNewButton2() {
		return btnNewButton2;
	}

	public void setBtnNewButton2(JButton btnNewButton2) {
		this.btnNewButton2 = btnNewButton2;
	}

	public void clearLabels() {
		getRefproduit().setText("");
		getIntituleproduit().setText("");
		getPrixunitaire().setText("");
		getQuantitedispo().setText("");
		getTauxremise().setText("");

	}
	public void addLabels() {
		contentPane.add(lblintitule);
		contentPane.add(intituleproduit);
		contentPane.add(lblprixunitaire);
		contentPane.add(prixunitaire);
		contentPane.add(lbltauxremise);
		contentPane.add(tauxremise);
		contentPane.add(lblquantitedispo);
		contentPane.add(quantitedispo);
		contentPane.add(btnNewButton1);
		contentPane.add(btnNewButton2);
		
	}
	

	public void removeLabels() {

		try {
			remove(lblintitule);
			remove(intituleproduit);
			remove(lblprixunitaire);
			remove(prixunitaire);
			remove(lbltauxremise);
			remove(tauxremise);
			remove(lblquantitedispo);
			remove(quantitedispo);
			remove(btnNewButton1);
			remove(btnNewButton2);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	public void setLabelsModifier(String string, int parseInt, int parseInt2, int parseInt3) {
		getIntituleproduit().setText(string);
		getPrixunitaire().setText(String.valueOf(parseInt));
		getTauxremise().setText(String.valueOf(parseInt2));
		getQuantitedispo().setText(String.valueOf(parseInt3));
		
		
	}

}