package fr.Enchere.BLL;

import fr.Enchere.DAO.ArticleVenduDAOJdbcImpl;
import fr.Enchere.DAO.GenericDAOFactory;

public class ArticleVenduManager {
	
	public ArticleVenduDAOJdbcImpl getArticleDAO() {
		return (ArticleVenduDAOJdbcImpl) GenericDAOFactory.getArticleVenduDAO();
	}
}
