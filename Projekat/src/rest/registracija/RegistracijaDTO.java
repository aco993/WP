package rest.registracija;

import beans.Korisnik;

public class RegistracijaDTO {
	
	private boolean uspesno;
	private String razlog;
	
	private Korisnik korisnik;

	public RegistracijaDTO(boolean uspesno, String razlog, Korisnik korisnik) {
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
