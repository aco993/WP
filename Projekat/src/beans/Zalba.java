package beans;

import java.io.Serializable;

public class Zalba implements Serializable{
	
	private String sifra;
	private String sifraKupovine;
	private String kupac;
	private String opis;
	public Zalba() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Zalba(String sifra, String sifraKupovine,String kupac, String opis) {
		super();
		this.sifra = sifra;
		this.sifraKupovine = sifraKupovine;
		this.kupac=kupac;
		this.opis = opis;
	}
	public String getKupac() {
		return kupac;
	}
	public void setKupac(String kupac) {
		this.kupac = kupac;
	}
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	public String getSifraKupovine() {
		return sifraKupovine;
	}
	public void setSifraKupovine(String sifraKupovine) {
		this.sifraKupovine = sifraKupovine;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}	
}
