package rest.kategorija;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.*;
import beans.KategorijaProizvoda;
import beans.Temp;
import beans.Proizvod;

/**
 * Servlet implementation class IzbrisiKategorijuServlet
 */
@WebServlet("/IzbrisiKategorijuServlet")
public class IzbrisiKategorijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzbrisiKategorijuServlet() {
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
		Singleton.getInstance().getKategorijeProizvoda().remove(kp);
		
		for(KategorijaProizvoda kate: Singleton.getInstance().getKategorijeProizvoda()){
			if(kate.getPodkategorija().equals(kp.getNaziv())){
				kate.setPodkategorija("Nema podkategoriju!");
			}
		}
		
		Singleton.getInstance().serializujKategorije(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujKategorije(getServletContext().getRealPath("\\"));
		
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		for(Proizvod p: Singleton.getInstance().getProizvodi()){
			if(p.getKategorijaProizvoda().equals(kp.getNaziv())){
				p.setKategorijaProizvoda("Nema kategoriju!");
			}
		}
		
		Singleton.getInstance().serializujProizvode(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		

		String sArticles = mapper.writeValueAsString(korisnik);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
