package rest.dostavljaci;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Dostavljac;
import beans.KategorijaProizvoda;
import beans.Temp;

/**
 * Servlet implementation class IzmeniDostavljacaServlet
 */
@WebServlet("/IzmeniDostavljacaServlet")
public class IzmeniDostavljacaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmeniDostavljacaServlet() {
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
		Singleton.getInstance().deserializujDostavljace(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("dataa");
		System.out.println(jsonRequest);

		ObjectMapper mapper = new ObjectMapper();
		Temp korisnik = mapper.readValue(jsonRequest, Temp.class);
		System.out.println(korisnik.getNaslov());
		
		Dostavljac kp=null;
		for(Dostavljac kate: Singleton.getInstance().getDostavljaci()){
			if(kate.getSifra().equals(korisnik.getNaslov())){
				kp=kate;
				break;
			}
		}

		String sArticles = mapper.writeValueAsString(kp);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
