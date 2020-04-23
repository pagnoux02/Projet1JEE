/**
 * 
 */
package fr.Enchere.DAO;

import java.util.List;

import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.DTOOutArticle;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;

/**
 * @author ilang
 *
 */
public interface ArticleVenduDAO extends GenericDAO<ArticleVendu> {
	
	public DTOOutArticle insertArticle(ArticleVendu t) throws DAOException, FunctionnalException;
	
	public List<ArticleVendu> selectFilter(Utilisateur currentUser, String search, int idCategorie, String achatsVentes, boolean chkOpenBid,
			boolean chkMyCurrentBid, boolean chkMyWonBid, boolean chkMyCurrentSales, boolean chkMyNotStartedSales, boolean chkMyEndedSales) 
			throws DAOException, FunctionnalException;
}
