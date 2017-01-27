package rest.proizvodi;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import aplikacija.Singleton;
import beans.*;

/**
 * Servlet implementation class DodajProizvodServlet
 */
@WebServlet("/DodajProizvodServlet")
public class DodajProizvodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajProizvodServlet() {
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
		
		System.out.println("USAO SAM U JEBENI SERVLET!!!");
		
		String naslov=(String) request.getSession().getAttribute("sifraProizvoda");
		System.out.println("DOOOOOOOOOOBIJENI NASLOV JEEEE "+naslov);
		
		if (!ServletFileUpload.isMultipartContent(request)) {
            PrintWriter writer = response.getWriter();
            writer.println("Request does not contain upload data");
            writer.flush();
            System.out.println("prekinuto 1");
            return;
        }
		boolean uspesno = true;
        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(90000);

        ServletFileUpload upload = new ServletFileUpload(factory);
        //upload.setFileSizeMax(MAX_FILE_SIZE);
        //upload.setSizeMax(MAX_REQUEST_SIZE);
		File f = new File("test");
		System.out.println("PUTANJA DO TOMCAT:" + f.getAbsolutePath());
		 String naziv = naslov;
		 String video = "";
		 String slika= "";
		 String slika1="";
			 String video1="";
		 
		 ArrayList<String> nazivi=new ArrayList<String>();

        
       
        FileItem itemFile = null;
        
        try {
            // parses the request's content to extract file data
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();
            
            // iterates over form's fields to get UUID Value
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                if (item.isFormField()) {
                   System.out.println("element sa forme" + item.getName() + "vrednoist" + item.getString());
                }
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                    itemFile = item;
                    String putanja = "";
                    String fileName = itemFile.getName();
                    System.out.println("ime fajla: "+fileName);
                    String fieldName = item.getFieldName();
                    System.out.println("ime polja: "+fieldName);
                    InputStream fileContent = item.getInputStream();
	                if(fieldName.contains("slika")) {
	                	System.out.println("usao u sliku");
	                	putanja = "proizvod_" + naziv + "_slika_" + fieldName;
	                	slika=putanja;                	
	                }
	                else if (fieldName.contains("video")) {
	                	putanja = "proizvod_" + naziv + "_video_" + fieldName; 
	                	video = putanja;
	                }
	                
	                /*String do_fajla_putanja=getServletContext().getRealPath("/")+"\\baza"+ File.separator + putanja;
	                
	                File file = new File(do_fajla_putanja);
	                //item.write(file);
	                Files.copy(fileContent, file.toPath());*/
	                
	                //nazivi.add(putanja);
	                
	                File file = new File(getServletContext().getRealPath("") + "\\" + putanja);
	                System.out.println("path:::" + file.toPath());
	                Files.copy(fileContent, file.toPath());
	         
                }
                System.out.println("FILE NAME IS : "+itemFile.getName());
                
            }
            System.out.println("no of items: " + formItems.size());
        
        }catch(Exception e) {
        	e.printStackTrace();
        	uspesno = false;
        }
        
        for(String s:nazivi){
        	System.out.println("FAJL SA PUTANJOM---"+s);
        }
        
        nazivi.add(slika);
        nazivi.add(video);
        
        
        
        ObjectMapper mapper = new ObjectMapper();	
		String sArticles = mapper.writeValueAsString(nazivi);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(sArticles);
	}

}
