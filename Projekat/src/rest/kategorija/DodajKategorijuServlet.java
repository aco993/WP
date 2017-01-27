package rest.kategorija;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;


import aplikacija.Singleton;
import beans.KategorijaProizvoda;
import beans.Temp;

/**
 * Servlet implementation class DodajKategorijuServlet
 */
@WebServlet("/DodajKategorijuServlet")
public class DodajKategorijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajKategorijuServlet() {
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
		Temp temp=new Temp("dodavanje");
		request.getSession().setAttribute("proveraKategorije", temp);
		
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Include.NON_NULL);
		KategorijaProizvoda korisnik = mapper.readValue(jsonRequest, KategorijaProizvoda.class);
		System.out.println(korisnik.getPodkategorija());
		
		boolean mozeSeDodati=true;
		for(KategorijaProizvoda kate: Singleton.getInstance().getKategorijeProizvoda()){
			if(kate.getNaziv().equals(korisnik.getNaziv())){
				mozeSeDodati=false;
				break;
			}
		}
		
		if(mozeSeDodati){
			Singleton.getInstance().getKategorijeProizvoda().add(korisnik);
			Singleton.getInstance().serializujKategorije(getServletContext().getRealPath("\\"));
			Singleton.getInstance().deserializujKategorije(getServletContext().getRealPath("\\"));
		}

		String sArticles = mapper.writeValueAsString(korisnik);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
