package rest.prodavnice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Dostavljac;
import beans.Prodavnica;
import beans.Temp;

/**
 * Servlet implementation class IzmeniProdavnicuServlet
 */
@WebServlet("/IzmeniProdavnicuServlet")
public class IzmeniProdavnicuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmeniProdavnicuServlet() {
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
		String jsonRequest = request.getParameter("dataa");
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

		String sArticles = mapper.writeValueAsString(prodavnica);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
