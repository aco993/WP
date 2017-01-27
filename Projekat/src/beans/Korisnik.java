package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Korisnik implements Serializable{
	
	private String korisnickoIme;
	private String lozinka;
	private String ime;
	private String prezime;
	private String uloga;
	private String kontaktTelefon;
	private String email;
	private String adresa;
	private String drzava;
	
	/*private ArrayList<String> kupovina=new ArrayList<String>();
	
	public ArrayList<String> getKupovina() {
		return kupovina;
	}

	public void setKupovina(ArrayList<String> kupovina) {
		this.kupovina = kupovina;
	}*/

	public Korisnik(){
		
	}
	
	public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, String uloga,
			String kontaktTelefon, String email, String adresa, String drzava) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.uloga = uloga;
		this.kontaktTelefon = kontaktTelefon;
		this.email = email;
		this.adresa = adresa;
		this.drzava = drzava;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public String getKontaktTelefon() {
		return kontaktTelefon;
	}

	public void setKontaktTelefon(String kontaktTelefon) {
		this.kontaktTelefon = kontaktTelefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
}
