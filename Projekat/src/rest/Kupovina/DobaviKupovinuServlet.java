package rest.Kupovina;

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
import beans.Kupovina;
import beans.Proizvod;
import beans.Temp;

/**
 * Servlet implementation class DobaviKupovinuServlet
 */
@WebServlet("/DobaviKupovinuServlet")
public class DobaviKupovinuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DobaviKupovinuServlet() {
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
		Singleton.getInstance().deserializujKupovine(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		Temp temp = mapper.readValue(jsonRequest, Temp.class);
		
		String sifra = temp.getNaslov();
		//int indeks = Integer.parseInt(temp.getNaslov());
		Kupovina kupovina = null;
		
		ArrayList<Kupovina> kupovine=Singleton.getInstance().getKupovine();
		
		for(Kupovina k : kupovine){
			if(k.getSifra().equals(sifra)){
				kupovina=k;
			}
		}

		String sArticles = mapper.writeValueAsString(kupovina);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
