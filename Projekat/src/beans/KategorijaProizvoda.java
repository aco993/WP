package beans;

import java.io.IOException;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class KategorijaProizvoda implements Serializable{
	
	private String naziv;
	private String opis;
	private String podkategorija;
	
	public KategorijaProizvoda(){
		
	}
	
	public KategorijaProizvoda(String jsonstring) throws JsonParseException, JsonMappingException, IOException{
		System.out.println(jsonstring);
	}
	
	public KategorijaProizvoda(String naziv, String opis, String podkategorija) {
		super();
		this.naziv = naziv;
		this.opis = opis;
		this.podkategorija = podkategorija;
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

	public String getPodkategorija() {
		return podkategorija;
	}

	public void setPodkategorija(String podkategorija) {
		this.podkategorija = podkategorija;
	}
}
