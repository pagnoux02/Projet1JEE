package fr.Enchere.Servelt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.Enchere.BLL.ArticleVenduService;
import fr.Enchere.BLL.CategorieService;
import fr.Enchere.BLL.RetraitManager;
import fr.Enchere.BLL.RetraitService;
import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.Categorie;
import fr.Enchere.BO.EtatVente;
import fr.Enchere.BO.Retrait;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.ParameterException;
import jdk.nashorn.internal.ir.RuntimeNode.Request;

/**
 * Servlet implementation class ModifArticle
 */
@WebServlet("/ModifArticle")
public class ModifArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleVendu articleVendu = new ArticleVendu();
		
		ArticleVenduService articleVenduService = new ArticleVenduService();
		
		String string = "";
		
		try {
			articleVendu = articleVenduService.getArticleVendu(Integer.parseInt(request.getParameter("idArt")));
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
		
		request.setAttribute("updArticle", articleVendu);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/article/AddArticle.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		System.out.println("servlet Update article");
		
		String string = "";
		
		String file = request.getParameter("file");
		
		System.out.println(file);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dateDebutEnchere = LocalDate.parse(request.getParameter("debutEnch"), dateTimeFormatter);
		
		LocalDate dateFinEnchere = LocalDate.parse(request.getParameter("finEnch"), dateTimeFormatter);
		// trouver la categorie
		
		Categorie categorie = new Categorie();
		
		CategorieService categorieService = new CategorieService();
		
		boolean modifArt = false;
		
		try {
			categorie = categorieService.getCategorie(Integer.parseInt(request.getParameter("cat")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		ArticleVendu articleVendu = new ArticleVendu();
		
		articleVendu.setNomArticle(request.getParameter("article"));
		articleVendu.setDescription(request.getParameter("des"));
		articleVendu.setDateDebutEncheres(dateDebutEnchere);
		articleVendu.setDateFinEncheres(dateFinEnchere);
		articleVendu.setMiseAPrix(Integer.parseInt(request.getParameter("miseAprix")));
		articleVendu.setCategorie(categorie);
		articleVendu.setPrixVente(Integer.parseInt(request.getParameter("NumPrixVentes")));
		
		Retrait retrait = new Retrait();
		retrait.setRue(request.getParameter("rue"));
		retrait.setCode_postale(Integer.parseInt(request.getParameter("codePostal")));
		retrait.setVille(request.getParameter("ville"));
		retrait.setId(Integer.parseInt(request.getParameter("idArtMdf")));
		articleVendu.setRetrait(retrait);
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
		
		//System.out.println(utilisateur.getPseudo());
		
		articleVendu.setUtilisateur(utilisateur);
		articleVendu.setEtatVente(EtatVente.StringToEtatVente(request.getParameter("etatVen")));
		articleVendu.setNoArticle(Integer.parseInt(request.getParameter("idArtMdf")));
		
		System.out.println(articleVendu.toString());
		
		ArticleVenduService articleVenduService = new ArticleVenduService();
		
		try {
			string = articleVenduService.modifArticleVendu(articleVendu);
			modifArt = true;
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
			modifArt = false;
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
			modifArt = false;
		}
		
		RetraitService retraitService = new RetraitService();
		
		try {
			string += retraitService.updateEnchere(retrait);
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
			modifArt = false;
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
			modifArt = true;
		}
		
		request.setAttribute("message", string);
		
		if(modifArt) {
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/AdministrationArticle");
			requestDispatcher.forward(request, response);
		}else {
			doGet(request, response);
		}
	}
}
