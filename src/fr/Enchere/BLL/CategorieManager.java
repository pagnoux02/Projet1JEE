package fr.Enchere.BLL;

import fr.Enchere.DAO.CategorieDAOJdbcImpl;
import fr.Enchere.DAO.GenericDAOFactory;

public class CategorieManager {
	public CategorieDAOJdbcImpl getCategorie() {
		return (CategorieDAOJdbcImpl) GenericDAOFactory.getCategorieDAO();
	}
}
