package fr.Enchere.Servelt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.Enchere.BLL.SetDonneesUtilisationService;
import fr.Enchere.Exception.BllException;

/**
 * Servlet implementation class DeleteUtilisateur
 */
@WebServlet("/DeleteUtilisateur")
public class DeleteUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUtilisateur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String string = "";
		
		SetDonneesUtilisationService setDonneesUtilisationService = new SetDonneesUtilisationService();
		
		try {
			string = setDonneesUtilisationService.deleteUtilisateur(Integer.parseInt(request.getParameter("idDel")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		HttpSession theSession = request.getSession(false);
		
		if(theSession != null) {
			synchronized(theSession) {
				System.out.println("session deconnecter");
				theSession.invalidate();
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/utilisateur/Connection.jsp");
				requestDispatcher.forward(request, response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String string = "";
//		
//		SetDonneesUtilisationService setDonneesUtilisationService = new SetDonneesUtilisationService();
//		
//		try {
//			string = setDonneesUtilisationService.deleteUtilisateur(Integer.parseInt(request.getParameter("idDel")));
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			string = e.getMessage();
//		} catch (BllException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			string = e.getMessage();
//		}
//		
//		request.setAttribute("message", string);
//		
//		HttpSession theSession = request.getSession(false);
//		
//		if(theSession != null) {
//			synchronized(theSession) {
//				System.out.println("session deconnecter");
//				theSession.invalidate();
//				RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/utilisateur/Connection.jsp");
//				requestDispatcher.forward(request, response);
//			}
//		}
//		
//	}

}
	}
