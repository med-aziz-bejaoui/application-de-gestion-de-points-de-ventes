package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VenteDAO {

	VenteEntity venteEntity;

	public VenteDAO() {
		this.venteEntity = new VenteEntity();

	}

	public int setMaxNumVente() { 
		Connection connection;
		int numvente = 0;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd-pfa", "root", "");

			Statement sta = connection.createStatement();
			ResultSet rs;
			rs = sta.executeQuery("SELECT MAX(numVente) FROM vente ");
			
			if (rs.next()) {
				numvente = rs.getInt(1);
			}
			numvente++;
			venteEntity.setNumVente(numvente);
			System.out.println("numero du vente " + venteEntity.getNumVente());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numvente;

	}

	public boolean Validationvente(ProduitSession unproduit) {

		boolean test = false;
		int qtedispo = 0;
		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd-pfa", "root", "");
			connection.setAutoCommit(false);
			connection.beginRequest();

			String sql = "SELECT * FROM produit WHERE refProduit = ? FOR UPDATE";
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, unproduit.dao.entity.getRefProduit());
			rs = stmt.executeQuery();

			while (rs.next()) {
				qtedispo = rs.getInt("qtedispo");

			}
			unproduit.dao.entity.setQtedispo(qtedispo);

			System.out.println("qtedispo= " + qtedispo);
			System.out.println("qte de vente= " + venteEntity.getQteDispo());
			
			if (venteEntity.getNumVente()!=setMaxNumVente()-1) {
				venteEntity.setNumVente(setMaxNumVente());
			}
			
			if (qtedispo <venteEntity.getQteDispo()) {
				System.out.println("ereeur lors de requete for update");

				int montant = 0;

				venteEntity.setQteVendue(qtedispo);

				montant = (unproduit.dao.entity.getPrixUn() * venteEntity.getQteVendue()
						* (1 - (unproduit.dao.entity.getTauxRemise() / 100)));
				venteEntity.setMontantVente(montant);
				
			} 
			
			if ((EnregistrerVente(connection, unproduit) == 0) || (unproduit
					.modifierQteDispodanslatableproduit(connection, venteEntity.getQteVendue()) == 0)) {
				System.out.println("ereeur");
			} else {
				test = true;
			}

			connection.commit();
			connection.setAutoCommit(true);

		} catch (Exception e) {
			try {
				connection.rollback();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		return test;

	}

	public int EnregistrerVente(Connection connection, ProduitSession unproduit) {
		int result = 0;

		try {

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO vente (numVente,dateVente, montantVente, Qtevendu,ref_prod) VALUES (?,?,?,?,?)");

			ps.setInt(1, venteEntity.getNumVente());
			ps.setDate(2, venteEntity.getSqlDate());
			ps.setInt(3, venteEntity.getMontantVente());
			ps.setInt(4, venteEntity.getQteVendue());
			ps.setInt(5, unproduit.dao.entity.getRefProduit());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}
}
