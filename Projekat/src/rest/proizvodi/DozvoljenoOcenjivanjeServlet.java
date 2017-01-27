package rest.proizvodi;

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
import beans.Recenzija;
import beans.Temp;

/**
 * Servlet implementation class DozvoljenoOcenjivanjeServlet
 */
@WebServlet("/DozvoljenoOcenjivanjeServlet")
public class DozvoljenoOcenjivanjeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DozvoljenoOcenjivanjeServlet() {
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
		Temp temp = (Temp) mapper.readValue(jsonRequest, Temp.class);
		
		Korisnik korisnik = (Korisnik)request.getSession().getAttribute("korisnik");
		
		ArrayList<Kupovina> kupovine = Singleton.getInstance().getKupovine();
		
		String mogucnost="ne";
		
		for(Kupovina k : kupovine){
			if(k.getSifraProizvoda().equals(temp.getNaslov())){
				if(k.getKupac().equals(korisnik.getKorisnickoIme())){
					mogucnost="da";
					break;
				}
			}
		}

		String sArticles = mapper.writeValueAsString(mogucnost);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
