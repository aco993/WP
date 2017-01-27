package pictures;

import java.io.File;
import java.io.IOException;
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

import beans.Prodavnica;
import beans.Temp;

/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
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
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);

        // process only if it is multipart content
        if (isMultipart) {
        	
            // Create a factory for disk-based file items
            FileItemFactory factory = new DiskFileItemFactory();

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            try {
            // Parse the request
	            List<FileItem> multiparts = upload.parseRequest(request);
	
	            for (FileItem item : multiparts) {
	                if (!item.isFormField()) {
	                	String location = getServletContext().getRealPath("\\") + "\\baza";
	                	System.out.println(location);
		                String name = new File(item.getName()).getName();
		                item.write(new File(location + File.separator + name));
		                String loc = "baza\\" + name;
		                System.out.println(loc);
						request.getSession().setAttribute("slika",  loc);
						response.getWriter().write(loc);
						System.out.println((String)request.getSession().getAttribute("slika"));
	                }
	            }
            } 
	        catch (Exception e) 
	        {
	          e.printStackTrace();
	        }
        }
        response.getWriter().write((String)request.getSession().getAttribute("slika"));
	}

}
