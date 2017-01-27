package beans;

import java.io.Serializable;
import java.util.Date;

public class Akcija implements Serializable {
private String sifra;
private String prodavnica;
private String proizvod;
private int procenat;
private String pocDatum;
private String krajDatum;


public Akcija() {
	super();
}
public Akcija(String sifra, String prodavnica, String proizvod, int procenat, String pocDatum, String krajDatum) {
	super();
	this.sifra = sifra;
	this.prodavnica = prodavnica;
	this.proizvod = proizvod;
	this.procenat = procenat;
	this.pocDatum = pocDatum;
	this.krajDatum = krajDatum;
}
public String getSifra() {
	return sifra;
}
public void setSifra(String sifra) {
	this.sifra = sifra;
}
public String getProdavnica() {
	return prodavnica;
}
public void setProdavnica(String prodavnica) {
	this.prodavnica = prodavnica;
}
public String getProizvod() {
	return proizvod;
}
public void setProizvod(String proizvod) {
	this.proizvod = proizvod;
}
public int getProcenat() {
	return procenat;
}
public void setProcenat(int procenat) {
	this.procenat = procenat;
}
public String getPocDatum() {
	return pocDatum;
}
public void setPocDatum(String pocDatum) {
	this.pocDatum = pocDatum;
}
public String getKrajDatum() {
	return krajDatum;
}
public void setKrajDatum(String krajDatum) {
	this.krajDatum = krajDatum;
}

}
