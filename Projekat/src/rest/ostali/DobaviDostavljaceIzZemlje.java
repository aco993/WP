package rest.ostali;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Dostavljac;
import beans.Temp;

/**
 * Servlet implementation class DobaviDostavljaceIzZemlje
 */
@WebServlet("/DobaviDostavljaceIzZemlje")
public class DobaviDostavljaceIzZemlje extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DobaviDostavljaceIzZemlje() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		Temp zemljaDostavljaca = mapper.readValue(jsonRequest, Temp.class);
		//Dostavljac dostavljac = mapper.readValue(jsonRequest, Dostavljac.class);
		
		ArrayList<Dostavljac> tempDostavljaci=new ArrayList<Dostavljac>();
		
		Dostavljac dostavljac=null;
		
		boolean mozeSeDodati=true;
		for(Dostavljac dost: Singleton.getInstance().getDostavljaci()){
			if(dost.getDrzave().contains(zemljaDostavljaca.getNaslov())){
				dostavljac=dost;
				tempDostavljaci.add(dostavljac);
			}
		}

		String sArticles = mapper.writeValueAsString(tempDostavljaci);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
