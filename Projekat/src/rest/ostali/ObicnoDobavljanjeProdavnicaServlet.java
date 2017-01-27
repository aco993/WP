package rest.ostali;

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
import beans.Prodavnica;
import beans.Proizvod;

/**
 * Servlet implementation class ObicnoDobavljanjeProdavnicaServlet
 */
@WebServlet("/ObicnoDobavljanjeProdavnicaServlet")
public class ObicnoDobavljanjeProdavnicaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObicnoDobavljanjeProdavnicaServlet() {
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
		Singleton.getInstance().deserializujProdavnice(getServletContext().getRealPath("\\"));
		ObjectMapper mapper = new ObjectMapper();

		ArrayList<Prodavnica> prodavnice=Singleton.getInstance().getProdavnice();
		
		Korisnik korisnik=(Korisnik) request.getSession().getAttribute("korisnik");
		
		if(korisnik.getUloga().equals("prodavac")){
			prodavnice = new ArrayList<Prodavnica>();
			for(Prodavnica p : Singleton.getInstance().getProdavnice()){
				if(p.getOdgovorniProdavac().equals(korisnik.getKorisnickoIme())){
					prodavnice.add(p);
				}
			}
		}
		
		String sArticles = mapper.writeValueAsString(prodavnice);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
