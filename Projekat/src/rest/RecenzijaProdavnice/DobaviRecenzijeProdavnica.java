package rest.RecenzijaProdavnice;

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
import beans.Recenzija;
import beans.RecenzijaProdavnice;

/**
 * Servlet implementation class DobaviRecenzijeZaProdavnice
 */
@WebServlet("/DobaviRecenzijeProdavnica")
public class DobaviRecenzijeProdavnica extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DobaviRecenzijeProdavnica() {
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
		Singleton.deserializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));
		
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<RecenzijaProdavnice> recenzije= Singleton.getInstance().getRecenzijeProdavnica();
		ArrayList<RecenzijaProdavnice> recenzijeKorisnika = new ArrayList<RecenzijaProdavnice>();
		ArrayList<RecenzijaProdavnice> konacneRecenzije=new ArrayList<RecenzijaProdavnice>();
		
		Korisnik korisnik=(Korisnik)request.getSession().getAttribute("korisnik");
		
		if(korisnik.getUloga().equals("kupac")){
			for(RecenzijaProdavnice k : recenzije){
				System.out.println("dje si baka-"+konacneRecenzije.size());
				if(k.getKupac().equals(korisnik.getKorisnickoIme())){
					recenzijeKorisnika.add(k);
				}
			}
			konacneRecenzije=recenzijeKorisnika;
			System.out.println("od ovog korisnika ima recenzija baka-"+konacneRecenzije.size());
		
	}
		else {
			konacneRecenzije=recenzije;
			System.out.println("ukupno ima kupovina-"+konacneRecenzije.size());
		}
		
		String sArticles = mapper.writeValueAsString(konacneRecenzije);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
