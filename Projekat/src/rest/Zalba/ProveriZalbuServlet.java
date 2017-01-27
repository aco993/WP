package rest.Zalba;

import java.io.IOException;
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
import beans.Temp2;
import beans.Zalba;

/**
 * Servlet implementation class ProveriZalbuServlet
 */
@WebServlet("/ProveriZalbuServlet")
public class ProveriZalbuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProveriZalbuServlet() {
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
		String jsonRequest = request.getParameter("dataa");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		Temp2 temp2 = (Temp2) mapper.readValue(jsonRequest, Temp2.class);
		
		Korisnik korisnik=(Korisnik)request.getSession().getAttribute("korisnik");
		
		Zalba zalba=null;
		
		for(Zalba z : Singleton.getInstance().getZalbe()){
			if(z.getSifraKupovine().equals(temp2.getSifra())){
				zalba=z;
				break;
			}
		}

		String sArticles;
		if(zalba!=null){
			sArticles = mapper.writeValueAsString(zalba);
		}else{
			String opis="";
			sArticles=mapper.writeValueAsString(new Zalba(temp2.getOcenka(),temp2.getSifra(),korisnik.getKorisnickoIme(),opis));
		}
			
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
