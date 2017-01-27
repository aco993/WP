package rest.logovanje;

import beans.Korisnik;

public class LogovanjeDTO {
	
	private boolean uspesno;
	private String razlog;
	
	private Korisnik korisnik;

	public LogovanjeDTO(boolean uspesno, String razlog, Korisnik korisnik) {
		super();
		this.uspesno = uspesno;
		this.razlog = razlog;
		this.korisnik = korisnik;
	}

	public boolean isUspesno() {
		return uspesno;
	}

	public void setUspesno(boolean uspesno) {
		this.uspesno = uspesno;
	}

	public String getRazlog() {
		return razlog;
	}

	public void setRazlog(String razlog) {
		this.razlog = razlog;
	}

	public Korisnik getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}
}
