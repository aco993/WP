package rest.kategorija;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.KategorijaProizvoda;
import beans.Korisnik;
import rest.registracija.RegistracijaDTO;

/**
 * Servlet implementation class DobaviKategorijeServlet
 */
@WebServlet("/DobaviKategorijeServlet")
public class DobaviKategorijeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DobaviKategorijeServlet() {
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
		
		ObjectMapper mapper = new ObjectMapper();

		ArrayList<KategorijaProizvoda> kategorije=Singleton.getInstance().getKategorijeProizvoda();
		
		String sArticles = mapper.writeValueAsString(kategorije);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
