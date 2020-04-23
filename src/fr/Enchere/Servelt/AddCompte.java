package fr.Enchere.Servelt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.Enchere.BLL.GetDonneesUtilisationService;
import fr.Enchere.BLL.SetDonneesUtilisationService;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.CheckDataUtil;

/**
 * Servlet implementation class AddCompte
 */
@WebServlet("/AddCompte")
public class AddCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCompte() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/utilisateur/AddUtilisateur.jsp");
		requestDispatcher.forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String string = "";
		
		boolean userAdd = false;
		
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
		utilisateur.setAdministrateur(false);
		
		System.out.println(utilisateur.getPseudo().toString());
		
		try {
			CheckDataUtil.checkMotdePasseEqualeMotDePasseConfirmation(utilisateur.getMotDePasse(), request.getParameter("cfPass"));
			
			SetDonneesUtilisationService setDonneesUtilisationService = new SetDonneesUtilisationService();
			
			GetDonneesUtilisationService getDonneesUtilisationService = new GetDonneesUtilisationService();
			
			getDonneesUtilisationService.seleteEmailInBDD(utilisateur.getEmail());
			
			getDonneesUtilisationService.seletePseudoInBDD(utilisateur.getPseudo());
			
			string = setDonneesUtilisationService.insertUtilisateur(utilisateur);
			
			userAdd = true;
		} catch (ParameterException | BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			userAdd = false;
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		System.out.println(string);
		if(userAdd) {
			//RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/Connection");
			requestDispatcher.forward(request, response);
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/utilisateur/AddUtilisateur.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}

}
