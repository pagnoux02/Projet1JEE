package fr.Enchere.BLL;


import fr.Enchere.DAO.GenericDAOFactory;
import fr.Enchere.DAO.RetraitDAOjdbcimpl;

public class RetraitManager {
	
	 public RetraitDAOjdbcimpl getRetrait() {
		 return (RetraitDAOjdbcimpl) GenericDAOFactory.getRetraitDAO();
	 }

	
}
