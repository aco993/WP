package rest.Zalba;

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
import beans.Zalba;

/**
 * Servlet implementation class UloziZalbuServlet
 */
@WebServlet("/UloziZalbuServlet")
public class UloziZalbuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UloziZalbuServlet() {
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
		Zalba zalba = mapper.readValue(jsonRequest, Zalba.class);
		
		Singleton.getInstance().getZalbe().add(zalba);
		
		System.out.println("u bazi ima zalbi-"+Singleton.getInstance().getZalbe().size());
		Singleton.getInstance().serializujZalbe(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujZalbe(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(zalba);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
