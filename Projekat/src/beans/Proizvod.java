package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class Proizvod implements Serializable{

	private String sifra;
	private String naziv;
	private String boja;
	private double dimenzije;
	private double tezina;
	private String zemljaProizvodnje;
	private String nazivProizvodjaca;
	private double jedinicnaCena;
	private String kategorijaProizvoda;
	private String slika;
	private String video;
	private double kolicinaUMagacinu;
	private String prodavnica;
	public String getProdavnica() {
		return prodavnica;
	}

	public void setProdavnica(String prodavnica) {
		this.prodavnica = prodavnica;
	}

	private double ocena;
	private ArrayList<String> recenzije=new ArrayList<String>();
	
	public Proizvod(){
		
	}
	
	public Proizvod(String sifra, String naziv, String boja, double dimenzije, double tezina, String zemljaProizvodnje,
			String nazivProizvodjaca, double jedinicnaCena, String kategorijaProizvoda, String slika,
			String video, double kolicinaUMagacinu, String prodavnica, double ocena, ArrayList<String> recenzije) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.boja = boja;
		this.dimenzije = dimenzije;
		this.tezina = tezina;
		this.zemljaProizvodnje = zemljaProizvodnje;
		this.nazivProizvodjaca = nazivProizvodjaca;
		this.jedinicnaCena = jedinicnaCena;
		this.kategorijaProizvoda = kategorijaProizvoda;
		this.slika = slika;
		this.video = video;
		this.kolicinaUMagacinu = kolicinaUMagacinu;
		this.prodavnica = prodavnica;
		this.ocena = ocena;
		this.recenzije = recenzije;
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

	public String getBoja() {
		return boja;
	}

	public void setBoja(String boja) {
		this.boja = boja;
	}

	public double getDimenzije() {
		return dimenzije;
	}

	public void setDimenzije(double dimenzije) {
		this.dimenzije = dimenzije;
	}

	public double getTezina() {
		return tezina;
	}

	public void setTezina(double tezina) {
		this.tezina = tezina;
	}

	public String getZemljaProizvodnje() {
		return zemljaProizvodnje;
	}

	public void setZemljaProizvodnje(String zemljaProizvodnje) {
		this.zemljaProizvodnje = zemljaProizvodnje;
	}

	public String getNazivProizvodjaca() {
		return nazivProizvodjaca;
	}

	public void setNazivProizvodjaca(String nazivProizvodjaca) {
		this.nazivProizvodjaca = nazivProizvodjaca;
	}

	public double getJedinicnaCena() {
		return jedinicnaCena;
	}

	public void setJedinicnaCena(double jedinicnaCena) {
		this.jedinicnaCena = jedinicnaCena;
	}

	public String getKategorijaProizvoda() {
		return kategorijaProizvoda;
	}

	public void setKategorijaProizvoda(String kategorijaProizvoda) {
		this.kategorijaProizvoda = kategorijaProizvoda;
	}

	public String getSlika() {
		return slika;
	}

	public void setSlika(String slika) {
		this.slika = slika;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public double getOcena() {
		return ocena;
	}

	public void setOcena(double ocena) {
		this.ocena = ocena;
	}

	public ArrayList<String> getRecenzije() {
		return recenzije;
	}

	public void setRecenzije(ArrayList<String> recenzije) {
		this.recenzije = recenzije;
	}

	public double getKolicinaUMagacinu() {
		return kolicinaUMagacinu;
	}

	public void setKolicinaUMagacinu(double kolicinaUMagacinu) {
		this.kolicinaUMagacinu = kolicinaUMagacinu;
	}
}
