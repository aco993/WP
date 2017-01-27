package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Dostavljac implements Serializable{
	
	private String sifra;
	private String naziv;
	private String opis;
	private ArrayList<String> drzave = new ArrayList<String>();
	private double cena;
	
	public Dostavljac(){
		
	}
	
	public Dostavljac(String sifra, String naziv, String opis, ArrayList<String> drzave, double cena) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.opis = opis;
		this.drzave = drzave;
		this.cena = cena;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public ArrayList<String> getDrzave() {
		return drzave;
	}

	public void setDrzave(ArrayList<String> drzave) {
		this.drzave = drzave;
	}

	public double getCena() {
		return cena;
	}

	public void setTarifePrenosa(double cena) {
		this.cena = cena;
	}
}
