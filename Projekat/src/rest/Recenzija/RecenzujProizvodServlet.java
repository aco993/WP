package rest.Recenzija;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Kupovina;
import beans.Proizvod;
import beans.Recenzija;

/**
 * Servlet implementation class RecenzujProizvodServlet
 */
@WebServlet("/RecenzujProizvodServlet")
public class RecenzujProizvodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecenzujProizvodServlet() {
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
		Recenzija recenzija = mapper.readValue(jsonRequest, Recenzija.class);
		
		Singleton.getInstance().getRecenzije().add(recenzija);
		
		System.out.println("u bazi ima recenzija-"+Singleton.getInstance().getRecenzije().size());
		Singleton.getInstance().serializujRecenzije(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujRecenzije(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(recenzija);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
