package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Kupovina implements Serializable{
	
	private String sifra;
	public String getSifra() {
		return sifra;
	}
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	private String kupac;
	private String nazivProizvoda;
	private String sifraProizvoda;
	public String getSifraProizvoda() {
		return sifraProizvoda;
	}
	public void setSifraProizvoda(String sifraProizvoda) {
		this.sifraProizvoda = sifraProizvoda;
	}
	private String datum;
	private String dostavljac;
	private double cenaDostavljaca;
	private double kolicina;
	private double jedinicnaCenaProizvoda;
	public Kupovina(String sifra, String kupac,String nazivProizvoda, String sifraProizvoda, String datum, String dostavljac, double cenaDostavljaca, double kolicina,
			double jedinicnaCenaProizvoda) {
		super();
		this.sifra=sifra;
		this.kupac=kupac;
		this.nazivProizvoda = nazivProizvoda;
		this.sifraProizvoda=sifraProizvoda;
		this.datum = datum;
		this.dostavljac = dostavljac;
		this.cenaDostavljaca = cenaDostavljaca;
		this.kolicina = kolicina;
		this.jedinicnaCenaProizvoda = jedinicnaCenaProizvoda;
	}
	public Kupovina() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getDostavljac() {
		return dostavljac;
	}
	public void setDostavljac(String dostavljac) {
		this.dostavljac = dostavljac;
	}
	public double getCenaDostavljaca() {
		return cenaDostavljaca;
	}
	public void setCenaDostavljaca(double cenaDostavljaca) {
		this.cenaDostavljaca = cenaDostavljaca;
	}
	public double getKolicina() {
		return kolicina;
	}
	public void setKolicina(double kolicina) {
		this.kolicina = kolicina;
	}
	public double getJedinicnaCenaProizvoda() {
		return jedinicnaCenaProizvoda;
	}
	public void setJedinicnaCenaProizvoda(double jedinicnaCenaProizvoda) {
		this.jedinicnaCenaProizvoda = jedinicnaCenaProizvoda;
	}
}
