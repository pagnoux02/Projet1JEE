package fr.Enchere.Servelt;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.Enchere.BO.Categorie;
import fr.Enchere.util.Constantes;
import javafx.util.converter.LocalDateStringConverter;

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
		
		

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/pages/article/AddArticle.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String string = "";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constantes.PATTERN_DATE_FORMATER);

		String nonArt = request.getParameter("article");
		String des = request.getParameter("des");
		String dateDebutStr = request.getParameter("debutEnch");
		String dateFinStr = request.getParameter("FinEnch");
		
		LocalDate dateDebut = LocalDate.parse(dateDebutStr, formatter);
		
		LocalDate dateFin = LocalDate.parse(dateFinStr, formatter);
		
		
		
		System.out.println(dateDebut + nonArt + des);
		
		//Categorie categorie = request.getParameter("cat");
		
	}

}
