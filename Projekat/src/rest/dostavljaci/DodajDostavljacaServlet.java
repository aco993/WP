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

/**
 * Servlet implementation class DodajDostavljacaServlet
 */
@WebServlet("/DodajDostavljacaServlet")
public class DodajDostavljacaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajDostavljacaServlet() {
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
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		Dostavljac dostavljac = mapper.readValue(jsonRequest, Dostavljac.class);
		
		boolean mozeSeDodati=true;
		for(Dostavljac dost: Singleton.getInstance().getDostavljaci()){
			if(dost.getSifra().equals(dostavljac.getSifra())){
				mozeSeDodati=false;
				break;
			}
		}
		
		if(mozeSeDodati){
			Singleton.getInstance().getDostavljaci().add(dostavljac);
			Singleton.getInstance().serializujDostavljace(getServletContext().getRealPath("\\"));
			Singleton.getInstance().deserializujDostavljace(getServletContext().getRealPath("\\"));
			
		}

		String sArticles = mapper.writeValueAsString(dostavljac);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
