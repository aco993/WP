package rest.kategorija;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.KategorijaProizvoda;

/**
 * Servlet implementation class SacuvajIzmenjenuKategorijuServlet
 */
@WebServlet("/SacuvajIzmenjenuKategorijuServlet")
public class SacuvajIzmenjenuKategorijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SacuvajIzmenjenuKategorijuServlet() {
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
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		

		ObjectMapper mapper = new ObjectMapper();
		
		KategorijaProizvoda korisnik = mapper.readValue(jsonRequest, KategorijaProizvoda.class);
		System.out.println(korisnik.getNaziv());
		
		int brojac=-1;
		KategorijaProizvoda kp=null;
		for(KategorijaProizvoda kate: Singleton.getInstance().getKategorijeProizvoda()){
			brojac++;
			if(kate.getNaziv().equals(korisnik.getNaziv())){
				kp=kate;
				System.out.println("NADJENO!");
				break;
			}
		}
		kp.setOpis(korisnik.getOpis());
		kp.setPodkategorija(korisnik.getPodkategorija());
		//Singleton.getInstance().getKategorijeProizvoda().get(brojac)=kp;
		Singleton.getInstance().getKategorijeProizvoda().set(brojac, kp);
		
		Singleton.getInstance().serializujKategorije(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujKategorije(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(kp);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
