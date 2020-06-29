import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;


 
@WebServlet("/Sesja")
@WebListener
public class Sesja extends HttpServlet implements ServletRequestListener, HttpSessionListener {
	public static final String licznikSesja = "licznik";
	private static final long serialVersionUID = 1L;
 
    public Sesja() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter responseWriter = resp.getWriter();
        responseWriter.write("<html><body>");
        HttpSession session = req.getSession();
        //session.setAttribute("napiecie", "9_woltow");
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String attributeName = attributeNames.nextElement();
            responseWriter.write("<p>" + attributeName + ": " + session.getAttribute(attributeName) + "</p>");
        }
        responseWriter.write("</body></html>");
        increaseVisitCounter(session);
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
