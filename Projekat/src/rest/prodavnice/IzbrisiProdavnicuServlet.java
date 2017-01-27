package rest.prodavnice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Dostavljac;
import beans.Prodavnica;
import beans.Proizvod;
import beans.Recenzija;
import beans.RecenzijaProdavnice;
import beans.Temp;

/**
 * Servlet implementation class IzbrisiProdavnicuServlet
 */
@WebServlet("/IzbrisiProdavnicuServlet")
public class IzbrisiProdavnicuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzbrisiProdavnicuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//brisanje prodavnice
		Singleton.getInstance().deserializujProdavnice(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);

		ObjectMapper mapper = new ObjectMapper();
		Temp korisnik = mapper.readValue(jsonRequest, Temp.class);
		System.out.println(korisnik.getNaslov());
		
		Prodavnica prodavnica=null;
		for(Prodavnica prod: Singleton.getInstance().getProdavnice()){
			if(prod.getSifra().equals(korisnik.getNaslov())){
				prodavnica=prod;
				break;
			}
		}
		
		Singleton.getInstance().getProdavnice().remove(prodavnica);
		Singleton.getInstance().serializujProdavnice(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujProdavnice(getServletContext().getRealPath("\\"));
		
		//brisanje recenzija prodavnica
		Singleton.getInstance().deserializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));

		ArrayList <RecenzijaProdavnice> recProd=Singleton.getInstance().getRecenzijeProdavnica();
		
		ArrayList <RecenzijaProdavnice> obrisatiRecProd=new ArrayList<RecenzijaProdavnice>();
		
		for(RecenzijaProdavnice rp : recProd){
			if(rp.getNazivProdavnice().equals(prodavnica.getNaziv())){
				obrisatiRecProd.add(rp);
			}
		}
		
		for(RecenzijaProdavnice rp : obrisatiRecProd){
			recProd.remove(rp);
		}
		
		Singleton.getInstance().serializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));
		
		
		
		//brisanje proizvoda
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		
		ArrayList <Proizvod> proizvodi=Singleton.getInstance().getProizvodi();
		
		ArrayList <Proizvod> obrisatiProizvode=new ArrayList<Proizvod>();
		
		for(Proizvod p : Singleton.getInstance().getProizvodi()){
			if(p.getProdavnica().equals(prodavnica.getNaziv())){
				obrisatiProizvode.add(p);
			}
		}
		
		for(Proizvod p : obrisatiProizvode){
			proizvodi.remove(p);
		}
		
		Singleton.getInstance().serializujProizvode(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		
		//brisanje recenzija proizvoda
		Singleton.getInstance().deserializujRecenzije(getServletContext().getRealPath("\\"));
		
		ArrayList <Recenzija> recenzije=Singleton.getInstance().getRecenzije();
		
		ArrayList <Recenzija> obrisatiRecenzije=new ArrayList<Recenzija>();
		
		for(Proizvod p : obrisatiProizvode){
			for(Recenzija r : recenzije){
				if(r.getSifraProizvoda().equals(p.getSifra())){
					obrisatiRecenzije.add(r);
				}
			}
		}
		
		for(Recenzija r : obrisatiRecenzije){
			recenzije.remove(r);
		}
		
		Singleton.getInstance().serializujRecenzije(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujRecenzije(getServletContext().getRealPath("\\"));
		
		
		

		String sArticles = mapper.writeValueAsString(korisnik);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
