package beans;

import java.io.Serializable;

public class RecenzijaProdavnice implements Serializable {
	
	private String sifra;
	private String kupac;
	private String nazivProdavnice;
	private String datum;
	private String komentar;
	private double ocena;
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	public String getNazivProdavnice() {
		return nazivProdavnice;
	}
	public void setNazivProdavnice(String nazivProdavnice) {
		this.nazivProdavnice = nazivProdavnice;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getKomentar() {
		return komentar;
	}
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	public double getOcena() {
		return ocena;
	}
	public void setOcena(double ocena) {
		this.ocena = ocena;
	}
	public RecenzijaProdavnice(String sifra, String kupac, String nazivProdavnice, String datum, String komentar,
			double ocena) {
		super();
		this.sifra = sifra;
		this.kupac = kupac;
		this.nazivProdavnice = nazivProdavnice;
		this.datum = datum;
		this.komentar = komentar;
		this.ocena = ocena;
	}
	public RecenzijaProdavnice() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
