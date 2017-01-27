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

/**
 * Servlet implementation class SacuvajIzmenjenuProdavnicuServlet
 */
@WebServlet("/SacuvajIzmenjenuProdavnicuServlet")
public class SacuvajIzmenjenuProdavnicuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SacuvajIzmenjenuProdavnicuServlet() {
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
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		

		ObjectMapper mapper = new ObjectMapper();
		
		Prodavnica prodavnica = mapper.readValue(jsonRequest, Prodavnica.class);
		
		int brojac=-1;
		for(Prodavnica prod: Singleton.getInstance().getProdavnice()){
			brojac++;
			if(prod.getSifra().equals(prodavnica.getSifra())){
				break;
			}
		}

		Singleton.getInstance().getProdavnice().set(brojac, prodavnica);
		Singleton.getInstance().serializujProdavnice(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujProdavnice(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(prodavnica);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
