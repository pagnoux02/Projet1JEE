package fr.Enchere.Servelt;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.Enchere.BLL.ArticleVenduManager;
import fr.Enchere.BLL.EnchereManager;
import fr.Enchere.BLL.GetDonneesUtilisationService;
import fr.Enchere.BLL.UtilisateurManager;
import fr.Enchere.BO.Enchere;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;


/**
 * Servlet implementation class DetailEnchere
 */
@WebServlet("/DetailEnchere")
public class DetailEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailEnchere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		EnchereManager enchereManager = new EnchereManager();
		GetDonneesUtilisationService utilisateur = new GetDonneesUtilisationService();
		
		try {
			request.setAttribute("Article", articleVenduManager.getArticleDAO().selectById(1));
			Enchere e = new Enchere();
			e=enchereManager.getEnchere().FindEnchereByIdArticle(1);
			request.setAttribute("Enchere", e);
			request.setAttribute("userSession", utilisateur.selectById(103));
			request.setAttribute("now", LocalDate.now());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FunctionnalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BllException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/enchere/DetailEnchere.jsp");
		rd.forward(request, response);
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/pages/enchere/DetailEnchere.jsp");
		rd.forward(request, response);
	}

}

