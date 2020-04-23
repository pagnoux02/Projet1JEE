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

import fr.Enchere.BLL.ArticleVenduService;
import fr.Enchere.BO.ArticleVendu;
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
		
		try {
			listArticles = articleVenduService.getAllArticleVendu();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		request.setAttribute("listArt", listArticles);
		
		Utilisateur utilisateur = (Utilisateur) request.getSession().getAttribute("user");
		
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
		
		String s = "";
		
		request.setAttribute("message", s);
	}

}
