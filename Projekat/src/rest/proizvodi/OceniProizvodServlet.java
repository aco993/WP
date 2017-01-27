package rest.proizvodi;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Proizvod;
import beans.Temp;
import beans.Temp2;

/**
 * Servlet implementation class OceniProizvodServlet
 */
@WebServlet("/OceniProizvodServlet")
public class OceniProizvodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OceniProizvodServlet() {
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
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		Temp2 temp2 = (Temp2) mapper.readValue(jsonRequest, Temp2.class);

		int brojac=-1;
		Proizvod proizvod=null;
		for(Proizvod proi: Singleton.getInstance().getProizvodi()){
			brojac++;
			if(proi.getSifra().equals(temp2.getSifra())){
				proizvod=proi;
				break;
			}
		}
		
		if(proizvod.getOcena()==0)
			proizvod.setOcena(Double.parseDouble(temp2.getOcenka()));
		else
			proizvod.setOcena((proizvod.getOcena()+Double.parseDouble(temp2.getOcenka()))/2);
		
		Singleton.getInstance().getProizvodi().set(brojac, proizvod);
		Singleton.getInstance().serializujProizvode(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(temp2);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
