package rest.kategorija;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.KategorijaProizvoda;
import beans.Temp;

/**
 * Servlet implementation class IzmeniKategorijuServlet
 */
@WebServlet("/IzmeniKategorijuServlet")
public class IzmeniKategorijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmeniKategorijuServlet() {
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
		Singleton.getInstance().deserializujKategorije(getServletContext().getRealPath("\\"));
		Temp temp=new Temp("izmena");
		request.getSession().setAttribute("proveraKategorije", temp);
		
		String jsonRequest = request.getParameter("dataa");
		System.out.println(jsonRequest);
		

		ObjectMapper mapper = new ObjectMapper();
		

		Temp korisnik = mapper.readValue(jsonRequest, Temp.class);
		System.out.println(korisnik.getNaslov());
		
		KategorijaProizvoda kp=null;
		for(KategorijaProizvoda kate: Singleton.getInstance().getKategorijeProizvoda()){
			if(kate.getNaziv().equals(korisnik.getNaslov())){
				kp=kate;
				System.out.println("NADJENO!");
				break;
			}
		}

		String sArticles = mapper.writeValueAsString(kp);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
		
	}

}
