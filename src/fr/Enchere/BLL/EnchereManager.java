package fr.Enchere.BLL;

import fr.Enchere.DAO.EnchereDAOJdbcImpl;
import fr.Enchere.DAO.GenericDAOFactory;


public class EnchereManager {
	 public EnchereDAOJdbcImpl getEnchere() {
		 return (EnchereDAOJdbcImpl) GenericDAOFactory.getEnchereDAO();
	 }
}
