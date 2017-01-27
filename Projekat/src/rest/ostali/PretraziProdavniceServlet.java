package rest.ostali;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Korisnik;
import beans.Prodavnica;
import beans.Temp2;

/**
 * Servlet implementation class PretraziProdavniceServlet
 */
@WebServlet("/PretraziProdavniceServlet")
public class PretraziProdavniceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PretraziProdavniceServlet() {
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
		
		Singleton.getInstance().deserializujProdavnice(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		Temp2 temp2 = mapper.readValue(jsonRequest, Temp2.class);

		ArrayList<Prodavnica> prodavnice=Singleton.getInstance().getProdavnice();
		
		String naziv=(temp2.getSifra()).toLowerCase();
		String drzava=(temp2.getOcenka()).toLowerCase();
		
		ArrayList<Prodavnica> prodavniceZaPrikaz=new ArrayList<Prodavnica>();
		
		for(Prodavnica prodavnica : prodavnice){
			if(((prodavnica.getNaziv()).toLowerCase()).contains(naziv) || naziv.equals("")){
				if(((prodavnica.getDrzava()).toLowerCase()).contains(drzava) || drzava.equals("")){
					prodavniceZaPrikaz.add(prodavnica);
				}
			}
		}
		
		String sArticles = mapper.writeValueAsString(prodavniceZaPrikaz);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
