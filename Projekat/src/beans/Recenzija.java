package beans;

import java.io.Serializable;
import java.util.Date;

public class Recenzija implements Serializable{
	
	private String sifra;
	private String kupac;
	private String nazivProizvoda;
	private String sifraProizvoda;
	private String datum;
	private String komentar;
	private double ocena;
	
	public Recenzija(String sifra, String kupac, String nazivProizvoda, String sifraProizvoda, String datum,
			double ocena, String komentar) {
		super();
		this.sifra = sifra;
		this.kupac = kupac;
		this.nazivProizvoda = nazivProizvoda;
		this.sifraProizvoda = sifraProizvoda;
		this.datum = datum;
		this.ocena = ocena;
		this.komentar = komentar;
	}
	
	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}

	public Recenzija() {
		super();
		// TODO Auto-generated constructor stub
	}
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
	public String getNazivProizvoda() {
		return nazivProizvoda;
	}
	public void setNazivProizvoda(String nazivProizvoda) {
		this.nazivProizvoda = nazivProizvoda;
	}
	public String getSifraProizvoda() {
		return sifraProizvoda;
	}
	public void setSifraProizvoda(String sifraProizvoda) {
		this.sifraProizvoda = sifraProizvoda;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public double getOcena() {
		return ocena;
	}
	public void setOcena(double ocena) {
		this.ocena = ocena;
	}
}
