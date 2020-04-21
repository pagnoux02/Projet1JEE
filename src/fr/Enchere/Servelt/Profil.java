package fr.Enchere.Servelt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;

import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

import fr.Enchere.BLL.GetDonneesUtilisationService;
import fr.Enchere.BLL.SetDonneesUtilisationService;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.CheckDataUtil;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Utilisateur> lisUtilisateurs = new ArrayList<>();

		GetDonneesUtilisationService getDonneesUtilisationService = new GetDonneesUtilisationService();

		String string = "";

		try {
			lisUtilisateurs = getDonneesUtilisationService.selectAllUtisateur();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}

		request.setAttribute("listUtilisateur", lisUtilisateurs);

		if (request.getParameter("idProfil") != null) {
			Utilisateur utilisateur = new Utilisateur();

			try {
				utilisateur = getDonneesUtilisationService
						.selectById(Integer.parseInt(request.getParameter("idProfil")));
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

		if (request.getParameter("modif") != null) {
			
			Utilisateur utilisateur = new Utilisateur();

			try {
				utilisateur = getDonneesUtilisationService
						.selectById(Integer.parseInt(request.getParameter("idmod")));
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

			request.setAttribute("utilisateur", utilisateur);
			
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("WEB-INF/pages/utilisateur/ModifProfil.jsp");
			requestDispatcher.forward(request, response);
		} else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/utilisateur/Profil.jsp");
			requestDispatcher.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String string = "";
		
		boolean updateBdd = false;

		Utilisateur utilisateur = new Utilisateur();

		utilisateur.setPseudo(request.getParameter("pseudo"));
		utilisateur.setNom(request.getParameter("nom"));
		utilisateur.setPrenom(request.getParameter("prenom"));
		utilisateur.setEmail(request.getParameter("email"));
		utilisateur.setTelephone(request.getParameter("telephone"));
		utilisateur.setRue(request.getParameter("rue"));
		utilisateur.setCodePostal(request.getParameter("codePostal"));
		utilisateur.setVille(request.getParameter("ville"));
		utilisateur.setMotDePasse(request.getParameter("pass"));
		utilisateur.setNumeroUtilisateur(Integer.parseInt(request.getParameter("IdUtil")));
		utilisateur.setAdministrateur(CheckDataUtil.estAdministrateur(request.getParameter("ad")));

		SetDonneesUtilisationService setDonneesUtilisationService = new SetDonneesUtilisationService();

		GetDonneesUtilisationService getDonneesUtilisationService = new GetDonneesUtilisationService();

		try {
			Utilisateur utilisateurControl = getDonneesUtilisationService
					.selectById(Integer.parseInt(request.getParameter("IdUtil")));

			CheckDataUtil.checkAncienMotdePasse(utilisateurControl.getMotDePasse(),
					CheckDataUtil.convertirMotdePasse(request.getParameter("AncPass")));

			CheckDataUtil.checkMotdePasseEqualeMotDePasseConfirmation(utilisateur.getMotDePasse(),
					request.getParameter("cfPass"));

			string = setDonneesUtilisationService.updateUtilisateur(utilisateur);
			
			updateBdd = true;
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		} catch (FunctionnalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		if(updateBdd) {
			doGet(request, response);
		}else {
			RequestDispatcher requestDispatcher = request
					.getRequestDispatcher("WEB-INF/pages/utilisateur/ModifProfil.jsp");
			requestDispatcher.forward(request, response);
		}

	}

}
