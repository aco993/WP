package rest.prodavnice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.KategorijaProizvoda;
import beans.Prodavnica;

/**
 * Servlet implementation class DodajProdavnicuServlet
 */
@WebServlet("/DodajProdavnicuServlet")
public class DodajProdavnicuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajProdavnicuServlet() {
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
		mapper.setSerializationInclusion(Include.NON_NULL);
		Prodavnica prodavnica = mapper.readValue(jsonRequest, Prodavnica.class);
		
		boolean mozeSeDodati=true;
		for(Prodavnica prod: Singleton.getInstance().getProdavnice()){
			if(prod.getSifra().equals(prodavnica.getSifra())){
				mozeSeDodati=false;
				break;
			}
			if(prod.getNaziv().equals(prodavnica.getNaziv())){
				mozeSeDodati=false;
				break;
			}
		}
		
		if(mozeSeDodati){
			Singleton.getInstance().getProdavnice().add(prodavnica);
			Singleton.getInstance().serializujProdavnice(getServletContext().getRealPath("\\"));
			Singleton.getInstance().deserializujProdavnice(getServletContext().getRealPath("\\"));
		}

		String sArticles = mapper.writeValueAsString(prodavnica);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
