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
import beans.Kupovina;
import beans.Proizvod;
import beans.Temp;

/**
 * Servlet implementation class MogucnostOcenjivanjaProdavniceServlet
 */
@WebServlet("/MogucnostOcenjivanjaProdavniceServlet")
public class MogucnostOcenjivanjaProdavniceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MogucnostOcenjivanjaProdavniceServlet() {
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
		
		ArrayList <String> proizvodi = new ArrayList<String>();
		
		for(Kupovina k : kupovine){
			if(k.getKupac().equals(korisnik.getKorisnickoIme())){
				proizvodi.add(k.getSifraProizvoda());
			}
		}
		
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		for(String s : proizvodi){
			for(Proizvod k :Singleton.getInstance().getProizvodi()){
				if(s.equals(k.getSifra())){
					if(k.getProdavnica().equals(temp.getNaslov())){
						mogucnost="da";
					}
				}
			}
		}
		

		String sArticles = mapper.writeValueAsString(mogucnost);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
