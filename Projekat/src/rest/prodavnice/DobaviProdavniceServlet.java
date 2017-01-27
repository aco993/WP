package rest.prodavnice;

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
import beans.Prodavnica;

/**
 * Servlet implementation class DobaviProdavniceServlet
 */
@WebServlet("/DobaviProdavniceServlet")
public class DobaviProdavniceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DobaviProdavniceServlet() {
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
		Korisnik korisnik=(Korisnik) request.getSession().getAttribute("korisnik");
		System.out.println(korisnik.getUloga());
		ArrayList<Prodavnica> prodavnicaProdavca=new ArrayList<Prodavnica>();	
		
		Singleton.getInstance().deserializujProdavnice(getServletContext().getRealPath("\\"));
		ObjectMapper mapper = new ObjectMapper();

		ArrayList<Prodavnica> prodavnice=Singleton.getInstance().getProdavnice();
		
		if(korisnik.getUloga().equals("administrator") || korisnik.getUloga().equals("kupac")){
			System.out.println("usao u administratora");
			System.out.println(prodavnice.size());
			String sArticles = mapper.writeValueAsString(prodavnice);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(sArticles);
		}
		else{
			System.out.println("usao u ono drugo");
			for(Prodavnica p : prodavnice){
				if(p.getOdgovorniProdavac().equals(korisnik.getKorisnickoIme())){
					prodavnicaProdavca.add(p);
				}
			}
			String sArticles = mapper.writeValueAsString(prodavnicaProdavca);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(sArticles);
		}
	}

}
