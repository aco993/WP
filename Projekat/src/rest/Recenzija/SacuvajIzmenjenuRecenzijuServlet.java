package rest.Recenzija;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Proizvod;
import beans.Recenzija;
import beans.Temp2;

/**
 * Servlet implementation class SacuvajIzmenjenuRecenzijuServlet
 */
@WebServlet("/SacuvajIzmenjenuRecenzijuServlet")
public class SacuvajIzmenjenuRecenzijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SacuvajIzmenjenuRecenzijuServlet() {
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
		Singleton.getInstance().deserializujRecenzije(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		

		ObjectMapper mapper = new ObjectMapper();
		
		Temp2 temp2 = mapper.readValue(jsonRequest, Temp2.class);
		
		int brojac=-1;
		for(Recenzija r: Singleton.getInstance().getRecenzije()){
			brojac++;
			if(r.getSifra().equals(temp2.getSifra())){
				r.setKomentar(temp2.getOcenka());
				break;
			}
		}

		//Singleton.getInstance().getProizvodi().set(brojac, proizvod);		
		Singleton.getInstance().serializujRecenzije(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujRecenzije(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(temp2);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
