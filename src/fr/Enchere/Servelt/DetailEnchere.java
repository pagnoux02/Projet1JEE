package fr.Enchere.Servelt;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.Enchere.BLL.ArticleVenduManager;
import fr.Enchere.BLL.EnchereManager;

import fr.Enchere.BLL.GetDonneesUtilisationService;
import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.Enchere;
import fr.Enchere.BO.EtatVente;
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
	int  idArticle = Integer.parseInt(request.getParameter("idArtEnch"));
	int idUserSession = Integer.parseInt(request.getParameter("idUser"));
	
	
		// TODO Auto-generated method stub
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		EnchereManager enchereManager = new EnchereManager();
		GetDonneesUtilisationService utilisateur = new GetDonneesUtilisationService();
		
		try {
			ArticleVendu Av = new ArticleVendu();
		 Av = articleVenduManager.getArticleDAO().selectById(idArticle);
		
		request.setAttribute("Article",Av );
		Enchere e = new Enchere();
		e=enchereManager.getEnchere().FindEnchereByIdArticle(idArticle);
		request.setAttribute("Enchere", e);
		request.setAttribute("userSession", utilisateur.selectById(idUserSession));
		if(LocalDate.now().isBefore(Av.getDateFinEncheres()) && Av.getDateDebutEncheres().isBefore(LocalDate.now())) {
			request.setAttribute("now", 0);
			System.out.println("before");
		}
		if(LocalDate.now().isAfter(Av.getDateFinEncheres()) && Av.getDateDebutEncheres().isBefore(LocalDate.now())) {
			request.setAttribute("now", 1);
			System.out.println("after");
		}
		if(Av.getDateDebutEncheres().isAfter(LocalDate.now())) {
			request.setAttribute("now", 2);
		}
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
		int  idArticle = Integer.parseInt(request.getParameter("idArtEnch"));
		int idUserSession = Integer.parseInt(request.getParameter("idUser"));
		
		// TODO Auto-generated method stub
		String leRetour = request.getParameter("btnValidation");
		EnchereManager enchereManagerStart = new EnchereManager();
		ArticleVenduManager articleVenduManagerStart= new ArticleVenduManager();
		//btn encherir 
		if( leRetour.equals("Enchérir")) {
			String credit = request.getParameter("credit");
			
			Enchere e = new Enchere();
			//insertion 
			try {

				e=enchereManagerStart.getEnchere().FindEnchere(idArticle, idUserSession);
				if ( e.getNoArticle() == 0|| e.getNoUtilisateur() == 0) {
								
					Enchere e2 = new Enchere(idUserSession , idArticle , java.sql.Date.valueOf(LocalDate.now()),Integer.parseInt(credit));
					enchereManagerStart.getEnchere().insert(e2);
					System.out.println("insert");
				}
				else {
					e.setMontant_enchere(Integer.parseInt(credit));
					enchereManagerStart.getEnchere().update(e);
					System.out.println("update");
				}
				
				

			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FunctionnalException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			//enchereManager2.getEnchere().update(enchere)
			
		}
		
		
		//affichage 
		try {
			
			ArticleVenduManager articleVenduManager= new ArticleVenduManager();
			EnchereManager enchereManager = new EnchereManager();
			GetDonneesUtilisationService utilisateur = new GetDonneesUtilisationService();
			Enchere e = new Enchere();
			ArticleVendu Av = new ArticleVendu();
			Av = articleVenduManager.getArticleDAO().selectById(idArticle);
			request.setAttribute("Article", articleVenduManager.getArticleDAO().selectById(idArticle));
			
			e=enchereManager.getEnchere().FindEnchereByIdArticle(idArticle);
			request.setAttribute("Enchere", e);
			request.setAttribute("userSession", utilisateur.selectById(idUserSession));
			if(LocalDate.now().isBefore(Av.getDateFinEncheres()) && Av.getDateDebutEncheres().isBefore(LocalDate.now())) {
				request.setAttribute("now", 0);
			}
			if(LocalDate.now().isAfter(Av.getDateFinEncheres()) && Av.getDateDebutEncheres().isBefore(LocalDate.now())) {
				request.setAttribute("now", 1);
			}
			if(Av.getDateDebutEncheres().isAfter(LocalDate.now())) {
				request.setAttribute("now", 2);
			}
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

