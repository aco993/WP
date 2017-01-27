package rest.Kupovina;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Dostavljac;
import beans.Kupovina;
import beans.Proizvod;

/**
 * Servlet implementation class KupiProizvodServlet
 */
@WebServlet("/KupiProizvodServlet")
public class KupiProizvodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KupiProizvodServlet() {
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
		Kupovina kupovina = mapper.readValue(jsonRequest, Kupovina.class);
		
		Singleton.getInstance().getKupovine().add(kupovina);
		
		System.out.println("u bazi ima kupovina-"+Singleton.getInstance().getKupovine().size());
		Singleton.getInstance().serializujKupovine(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujKupovine(getServletContext().getRealPath("\\"));
		
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		
		Proizvod proizvod = null;
		
		int brojac=-1;
		for(Proizvod proi: Singleton.getInstance().getProizvodi()){
			brojac++;
			if(proi.getSifra().equals(kupovina.getSifraProizvoda())){
				proizvod=proi;
				break;
			}
		}
		
		proizvod.setKolicinaUMagacinu(proizvod.getKolicinaUMagacinu()-kupovina.getKolicina());

		Singleton.getInstance().getProizvodi().set(brojac, proizvod);		
		Singleton.getInstance().serializujProizvode(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));


		String sArticles = mapper.writeValueAsString(kupovina);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
