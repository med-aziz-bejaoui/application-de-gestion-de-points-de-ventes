package client;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InterfaceSupprimer extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField refproduit;

	private JLabel lblref;
	
  
	public InterfaceSupprimer() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\User\\Desktop\\STDM.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 190, 1014, 597);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblVendreProduit = new JLabel("Supprimer Un Produit");
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

	public void clearLabels() {
		getRefproduit().setText("");
		

	}
	
	

}