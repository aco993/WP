package rest.RecenzijaProdavnice;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Prodavnica;
import beans.Proizvod;
import beans.Temp2;

/**
 * Servlet implementation class OceniProdavnicuServlet
 */
@WebServlet("/OceniProdavnicuServlet")
public class OceniProdavnicuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OceniProdavnicuServlet() {
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
		Temp2 temp2 = (Temp2) mapper.readValue(jsonRequest, Temp2.class);

		int brojac=-1;
		Prodavnica prodavnica=null;
		for(Prodavnica prod: Singleton.getInstance().getProdavnice()){
			brojac++;
			if(prod.getNaziv().equals(temp2.getSifra())){
				prodavnica=prod;
				break;
			}
		}
		
		System.out.println("IME PRODAVNICEEEEEEE +++++ "+prodavnica.getNaziv());
		
		if(prodavnica.getOcena()==0)
			prodavnica.setOcena(Double.parseDouble(temp2.getOcenka()));
		else
			prodavnica.setOcena((prodavnica.getOcena()+Double.parseDouble(temp2.getOcenka()))/2);
		
		Singleton.getInstance().getProdavnice().set(brojac, prodavnica);
		Singleton.getInstance().serializujProdavnice(getServletContext().getRealPath("\\"));
		Singleton.getInstance().deserializujProdavnice(getServletContext().getRealPath("\\"));

		String sArticles = mapper.writeValueAsString(temp2);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
