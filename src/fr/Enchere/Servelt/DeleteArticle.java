package fr.Enchere.Servelt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.Enchere.BLL.ArticleVenduService;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.ParameterException;

/**
 * Servlet implementation class DeleteArticle
 */
@WebServlet("/DeleteArticle")
public class DeleteArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleVenduService articleVenduService = new ArticleVenduService();
		
		String string = "";
		
		try {
			string = articleVenduService.supprArticleVendu(Integer.parseInt(request.getParameter("idDel")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
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
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/AdministrationArticle");
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
