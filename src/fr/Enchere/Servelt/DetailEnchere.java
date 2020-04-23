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
import fr.Enchere.BLL.EnchereService;
import fr.Enchere.BLL.GetDonneesUtilisationService;
import fr.Enchere.BLL.UtilisateurManager;
import fr.Enchere.BO.Enchere;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;


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
			request.setAttribute("userSession", utilisateur.selectById(1003));
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
		String leRetour = request.getParameter("btnValidation");
		ArticleVenduManager articleVenduManager2= new ArticleVenduManager();
		EnchereManager enchereManager2 = new EnchereManager();
		GetDonneesUtilisationService utilisateur2 = new GetDonneesUtilisationService();
		if( leRetour.equals("Enchérir")) {

			String credit = request.getParameter("credit");
			
			
			
			Enchere e = new Enchere();
			try {
				e=enchereManager2.getEnchere().FindEnchereByIdArticle(1);
				e.setMontant_enchere(Integer.parseInt(credit));
				enchereManager2.getEnchere().insert(e);
				System.out.println(enchereManager2.getEnchere().update(e));

			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FunctionnalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//enchereManager2.getEnchere().update(enchere)
			
		}
		
		
		
		try {
			request.setAttribute("Article", articleVenduManager2.getArticleDAO().selectById(1));
			Enchere e = new Enchere();
			e=enchereManager2.getEnchere().FindEnchereByIdArticle(1);
			request.setAttribute("Enchere", e);
			request.setAttribute("userSession", utilisateur2.selectById(1003));
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

}

