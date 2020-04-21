package fr.Enchere.Servelt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import fr.Enchere.BLL.EnchereManager;
import fr.Enchere.BO.Enchere;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.ParameterException;

/**
 * Servlet implementation class AddEnchere
 */
@WebServlet("/AddEnchere")
public class AddEnchere extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEnchere() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/enchere.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
String string = "";
		
		Enchere enchere = new Enchere();
		
		enchere.setNoUtilisateur(Integer.parseInt(request.getParameter("id_uti")));
		enchere.setNoArticle(Integer.parseInt(request.getParameter("id_arti")));
		
		enchere.setMontant_enchere(Integer.parseInt(request.getParameter("montant")));	
		System.out.println(enchere);
		
		try {
			
			EnchereManager enchereManager = new EnchereManager();
			
			string = enchereManager.deleteEnchere(1);
		} catch (ParameterException | BllException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			string = e.getMessage();
		}
		
		request.setAttribute("message", string);
		
		System.out.println(string);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/Home.jsp");
		requestDispatcher.forward(request, response);
	}

}
