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

/**
 * Servlet implementation class RecenzujProdavnicuServlet
 */
@WebServlet("/RecenzujProdavnicuServlet")
public class RecenzujProdavnicuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecenzujProdavnicuServlet() {
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
		
		ObjectMapper mapper = new ObjectMapper();
		RecenzijaProdavnice recenzija = mapper.readValue(jsonRequest, RecenzijaProdavnice.class);
		
		Singleton.getInstance().getRecenzijeProdavnica().add(recenzija);
		
		System.out.println("u bazi ima recenzija prodavnica-"+Singleton.getInstance().getRecenzijeProdavnica().size());
		Singleton.getInstance().serializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujRecenzijeProdavnica(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(recenzija);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
