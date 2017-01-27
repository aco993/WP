package rest.proizvodi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Prodavnica;
import beans.Proizvod;
import beans.Temp;

/**
 * Servlet implementation class IzbrisiProizvodServlet
 */
@WebServlet("/IzbrisiProizvodServlet")
public class IzbrisiProizvodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzbrisiProizvodServlet() {
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
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);

		ObjectMapper mapper = new ObjectMapper();
		Temp korisnik = mapper.readValue(jsonRequest, Temp.class);
		System.out.println(korisnik.getNaslov());
		
		Proizvod proizvod=null;
		for(Proizvod proi: Singleton.getInstance().getProizvodi()){
			if(proi.getSifra().equals(korisnik.getNaslov())){
				proizvod=proi;
				break;
			}
		}
		
		Singleton.getInstance().getProizvodi().remove(proizvod);
		Singleton.getInstance().serializujProizvode(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(korisnik);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
