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

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import fr.Enchere.BLL.GetDonneesUtilisationService;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;

/**
 * Servlet implementation class Profil
 */
@WebServlet("/Profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Utilisateur> lisUtilisateurs = new ArrayList<>();
		
		GetDonneesUtilisationService getDonneesUtilisationService = new GetDonneesUtilisationService();
		
		String string = "";
		
		try {
			lisUtilisateurs = getDonneesUtilisationService.selectAllUtisateur();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		request.setAttribute("listUtilisateur", lisUtilisateurs);

		if(request.getParameter("idProfil") != null) {
			Utilisateur utilisateur = new Utilisateur();
			
			try {
				utilisateur = getDonneesUtilisationService.selectById(Integer.parseInt(request.getParameter("idProfil")));
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
			
			request.setAttribute("userProfil", utilisateur);
			
		}
		
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/utilisateur/Profil.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
