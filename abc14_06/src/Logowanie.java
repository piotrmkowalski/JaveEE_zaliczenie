

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Servlet implementation class Logowanie
 */
@WebServlet("/Logowanie")
public class Logowanie extends HttpServlet implements ServletRequestListener, HttpSessionListener {
	private static final long serialVersionUID = 1L;
	public static final String licznikSesja = "licznik";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logowanie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=utf-8");
		PrintWriter responseWriter = response.getWriter();
		//responseWriter.append("Served at: ").append(request.getContextPath());
		
		String wartosc_droga= request.getParameter("s");
		String wartosc_czas= request.getParameter("t");
		// response.getWriter().append("s = " + wartosc_droga + " t = "+wartosc_czas);
        System.out.println("s = " + wartosc_droga + " t = "+wartosc_czas);
        
    	String nazwa   = "s";//wartosc_droga;//"predkosc";
    	String wartosc = wartosc_droga; //"5_metrow_na_sekunde";
    	Cookie ciastko        = new Cookie(nazwa  , wartosc);
    	Cookie ciastko_drugie = new Cookie("t", wartosc_czas);
    	
    	ciastko.setMaxAge(90);    	
    	response.addCookie(ciastko);
    	response.addCookie(ciastko_drugie);
    	    	
        responseWriter.write("<html><body>test  ");
        responseWriter.append("Parametry GET-a: s = " + wartosc_droga + " t = "+wartosc_czas);
        // ODCZYT      
    	Cookie[] ciastka = request.getCookies();    
    	for(Cookie ciasteczko: ciastka) {   		 
    		wartosc = ciasteczko.getValue(); 
    		nazwa   = ciasteczko.getName(); 
    		responseWriter.println("<br	> ciasteczo: " + nazwa +" : " + wartosc);
    	}
    	
    	HttpSession session = request.getSession();
        session.setAttribute("s", wartosc_droga);
        session.setAttribute("t", wartosc_czas);
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            responseWriter.write("<p> sesja: " + attributeName + ": " + session.getAttribute(attributeName) + "</p>");
        }
    	
        responseWriter.write("</body></html>");
        increaseVisitCounter(session);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void increaseVisitCounter(HttpSession session) {
        Object counter = session.getAttribute(licznikSesja);
        Integer licznikSerwer;
        if (counter != null) {
        	licznikSerwer = (Integer) counter + 1;
        }
        else {
        	licznikSerwer = 1;
        }
        session.setAttribute(licznikSesja, licznikSerwer);
    }
    
    public void requestDestroyed(ServletRequestEvent requestListener) {
        System.out.println("Request usunięty.");
    }

    public void requestInitialized(ServletRequestEvent requestListener) {
        System.out.println("Request rozpoczęty.");
    }
    
    public void sessionDestroyed(HttpSessionEvent sesja) {
        System.out.println("Sesja usunięta.");
    }

    public void sessionCreated(HttpSessionEvent sesja) {
        System.out.println("Sesja utworzona " + sesja.getSession().getId());
    }

}
