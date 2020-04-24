package fr.Enchere.Servelt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.Enchere.BLL.ArticleVenduService;
import fr.Enchere.BLL.CategorieService;
import fr.Enchere.BLL.EnchereService;
import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.Categorie;
import fr.Enchere.BO.Enchere;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;

/**
 * Servlet implementation class ListEnchereCo
 */
@WebServlet("/ListEnchereCo")
public class ListEnchereCo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListEnchereCo() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ArticleVendu> listArticles = new ArrayList<>();
		
		String string = "";
		
		ArticleVenduService articleVenduService = new ArticleVenduService();
		
		if(request.getAttribute("listArt") == null) {
		
			try {
				listArticles = articleVenduService.getAllArticleVendu();
			} catch (BllException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				string = e.getMessage();
			}
			request.setAttribute("message", string);
		
			request.setAttribute("listArt", listArticles);
		}
		
		EnchereService enchereService = new EnchereService();
		
		List<Enchere> listEncheres = new ArrayList<>();
		
		for (ArticleVendu articleVendu : listArticles) {
			
			try {
				Enchere enchere = enchereService.SelectEnchereMax(articleVendu.getNoArticle());
				listEncheres.add(enchere);
			} catch (BllException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				string = e.getMessage();
			}
			
			
		}
		
		request.setAttribute("listEnchere", listEncheres);
		
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
		
		List<Categorie> listCategories = new ArrayList<>();
		
		CategorieService categorieService = new CategorieService();
		
		try {
			listCategories = categorieService.getAllCategorie();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		request.setAttribute("listCat", listCategories);
		
		
		if(utilisateur != null && !utilisateur.getPseudo().isEmpty()) {
			RequestDispatcher requestDispacher = request.getRequestDispatcher("/WEB-INF/pages/listEnchere/ListEnchereCo.jsp");
			requestDispacher.forward(request, response);
		}else {
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/ListEnchereDeco");
			requestDispatcher.forward(request, response);
					
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String string = "";
		
		String search = request.getParameter("search");
		
		int catId = Integer.parseInt(request.getParameter("categorie"));
		
		String achatVente = request.getParameter("grp-achatVente");
		
		boolean enchereOuvert = estCheck(request.getParameter("enOuv"));
		
		boolean enchereEnCour = estCheck(request.getParameter("enCour"));
		
		boolean enchereRemporter = estCheck(request.getParameter("enRepor"));
		
		boolean venteEnCour = estCheck(request.getParameter("veCour"));
		
		boolean venteNonDebuter = estCheck(request.getParameter("nonDebut"));
		
		boolean venteTerm = estCheck(request.getParameter("veTerm"));
		
//		System.out.println(search + catId + achatVente + enchereOuvert + "  " + enchereEnCour + "  " + enchereRemporter + "  " + 
//				venteEnCour + "  " + venteNonDebuter + "  " + venteTerm);
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
				
		ArticleVenduService articleVenduService = new ArticleVenduService();
		
		List<ArticleVendu> listArticleVendus = new ArrayList<>();
		
		try {
			listArticleVendus = articleVenduService.getArticleVenduFilter(utilisateur, search, catId, achatVente, enchereOuvert,
					enchereEnCour, enchereRemporter, venteEnCour, venteNonDebuter, venteTerm);
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		request.setAttribute("listArt", listArticleVendus);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/listEnchere/ListEnchereCo.jsp");
		requestDispatcher.forward(request, response);
		
	}
	
	/**
	 * 
	 * @param string
	 * @return
	 */
	private boolean estCheck(String string) {
		return string == null || string.isEmpty() ? false : true;
	}

}
