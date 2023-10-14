package server;

import java.util.Date;

public class VenteEntity {
	private int numVente;
	private int QteDispo;
	private int Qtevendu;
	private int montantVente;
	private java.sql.Date sqlDate;
	private Date date;
	
	
    public VenteEntity() {
    	this.date = new Date(); 
		this.sqlDate = new java.sql.Date(date.getTime());
	}

	public int getNumVente() {
        return numVente;
    }

    public void setNumVente(int numVente) {
        this.numVente = numVente;
    }

    public int getQteVendue() {
        return Qtevendu;
    }

    public void setQteVendue(int qteVendue) {
        this.Qtevendu = qteVendue;
    }

    public int getMontantVente() {
        return montantVente;
    }

    public void setMontantVente(int montantVente) {
        this.montantVente = montantVente;
    }

	public java.sql.Date getSqlDate() {
		return sqlDate;
	}

	public Date getDate() {
		return date;
	}

	public int getQteDispo() {
		return QteDispo;
	}

	public void setQteDispo(int qteDispo) {
		QteDispo = qteDispo;
	}
}
