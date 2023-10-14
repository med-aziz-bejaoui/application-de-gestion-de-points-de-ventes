package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class ProduitDAO {

	ProduitEntity entity;

	public ProduitDAO() {
		ProduitEntity entity = new ProduitEntity();
		this.entity = entity;
	}

	public boolean verificationExactitudeProd(int refProdVL) {
		try {
 
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd-pfa", "root", "");

			String sql = "SELECT * FROM produit WHERE refProduit = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, refProdVL);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				entity.setRefProduit(refProdVL);
				entity.setIntitule(rs.getString("intitule"));
				entity.setPrixUn(rs.getInt("prixUn"));
				entity.setTauxRemise(rs.getInt("tauxRemise"));
				entity.setQtedispo(rs.getInt("qtedispo"));
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public int verificationDispoProduit(int refProdVL) {
		int qtedispo = 0;
		try {

			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd-pfa", "root", "");

			String sql = "SELECT * FROM produit WHERE refProduit = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, refProdVL);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				qtedispo = rs.getInt("qtedispo");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return qtedispo;

	}

	public int modifierQteDispoproduit(Connection connection, int qtevendu) {

		int y = 0;
		int qteRestant = entity.getQtedispo() - qtevendu;

		try {

			PreparedStatement psProduit = connection.prepareStatement("UPDATE produit SET qtedispo='" + qteRestant
					+ "' WHERE refProduit ='" + entity.getRefProduit() + "'");
			y = psProduit.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return y;
	}
	
	public int ajouter_produit(String string, int a, int i, int j, int k) {
		entity.setIntitule(string);
		entity.setRefProduit(a);
		entity.setPrixUn(i);
		entity.setQtedispo(j);
		entity.setTauxRemise(k);
		int result = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd-pfa", "root", "");

			PreparedStatement ps = connection.prepareStatement(
					"INSERT INTO produit (refProduit,intitule, prixUn, tauxRemise,qtedispo) VALUES (?,?,?,?,?)");

			ps.setInt(1, entity.getRefProduit());
			ps.setString(2, entity.getIntitule());
			ps.setInt(3, entity.getPrixUn());
			ps.setInt(4, entity.getTauxRemise());
			ps.setInt(5, entity.getQtedispo());

			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;

	}

	public int modifierproduit(String string, int i, int j, int k) {
		int test = 0;

		entity.setIntitule(string);
		entity.setPrixUn(i);
		entity.setTauxRemise(j);
		entity.setQtedispo(k);
		

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd-pfa", "root", "");

			PreparedStatement ps = connection.prepareStatement(
					"UPDATE produit SET intitule=?, prixUn=?, tauxRemise=?, qtedispo=? WHERE refProduit=?");

			ps.setString(1, entity.getIntitule());
			ps.setInt(2, entity.getPrixUn());
			ps.setInt(3, entity.getTauxRemise());
			ps.setInt(4, entity.getQtedispo());
			ps.setInt(5, entity.getRefProduit());

			test = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return test;
	}


	public int supprimer() {
		int test = 0;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd-pfa", "root", "");

			String sql = "DELETE FROM produit WHERE refProduit = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, entity.getRefProduit());
			test = ps.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return test;
	}
}
