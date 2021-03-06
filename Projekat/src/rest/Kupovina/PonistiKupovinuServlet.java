package rest.Kupovina;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Korisnik;
import beans.Kupovina;
import beans.Proizvod;
import beans.Temp;

/**
 * Servlet implementation class PonistiKupovinuServlet
 */
@WebServlet("/PonistiKupovinuServlet")
public class PonistiKupovinuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PonistiKupovinuServlet() {
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
		Singleton.getInstance().deserializujKupovine(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		Temp temp = mapper.readValue(jsonRequest, Temp.class);
		
		String sifra = temp.getNaslov();
		//int indeks = Integer.parseInt(temp.getNaslov());
		Kupovina kupovina = null;
		
		ArrayList<Kupovina> kupovine=Singleton.getInstance().getKupovine();
		ArrayList<Kupovina> kupovineKorisnika=new ArrayList<Kupovina>();
		
		Korisnik korisnik=(Korisnik)request.getSession().getAttribute("korisnik");
		
		for(Kupovina k : kupovine){
			if(k.getKupac().equals(korisnik.getKorisnickoIme())){
				kupovineKorisnika.add(k);
			}
			if(k.getSifra().equals(sifra) && kupovina==null)
				kupovina=k;
		}

		kupovine.remove(kupovina);
		
		System.out.println("u bazi ima kupovina-"+Singleton.getInstance().getKupovine().size());
		Singleton.getInstance().serializujKupovine(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujKupovine(getServletContext().getRealPath("\\"));
		
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		
		Proizvod proizvod = null;
		
		int brojac=-1;
		for(Proizvod proi: Singleton.getInstance().getProizvodi()){
			brojac++;
			if(proi.getSifra().equals(kupovina.getSifraProizvoda())){
				proizvod=proi;
				break;
			}
		}
		
		proizvod.setKolicinaUMagacinu(proizvod.getKolicinaUMagacinu()+kupovina.getKolicina());

		Singleton.getInstance().getProizvodi().set(brojac, proizvod);		
		Singleton.getInstance().serializujProizvode(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));


		String sArticles = mapper.writeValueAsString(kupovina);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
