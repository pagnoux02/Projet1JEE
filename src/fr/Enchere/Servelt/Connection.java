package fr.Enchere.Servelt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.Enchere.BLL.GetDonneesUtilisationService;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;
import fr.Enchere.util.CheckDataUtil;

/**
 * Servlet implementation class Connection
 */
@WebServlet("/Connection")
public class Connection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		if(request.getParameter("LogOut") != null) {
			HttpSession theSession = request.getSession(false);
		
			if(theSession != null) {
				synchronized(theSession) {
					System.out.println("session deconnecter");
					theSession.invalidate();
					response.sendRedirect("index.jsp");
				}
			}
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/utilisateur/Connection.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String string = "";
		
		String pseudo = request.getParameter("pseudo");
		
		String pass = request.getParameter("pass");
		
		boolean userTrouver = false;
		
		Utilisateur utilisateur = new Utilisateur();
		
		GetDonneesUtilisationService getDonneesUtilisationService = new GetDonneesUtilisationService();
		
		try {
			utilisateur = getDonneesUtilisationService.selectUserInBDD(pseudo, pass);
			
			if(utilisateur != null && pseudo.equals(utilisateur.getPseudo()) && CheckDataUtil.convertirMotdePasse(pass).equals(utilisateur.getMotDePasse())) {
				string = "il y a un utilisateur qui est :" + utilisateur.toString();
				userTrouver = true;
			}
		} catch (BllException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		System.out.println(string);
		if(userTrouver) {
			HttpSession session = request.getSession();
			
			session.setAttribute("userTrouver", userTrouver);
			
			session.setAttribute("user", utilisateur);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/utilisateur/Connection.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}
