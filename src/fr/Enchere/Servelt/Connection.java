package fr.Enchere.Servelt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
					response.sendRedirect(request.getContextPath() + "/Connection");
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
				//string = "il y a un utilisateur qui est :" + utilisateur.toString();
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
			if(request.getParameter("savePass") != null) {
				System.out.println("test");
				System.out.println(request.getParameter("savePass"));
				
				Cookie[] cookies = request.getCookies();
				
				Cookie passCookie = null;
				
				if(cookies == null) {
					
				}
				
				List<Cookie> lisCookies = Arrays.asList(cookies);
				
				boolean cookieTrouver = false;
				
				for (Cookie cookie : cookies) {
					if("PassENIEnchere".equals(cookie.getName())) {
						cookieTrouver = true;
					}
				}
				if(!cookieTrouver) {
					passCookie = new Cookie("PassENIEnchere", pass);
					passCookie.setMaxAge(88400);
					response.addCookie(passCookie);
				} 
			}
			
			System.out.println("testSorte");
			
			HttpSession session = request.getSession();
			
			session.setAttribute("userTrouver", userTrouver);
			
			session.setAttribute("user", utilisateur);
			
			System.out.println("session cr�e");
			
			String res = "";
			
			response.sendRedirect(request.getContextPath() + "/ListEnchereCo");
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/utilisateur/Connection.jsp");
			requestDispatcher.forward(request, response);
		}
	}
}