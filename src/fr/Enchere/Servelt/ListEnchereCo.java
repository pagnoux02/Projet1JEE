package fr.Enchere.Servelt;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.ParameterException;

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
		RequestDispatcher requestDispacher = request.getRequestDispatcher("/WEB-INF/pages/listEnchere/ListEnchereCo.jsp");
		requestDispacher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String s = "";
		
//		try {
//			
//		} catch (ParameterException | BllException e) {
//			e.printStackTrace();
//			s = e.getMessage();
//		}
		
		request.setAttribute("message", s);
	}

}
