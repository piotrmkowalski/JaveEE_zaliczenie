import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Ciasteczko
 */
@WebServlet("/Ciasteczko")
public class Ciasteczko extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public Ciasteczko() {
        super();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html; charset=utf-8");
    	PrintWriter responseWriter = resp.getWriter();    
    	// ZAPIS
    	String nazwa   = "predkosc";
    	String wartosc = "5_metrow_na_sekunde";
    	Cookie ciastko        = new Cookie(nazwa  , wartosc);
    	Cookie ciastko_drugie = new Cookie("sila", "8_niutonow");
    	
    	ciastko.setMaxAge(90);    	
    	resp.addCookie(ciastko);
    	resp.addCookie(ciastko_drugie);
    	    	
        responseWriter.write("<html><body>test");
        // ODCZYT      
    	Cookie[] ciastka = req.getCookies();    
    	for(Cookie ciasteczko: ciastka) {   		 
    		wartosc = ciasteczko.getValue(); 
    		nazwa   = ciasteczko.getName(); 
    		responseWriter.println("<br	>" + nazwa +" : " + wartosc);
    	}       
        responseWriter.write("</body></html>");

    }
}
