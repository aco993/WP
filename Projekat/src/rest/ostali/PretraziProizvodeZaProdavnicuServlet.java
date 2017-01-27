package rest.ostali;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import aplikacija.Singleton;
import beans.Prodavnica;
import beans.Proizvod;
import beans.Temp8;

/**
 * Servlet implementation class PretraziProizvodeZaProdavnicuServlet
 */
@WebServlet("/PretraziProizvodeZaProdavnicuServlet")
public class PretraziProizvodeZaProdavnicuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PretraziProizvodeZaProdavnicuServlet() {
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
		Singleton.getInstance().deserializujProizvode(getServletContext().getRealPath("\\"));
		String jsonRequest = request.getParameter("data");
		System.out.println(jsonRequest);
		
		ObjectMapper mapper = new ObjectMapper();
		Temp8 temp8 = (Temp8) mapper.readValue(jsonRequest, Temp8.class);

		ArrayList<Proizvod> proizvodi=Singleton.getInstance().getProizvodi();
		
		String prodavnica=(temp8.getA()).toLowerCase();
		String naziv=(temp8.getB()).toLowerCase();
		String opis=(temp8.getC()).toLowerCase();
		String kategorija=(temp8.getD()).toLowerCase();
		String zemljaProizvodnje=(temp8.getE()).toLowerCase();
		double ocena;
		if(temp8.getF().equals("")){
			ocena=0;
		}
		else
			ocena=Double.parseDouble(temp8.getF());
		
		double cenaVecaOd;
		if(temp8.getG().equals("")){
			cenaVecaOd=0;
		}else
			cenaVecaOd=Double.parseDouble(temp8.getG());
		double cenaManjaOd;
		if(temp8.getH().equals("")){
			cenaManjaOd=0;
		}else
			cenaManjaOd=Double.parseDouble(temp8.getH());
		
		ArrayList<Proizvod> proizvodZaPrikaz=new ArrayList<Proizvod>();
		
		for(Proizvod proizvod : proizvodi){
			if(((proizvod.getProdavnica()).toLowerCase()).equals(prodavnica) || prodavnica.equals("")){
				if(((proizvod.getNaziv()).toLowerCase()).contains(naziv) || naziv.equals("")){
					if(((proizvod.getKategorijaProizvoda()).toLowerCase()).contains(kategorija) || kategorija.equals("")){
						if(((proizvod.getZemljaProizvodnje()).toLowerCase()).contains(zemljaProizvodnje) || zemljaProizvodnje.equals("")){
							if(proizvod.getJedinicnaCena()>=cenaVecaOd){
								proizvodZaPrikaz.add(proizvod);
							}
							/*if(proizvod.getOcena()==ocena){
								if(proizvod.getJedinicnaCena()>=cenaVecaOd){
									if(proizvod.getJedinicnaCena()<=cenaManjaOd){
										proizvodZaPrikaz.add(proizvod);
									}
									
								}
							}*/
							
						}
					}
				}
			}
		}
		
		String sArticles = mapper.writeValueAsString(proizvodZaPrikaz);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
