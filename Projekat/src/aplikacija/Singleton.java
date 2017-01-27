package aplikacija;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import beans.Dostavljac;
import beans.KategorijaProizvoda;
import beans.Korisnik;
import beans.Kupovina;
import beans.Prodavnica;
import beans.Proizvod;
import beans.Recenzija;
import beans.RecenzijaProdavnice;
import beans.Zalba;
import beans.Akcija;
import rest.logovanje.TempKorisnik;

public class Singleton {
	
	private static Singleton instance=null;
	private static ArrayList<Korisnik> korisnici=new ArrayList<Korisnik>();
	private static ArrayList<KategorijaProizvoda> kategorijeProizvoda=new ArrayList<KategorijaProizvoda>();
	private static ArrayList<String> drzave=new ArrayList<String>();
	private static ArrayList<Dostavljac> dostavljaci=new ArrayList<Dostavljac>();
	private static ArrayList<Prodavnica> prodavnice=new ArrayList<Prodavnica>();
	private static ArrayList<Proizvod> proizvodi=new ArrayList<Proizvod>();
	
	private static ArrayList<Kupovina> kupovine=new ArrayList<Kupovina>();
	
	private static ArrayList<Recenzija> recenzije=new ArrayList<Recenzija>();
	
	private static ArrayList<Zalba> zalbe=new ArrayList<Zalba>();
	
	private static ArrayList<RecenzijaProdavnice> recenzijeProdavnica=new ArrayList<RecenzijaProdavnice>();
	private static ArrayList<Akcija> akcije= new ArrayList<Akcija>();

	public static ArrayList<RecenzijaProdavnice> getRecenzijeProdavnica() {
		return recenzijeProdavnica;
	}

	public static void setRecenzijeProdavnica(ArrayList<RecenzijaProdavnice> recenzijeProdavnica) {
		Singleton.recenzijeProdavnica = recenzijeProdavnica;
	}

	public static ArrayList<Zalba> getZalbe() {
		return zalbe;
	}

	public static void setZalbe(ArrayList<Zalba> zalbe) {
		Singleton.zalbe = zalbe;
	}

	public static ArrayList<Recenzija> getRecenzije() {
		return recenzije;
	}

	public static void setRecenzije(ArrayList<Recenzija> recenzije) {
		Singleton.recenzije = recenzije;
	}

	public static ArrayList<Kupovina> getKupovine() {
		return kupovine;
	}

	public static void setKupovine(ArrayList<Kupovina> kupovine) {
		Singleton.kupovine = kupovine;
	}

	public static ArrayList<Proizvod> getProizvodi() {
		return proizvodi;
	}

	public static ArrayList<Prodavnica> getProdavnice() {
		return prodavnice;
	}

	public static ArrayList<Dostavljac> getDostavljaci() {
		return dostavljaci;
	}

	public static ArrayList<String> getDrzave() {
		return drzave;
	}

	public static ArrayList<KategorijaProizvoda> getKategorijeProizvoda() {
		return kategorijeProizvoda;
	}
	
	public static ArrayList<Akcija> getAkcije() {		
		return akcije;		
	}		
	public static void setAkcije(ArrayList<Akcija> akcije) {		
		Singleton.akcije = akcije;		
	}

	public Singleton() {
		drzave.add("Srbija");
		drzave.add("Rusija");
		drzave.add("Gruzija");
		drzave.add("Jermenija");
		drzave.add("Belorusija");
		drzave.add("Ukrajina");
		drzave.add("Bugarska");
		drzave.add("Makedonija");
		drzave.add("Poljska");
		drzave.add("Ceska");
		drzave.add("Slovacka");
		drzave.add("BIH");
	}
	
	public static Singleton getInstance() {
		if(instance==null){
			instance = new Singleton();
		}
		return instance;
	}
	
	public synchronized static ArrayList<Korisnik> getKorisnici() {
		return korisnici;
	}
	
	public synchronized static boolean proveriRegistraciju(Korisnik korisnik){
		for(Korisnik k : korisnici){
			if(k.getKorisnickoIme().equals(korisnik.getKorisnickoIme()))
				return true;
		}
		return false;		
	}
	
	public synchronized static Korisnik proveriLogovanje(TempKorisnik temp){
		Korisnik korisnik=null;
				
		for(Korisnik k: korisnici){
			if(k.getKorisnickoIme().equals(temp.getKorisnickoIme())){
				if(k.getLozinka().equals(temp.getLozinka())){
					korisnik=k;
					break;
				}
			}
		}		
		return korisnik;
	}
	
