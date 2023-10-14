package client;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


/**
 * User Registration using Swing 
 * 
 * @author javaguides.net
 * 
 */
public class InterfaceVendre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField refproduit;
	private JTextField Qtevendu;
	// private JTextField email;
	private JLabel lblMontant_value;
	private JLabel lblMontant1;
	private JLabel lblQteVendu;
	private JButton btnNewButton1;
	private JButton btnNewButton2;

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
	
	public int demandesaisieQtedesireeparleclient() {
		String QteVendu = Qtevendu.getText();
		int qte;
		try {
			qte = Integer.parseInt(QteVendu);
		} catch (Exception e) {
			qte = 0;
		}

		return qte;

	}
	/*public void AfficherMontantVente() {
		String mnt = Integer.toString(venteDAO.venteEntity.getMontantVente());
		//controleur.AfficherMontantVente(mnt);
		
	}*/

	public void clean_label() {

		lblMontant_value.setText("");

	}

	/**
	 * Create the frame.
	 */
	public InterfaceVendre() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVendreProduit = new JLabel("Vendre Produit");
		lblVendreProduit.setFont(new Font("Times New Roman", Font.PLAIN, 42));
		lblVendreProduit.setBounds(362, 52, 325, 50);
		contentPane.add(lblVendreProduit);

		JLabel lblref = new JLabel("Reference du Produit");
		lblref.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblref.setBounds(58, 152, 200, 43);
		contentPane.add(lblref);

		refproduit = new JTextField();
		refproduit.setFont(new Font("Tahoma", Font.PLAIN, 32));
		refproduit.setBounds(300, 151, 228, 50);
		contentPane.add(refproduit);
		refproduit.setColumns(10);

		btnNewButton2 = new JButton("Annuler");
		btnNewButton2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton2.setBounds(450, 447, 259, 74);
		

		/*
		 * clean_label(); btnNewButton2.addActionListener(new ActionListener() { public
		 * void actionPerformed(ActionEvent e) { clean_label(); JTextField[] jtf = {
		 * refproduit, Qtevendu }; for (int i = 0; i < jtf.length; i++) {
		 * jtf[i].setText(""); } } });
		 */

	}

	public void addFieldQuantite() {
		lblQteVendu = new JLabel("Quantite de Vente");
		lblQteVendu.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblQteVendu.setBounds(58, 243, 200, 29);
		contentPane.add(lblQteVendu);

		Qtevendu = new JTextField();
		Qtevendu.setFont(new Font("Tahoma", Font.PLAIN, 32));
		Qtevendu.setBounds(300, 235, 228, 50);
		contentPane.add(Qtevendu);
		Qtevendu.setColumns(10);
		Qtevendu.requestFocus();

	}

	public void addLabels() {

		lblMontant1 = new JLabel("Montant Total: ");
		lblMontant1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMontant1.setBounds(542, 329, 139, 26);
		contentPane.add(lblMontant1);

		lblMontant_value = new JLabel("");
		lblMontant_value.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblMontant_value.setBounds(720, 329, 139, 26);
		contentPane.add(lblMontant_value);

		btnNewButton1 = new JButton("Valider");
		btnNewButton1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		btnNewButton1.setBounds(120, 447, 259, 74);
		

		contentPane.add(btnNewButton1);
		contentPane.add(btnNewButton2);
		

	}

	public void removeLabels() {

		try {
			remove(lblQteVendu);
			remove(Qtevendu);
			remove(lblMontant_value);
			remove(lblMontant1);
			remove(btnNewButton1);
			remove(btnNewButton2);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void removeLabelsValid() {

		try {

			remove(lblMontant_value);
			remove(lblMontant1);
			remove(btnNewButton1);

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public JTextField getRefproduit() {
		return refproduit;
	}

	public JTextField getQtevendu() {
		return Qtevendu;
	}

	public JLabel getLblMontant_value() {
		return lblMontant_value;
	}

	public JButton getBtnNewButton1() {
		return btnNewButton1;
	}

	public JButton getBtnNewButton2() {
		return btnNewButton2;
	}

}