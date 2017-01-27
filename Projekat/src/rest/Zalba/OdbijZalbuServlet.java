package rest.Zalba;

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
import beans.Zalba;

/**
 * Servlet implementation class OdbijZalbuServlet
 */
@WebServlet("/OdbijZalbuServlet")
public class OdbijZalbuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OdbijZalbuServlet() {
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
		Singleton.getInstance().deserializujZalbe(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		Temp temp = mapper.readValue(jsonRequest, Temp.class);
		
		String sifra = temp.getNaslov();
		//int indeks = Integer.parseInt(temp.getNaslov());
		Zalba zalba = null;
		
		for(Zalba z : Singleton.getInstance().getZalbe()){
			if(z.getSifra().equals(sifra))
				zalba=z;
		}

		Singleton.getInstance().getZalbe().remove(zalba);
		
		System.out.println("u bazi ima zalbi-"+Singleton.getInstance().getZalbe().size());
		Singleton.getInstance().serializujZalbe(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujZalbe(getServletContext().getRealPath("\\"));
		
		String sArticles = mapper.writeValueAsString(zalba);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