	public static synchronized void serializujKorisnike(String location) {
		location += "baza\\korisnici.bin";
		try
		{
			FileOutputStream fout = new FileOutputStream(location);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(korisnici);
			oout.close();
			fout.close();
		} catch(IOException i)
		{
			i.printStackTrace();
		}	
		
	}
	
	public static synchronized void deserializujKorisnike(String location) {
		location += "baza\\korisnici.bin";
		try
		{
		   FileInputStream fileIn = new FileInputStream(location);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   korisnici = (ArrayList<Korisnik>) in.readObject();
		   in.close();
		   fileIn.close();
		   for(Korisnik k : korisnici){
				System.out.println(k.getKorisnickoIme()+" "+k.getLozinka());
			}
		}catch(IOException i)
		{
		   i.printStackTrace();
		   return;
		}catch(ClassNotFoundException c)
		{
		   c.printStackTrace();
		   return;
		}
	}
	
	
	
	public static synchronized void serializujKategorije(String location) {
		location += "baza\\kategorije.bin";
		try
		{
			FileOutputStream fout = new FileOutputStream(location);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(kategorijeProizvoda);
			oout.close();
			fout.close();
		} catch(IOException i)
		{
			i.printStackTrace();
		}	
		
	}
	
	public static synchronized void deserializujKategorije(String location) {
		location += "baza\\kategorije.bin";
		try
		{
		   FileInputStream fileIn = new FileInputStream(location);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   kategorijeProizvoda = (ArrayList<KategorijaProizvoda>) in.readObject();
		   in.close();
		   fileIn.close();
		}catch(IOException i)
		{
		   i.printStackTrace();
		   return;
		}catch(ClassNotFoundException c)
		{
		   c.printStackTrace();
		   return;
		}		
	}
	
	public static synchronized void serializujDostavljace(String location) {
		location += "baza\\dostavljaci.bin";
		try
		{
			FileOutputStream fout = new FileOutputStream(location);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(dostavljaci);
			oout.close();
			fout.close();
		} catch(IOException i)
		{
			i.printStackTrace();
		}	
		
	}
	
	public static synchronized void deserializujDostavljace(String location) {
		location += "baza\\dostavljaci.bin";
		try
		{
		   FileInputStream fileIn = new FileInputStream(location);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   dostavljaci = (ArrayList<Dostavljac>) in.readObject();
		   in.close();
		   fileIn.close();
		}catch(IOException i)
		{
		   i.printStackTrace();
		   return;
		}catch(ClassNotFoundException c)
		{
		   c.printStackTrace();
		   return;
		}		
	}
	
	public static synchronized void serializujProdavnice(String location) {
		location += "baza\\prodavnice.bin";
		try
		{
			FileOutputStream fout = new FileOutputStream(location);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(prodavnice);
			oout.close();
			fout.close();
		} catch(IOException i)
		{
			i.printStackTrace();
		}	
		
	}
	
	public static synchronized void deserializujProdavnice(String location) {
		location += "baza\\prodavnice.bin";
		try
		{
		   FileInputStream fileIn = new FileInputStream(location);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   prodavnice = (ArrayList<Prodavnica>) in.readObject();
		   in.close();
		   fileIn.close();
		}catch(IOException i)
		{
		   i.printStackTrace();
		   return;
		}catch(ClassNotFoundException c)
		{
		   c.printStackTrace();
		   return;
		}		
	}
	
	public static synchronized void serializujProizvode(String location) {
		location += "baza\\proizvodi.bin";
		try
		{
			FileOutputStream fout = new FileOutputStream(location);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(proizvodi);
			oout.close();
			fout.close();
		} catch(IOException i)
		{
			i.printStackTrace();
		}	
		
	}
	
	public static synchronized void deserializujProizvode(String location) {
		location += "baza\\proizvodi.bin";
		try
		{
		   FileInputStream fileIn = new FileInputStream(location);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   proizvodi = (ArrayList<Proizvod>) in.readObject();
		   in.close();
		   fileIn.close();
		}catch(IOException i)
		{
		   i.printStackTrace();
		   return;
		}catch(ClassNotFoundException c)
		{
		   c.printStackTrace();
		   return;
		}		
	}
	
