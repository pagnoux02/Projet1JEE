package fr.Enchere.Servelt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fr.Enchere.BLL.ArticleVenduService;
import fr.Enchere.BLL.CategorieService;
import fr.Enchere.BLL.RetraitManager;
import fr.Enchere.BLL.RetraitService;
import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.Categorie;
import fr.Enchere.BO.DTOOutArticle;
import fr.Enchere.BO.EtatVente;
import fr.Enchere.BO.Retrait;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.Constantes;

/**
 * Servlet implementation class VendreActicle
 */
@WebServlet("/VendreActicle")
public class VendreActicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VendreActicle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("test");
		
		String string = "";
		
		List<Categorie> lisCategories = new ArrayList<>();
		
		CategorieService categorieService = new CategorieService();
		
		try {
			lisCategories = categorieService.getAllCategorie();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
			
		}
		
		request.setAttribute("ListCat", lisCategories);
		
		if(request.getParameter("isModif") != null) {
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/ModifArticle");
			requestDispatcher.forward(request, response);
					
		}else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/article/AddArticle.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
		
		Retrait retrait = new Retrait();
		retrait.setRue(request.getParameter("rue"));
		retrait.setCode_postale(Integer.parseInt(request.getParameter("codePostal")));
		retrait.setVille(request.getParameter("ville"));
		
		articleVendu.setRetrait(retrait);
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
		
		//System.out.println(utilisateur.getPseudo());
		
		articleVendu.setUtilisateur(utilisateur);
		articleVendu.setEtatVente(EtatVente.Créée);
		
		DTOOutArticle dtoOutArticle = new DTOOutArticle();
		
		ArticleVenduService articleVenduService = new ArticleVenduService();
		
		boolean addArticle = false;
		try {
			dtoOutArticle = articleVenduService.newArticleVendu(articleVendu);
			addArticle = true;
			
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
			addArticle = false;
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
			addArticle = false;
		}
		
		retrait.setId(dtoOutArticle.getIdArticle());
		
		RetraitService retraitService = new RetraitService();
		
		try {
			string = retraitService.insertEnchere(retrait);
			addArticle = true;
		} catch (BllException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			string = e1.getMessage();
			addArticle = false;
		} catch (ParameterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			string = e1.getMessage();
			addArticle = false;
		}
		
		request.setAttribute("message", string);
		
		if(addArticle) {
			
			if(utilisateur.getAdministrateur()) {
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/AdministrationArticle");
				requestDispatcher.forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/ListEnchereCo");
			}
		}else {
			doGet(request,response);
		}
	}
}
