package rest.RecenzijaProdavnice;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Recenzija;
import beans.RecenzijaProdavnice;
import beans.Temp;

/**
 * Servlet implementation class ObrisiRecenzijuPServlet
 */
@WebServlet("/ObrisiRecenzijuPServlet")
public class ObrisiRecenzijuPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ObrisiRecenzijuPServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Singleton.getInstance().deserializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		Temp temp = mapper.readValue(jsonRequest, Temp.class);
		
		String sifra = temp.getNaslov();
		//int indeks = Integer.parseInt(temp.getNaslov());
		RecenzijaProdavnice recenzija = null;
		
		ArrayList<RecenzijaProdavnice> recenzije=Singleton.getInstance().getRecenzijeProdavnica();
		
		for(RecenzijaProdavnice k : recenzije){
			if(k.getSifra().equals(sifra))
				recenzija=k;
		}

		recenzije.remove(recenzija);
		
		System.out.println("u bazi ima recenzija-"+Singleton.getInstance().getRecenzijeProdavnica().size());
		Singleton.getInstance().serializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));


		String sArticles = mapper.writeValueAsString(recenzija);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
