package rest.logovanje;

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
import rest.registracija.RegistracijaDTO;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
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
		Singleton.getInstance().deserializujKorisnike(getServletContext().getRealPath("\\"));
		
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);

		ObjectMapper mapper = new ObjectMapper();
		TempKorisnik temp = mapper.readValue(jsonRequest, TempKorisnik.class);
		System.out.println(temp.getKorisnickoIme());
		
		Korisnik korisnik = Singleton.getInstance().proveriLogovanje(temp);
		boolean uspesnaRegistracija=false;
		String razlog;
		if(korisnik==null){
			uspesnaRegistracija=false;
			razlog="Uneli ste ili pogresnu lozinku ili pogresno korisnicko ime!";
		} 
		else{
			uspesnaRegistracija=true;
			razlog="Proslo OK!";
			request.getSession().setAttribute("korisnik", korisnik);
		}
		
		LogovanjeDTO reg = new LogovanjeDTO(uspesnaRegistracija, razlog, korisnik);
		String sArticles = mapper.writeValueAsString(reg);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
