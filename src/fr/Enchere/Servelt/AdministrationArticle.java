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
import fr.Enchere.Exception.BllException;

/**
 * Servlet implementation class AdministrationArticle
 */
@WebServlet("/AdministrationArticle")
public class AdministrationArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministrationArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String string = "";
		
		List<ArticleVendu> listArticleVendus = new ArrayList<>();
		
		ArticleVenduService articleVenduService = new ArticleVenduService();
		
		try {
			listArticleVendus = articleVenduService.getAllArticleVendu();
		} catch (BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		System.out.println(listArticleVendus.toString());
		
		request.setAttribute("listArticles", listArticleVendus);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/article/AdministrationActicle.jsp");
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
