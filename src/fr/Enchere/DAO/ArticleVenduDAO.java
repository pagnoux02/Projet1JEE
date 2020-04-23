/**
 * 
 */
package fr.Enchere.DAO;

import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.DTOOutArticle;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;

/**
 * @author ilang
 *
 */
public interface ArticleVenduDAO extends GenericDAO<ArticleVendu> {
	
	public DTOOutArticle insertArticle(ArticleVendu t) throws DAOException, FunctionnalException;
}
