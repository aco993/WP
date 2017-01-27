package rest.proizvodi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Korisnik;
import beans.Proizvod;
import beans.Temp;

/**
 * Servlet implementation class ProizvodDobaviSifru
 */
@WebServlet("/ProizvodDobaviSifru")
public class ProizvodDobaviSifru extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProizvodDobaviSifru() {
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
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);

		ObjectMapper mapper = new ObjectMapper();
		Temp korisnik = mapper.readValue(jsonRequest, Temp.class);
		System.out.println("SAD SMO U NOVOKOMPONOVANOM SERVLETU SA NASLOVOM-"+korisnik.getNaslov());

		request.getSession().setAttribute("sifraProizvoda", korisnik.getNaslov());
		
		
		String sArticles=mapper.writeValueAsString((Korisnik)request.getSession().getAttribute("korisnik"));
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
		
	}

}
