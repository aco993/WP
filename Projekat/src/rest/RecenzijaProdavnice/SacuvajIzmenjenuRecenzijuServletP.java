package rest.RecenzijaProdavnice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Recenzija;
import beans.RecenzijaProdavnice;
import beans.Temp2;

/**
 * Servlet implementation class SacuvajIzmenjenuRecenzijuServletP
 */
@WebServlet("/SacuvajIzmenjenuRecenzijuServletP")
public class SacuvajIzmenjenuRecenzijuServletP extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SacuvajIzmenjenuRecenzijuServletP() {
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
		Singleton.getInstance().deserializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		System.out.println("oces kekija");
		

		ObjectMapper mapper = new ObjectMapper();
		
		Temp2 temp2 = mapper.readValue(jsonRequest, Temp2.class);
		
		int brojac=-1;
		for(RecenzijaProdavnice r: Singleton.getInstance().getRecenzijeProdavnica()){
			brojac++;
			if(r.getSifra().equals(temp2.getSifra())){
				r.setKomentar(temp2.getOcenka());
				break;
			}
		}

		//Singleton.getInstance().getProizvodi().set(brojac, proizvod);		
		Singleton.getInstance().serializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(temp2);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
