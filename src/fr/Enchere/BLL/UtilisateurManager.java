package fr.Enchere.BLL;

import fr.Enchere.DAO.GenericDAOFactory;
import fr.Enchere.DAO.UtilisationDAOJdbcImpl;

public class UtilisateurManager {
	
	 public UtilisationDAOJdbcImpl getUtilisation() {
		 return (UtilisationDAOJdbcImpl) GenericDAOFactory.getUtilisateurDao();
	 }
}
