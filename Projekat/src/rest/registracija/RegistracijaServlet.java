package rest.registracija;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Korisnik;

/**
 * Servlet implementation class RegistracijaServlet
 */
@WebServlet("/RegistracijaServlet")
public class RegistracijaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistracijaServlet() {
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
		Singleton.getInstance();
		// TODO Auto-generated method stub
		Singleton.getInstance().deserializujKorisnike(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);

		ObjectMapper mapper = new ObjectMapper();
		Korisnik korisnik = mapper.readValue(jsonRequest, Korisnik.class);
		System.out.println(korisnik.getKorisnickoIme());
		
		boolean uspesnaRegistracija = Singleton.proveriRegistraciju(korisnik);
		String razlog;
		if(uspesnaRegistracija){
			razlog="Nazalost, korisnicko ime vec postoji! Pokusajte sa nekim drugim korisnickim imenom :)";
		} 
		else{
			razlog="Proslo ok :)";
			Singleton.getInstance().getKorisnici().add(korisnik);
			Singleton.getInstance().serializujKorisnike(getServletContext().getRealPath("\\"));
		}
		
		RegistracijaDTO reg = new RegistracijaDTO(uspesnaRegistracija, razlog, korisnik);
		String sArticles = mapper.writeValueAsString(reg);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}
}