	public static synchronized void serializujKupovine(String location) {
		location += "baza\\kupovine.bin";
		try
		{
			FileOutputStream fout = new FileOutputStream(location);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(kupovine);
			oout.close();
			fout.close();
		} catch(IOException i)
		{
			i.printStackTrace();
		}	
		
	}
	
	public static synchronized void deserializujKupovine(String location) {
		location += "baza\\kupovine.bin";
		try
		{
		   FileInputStream fileIn = new FileInputStream(location);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   kupovine = (ArrayList<Kupovina>) in.readObject();
		   in.close();
		   fileIn.close();
		}catch(IOException i)
		{
		   i.printStackTrace();
		   return;
		}catch(ClassNotFoundException c)
		{
		   c.printStackTrace();
		   return;
		}		
	}
	
	public static synchronized void serializujRecenzije(String location) {
		location += "baza\\recenzije.bin";
		try
		{
			FileOutputStream fout = new FileOutputStream(location);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(recenzije);
			oout.close();
			fout.close();
		} catch(IOException i)
		{
			i.printStackTrace();
		}	
		
	}
	
	public static synchronized void deserializujRecenzije(String location) {
		location += "baza\\recenzije.bin";
		try
		{
		   FileInputStream fileIn = new FileInputStream(location);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   recenzije = (ArrayList<Recenzija>) in.readObject();
		   in.close();
		   fileIn.close();
		}catch(IOException i)
		{
		   i.printStackTrace();
		   return;
		}catch(ClassNotFoundException c)
		{
		   c.printStackTrace();
		   return;
		}		
	}
	
	public static synchronized void serializujZalbe(String location) {
		location += "baza\\zalbe.bin";
		try
		{
			FileOutputStream fout = new FileOutputStream(location);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(zalbe);
			oout.close();
			fout.close();
		} catch(IOException i)
		{
			i.printStackTrace();
		}	
		
	}
	
	public static synchronized void deserializujZalbe(String location) {
		location += "baza\\zalbe.bin";
		try
		{
		   FileInputStream fileIn = new FileInputStream(location);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   zalbe = (ArrayList<Zalba>) in.readObject();
		   in.close();
		   fileIn.close();
		}catch(IOException i)
		{
		   i.printStackTrace();
		   return;
		}catch(ClassNotFoundException c)
		{
		   c.printStackTrace();
		   return;
		}		
	}
	
	public static synchronized void serializujRecenzijeProdavnica(String location) {
		location += "baza\\recenzijeProdavnica.bin";
		try
		{
			FileOutputStream fout = new FileOutputStream(location);
			ObjectOutputStream oout = new ObjectOutputStream(fout);
			oout.writeObject(recenzijeProdavnica);
			oout.close();
			fout.close();
		} catch(IOException i)
		{
			i.printStackTrace();
		}	
		
	}
	
	public static synchronized void deserializujRecenzijeProdavnica(String location) {
		location += "baza\\recenzijeProdavnica.bin";
		try
		{
		   FileInputStream fileIn = new FileInputStream(location);
		   ObjectInputStream in = new ObjectInputStream(fileIn);
		   recenzijeProdavnica = (ArrayList<RecenzijaProdavnice>) in.readObject();
		   in.close();
		   fileIn.close();
		}catch(IOException i)
		{
		   i.printStackTrace();
		   return;
		}catch(ClassNotFoundException c)
		{
		   c.printStackTrace();
		   return;
		}		
	}
	
	public static synchronized void serializujAkcije(String location) {		
		location += "baza\\akcije.bin";		
		try		
		{		
			FileOutputStream fout = new FileOutputStream(location);		
			ObjectOutputStream oout = new ObjectOutputStream(fout);		
			oout.writeObject(akcije);		
			oout.close();		
			fout.close();		
		} catch(IOException i)		
		{		
			i.printStackTrace();		
		}			
				
	}		
			
	public static synchronized void deserializujAkcije(String location) {		
		location += "baza\\akcije.bin";		
		try		
		{		
		   FileInputStream fileIn = new FileInputStream(location);		
		   ObjectInputStream in = new ObjectInputStream(fileIn);		
		  akcije = (ArrayList<Akcija>) in.readObject();		
		   in.close();		
		   fileIn.close();		
		}catch(IOException i)		
		{		
		   i.printStackTrace();		
		   return;		
		}catch(ClassNotFoundException c)		
		{		
		   c.printStackTrace();		
		   return;		
		}				
	}
}
