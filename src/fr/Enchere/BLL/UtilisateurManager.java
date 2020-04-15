package fr.Enchere.BLL;

import fr.Enchere.DAO.GenericDAOFactory;
import fr.Enchere.DAO.UtilisationDAO;

public class UtilisateurManager {
	
	 public UtilisationDAO getUtilisation() {
		 return (UtilisationDAO) GenericDAOFactory.getUtilisateurDao();
	 }
}
