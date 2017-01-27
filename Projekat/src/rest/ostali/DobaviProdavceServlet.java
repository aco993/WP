package rest.ostali;

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

/**
 * Servlet implementation class DobaviProdavceServlet
 */
@WebServlet("/DobaviProdavceServlet")
public class DobaviProdavceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DobaviProdavceServlet() {
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
		ObjectMapper mapper = new ObjectMapper();

		ArrayList<Korisnik> prodavci= new ArrayList<Korisnik>();
		ArrayList<Korisnik> korisnici=Singleton.getInstance().getKorisnici();
		
		for(Korisnik k : korisnici){
			if(k.getUloga().equals("prodavac")){
				prodavci.add(k);
				System.out.println("naso");
			}
		}
		
		
		String sArticles = mapper.writeValueAsString(prodavci);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
