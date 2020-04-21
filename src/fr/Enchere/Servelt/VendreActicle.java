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

import fr.Enchere.BLL.ArticleVenduService;
import fr.Enchere.BLL.CategorieService;
import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.Categorie;
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
		

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/article/AddArticle.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String string = "";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String nonArt = request.getParameter("article");
		String des = request.getParameter("des");
		String dateDebutStr = request.getParameter("debutEnch");
		String dateFinStr = request.getParameter("finEnch");
		String miseAprix = request.getParameter("miseAprix");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		
		String cat = request.getParameter("cat");

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
		
		articleVendu.setUtilisateur(utilisateur);
		
		ArticleVenduService articleVenduService = new ArticleVenduService();
		
		boolean addArticle = false;
		try {
			string = articleVenduService.newArticleVendu(articleVendu);
			addArticle = true;
			
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		} catch (ParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		System.out.println("date1 :" + dateDebutEnchere);
		System.out.println("date2 :" + dateFinEnchere);
		
		System.out.println( nonArt + des + cat);
		System.out.println(miseAprix + rue + codePostal + ville);
		//System.out.println("non file :" + fileName);
		System.out.println("categorie" + categorie.getLibelle());
		System.out.println("date1 :" + dateDebutStr + "date2 :" + dateFinStr);
		
		if(addArticle) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);
		}else {
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/VendreActicle");
			requestDispatcher.forward(request, response);
		}
	}
}
