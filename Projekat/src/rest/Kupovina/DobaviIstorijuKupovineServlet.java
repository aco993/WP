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
import beans.KategorijaProizvoda;
import beans.Korisnik;
import beans.Kupovina;

/**
 * Servlet implementation class DobaviIstorijuKupovineServlet
 */
@WebServlet("/DobaviIstorijuKupovineServlet")
public class DobaviIstorijuKupovineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DobaviIstorijuKupovineServlet() {
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
		
		ObjectMapper mapper = new ObjectMapper();
		
		ArrayList<Kupovina> kupovine=Singleton.getInstance().getKupovine();
		ArrayList<Kupovina> kupovineKorisnika=new ArrayList<Kupovina>();
		
		Korisnik korisnik=(Korisnik)request.getSession().getAttribute("korisnik");
		
		for(Kupovina k : kupovine){
			if(k.getKupac().equals(korisnik.getKorisnickoIme())){
				kupovineKorisnika.add(k);
			}
		}
		
		System.out.println("od ovog korisnika ima kupovina-"+kupovineKorisnika.size());
		
		String sArticles = mapper.writeValueAsString(kupovineKorisnika);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
