package fr.Enchere.Servelt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.Enchere.BLL.GetDonneesUtilisationService;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;

/**
 * Servlet implementation class MonProfil
 */
@WebServlet("/MonProfil")
public class MonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MonProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = new Utilisateur();
		
		GetDonneesUtilisationService getDonneesUtilisationService = new GetDonneesUtilisationService();

		String string = "";

		try {
			utilisateur = getDonneesUtilisationService.selectById(Integer.parseInt(request.getParameter("idprof")));
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}

		request.setAttribute("utilisateur", utilisateur);
		
		if (request.getParameter("modif") != null) {
			
			request.getAttribute("utilisateur");
			
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("WEB-INF/pages/utilisateur/ModifProfil.jsp");
			requestDispatcher.forward(request, response);
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/utilisateur/MonProfil.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
