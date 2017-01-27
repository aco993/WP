package rest.Recenzija;

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
import beans.Recenzija;

/**
 * Servlet implementation class DobaviRecenzijeServlet
 */
@WebServlet("/DobaviRecenzijeServlet")
public class DobaviRecenzijeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DobaviRecenzijeServlet() {
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
		Singleton.getInstance().deserializujRecenzije(getServletContext().getRealPath("\\"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<Recenzija> recenzije=Singleton.getInstance().getRecenzije();
		ArrayList<Recenzija> recenzijeKorisnika=new ArrayList<Recenzija>();
		ArrayList<Recenzija> konacneRecenzije=new ArrayList<Recenzija>();
		
		Korisnik korisnik=(Korisnik)request.getSession().getAttribute("korisnik");
		
		if(korisnik.getUloga().equals("kupac")){
			for(Recenzija k : recenzije){
				if(k.getKupac().equals(korisnik.getKorisnickoIme())){
					recenzijeKorisnika.add(k);
				}
			}
			konacneRecenzije=recenzijeKorisnika;
			System.out.println("od ovog korisnika ima recenzija-"+konacneRecenzije.size());
		}
		
		else{
			konacneRecenzije=recenzije;
			System.out.println("ukupno ima kupovina-"+konacneRecenzije.size());
		}
		
		String sArticles = mapper.writeValueAsString(konacneRecenzije);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
