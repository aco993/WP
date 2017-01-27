package rest.logovanje;

import java.io.Serializable;

public class TempKorisnik implements Serializable{
	
	private String korisnickoIme;
	private String lozinka;
	
	public TempKorisnik(){
		
	}
	
	public TempKorisnik(String korisnickoIme, String lozinka) {
		super();
		this.korisnickoIme = korisnickoIme;
		this.lozinka = lozinka;
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
}
