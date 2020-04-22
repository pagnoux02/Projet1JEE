/**
 * 
 */
package fr.Enchere.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.DTOOutArticle;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.CheckArticleVendu;
import fr.Enchere.util.CheckDataUtil;
import fr.Enchere.util.Constantes;

/**
 * @author ilang
 */
public class ArticleVenduService {
	
	/**
	 * @author ilang
	 * @return
	 * @throws BllException
	 */
	public List<ArticleVendu> getAllArticleVendu() throws BllException {
		
		List<ArticleVendu> listArticleVendu = new ArrayList<>();
		
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		
		try {
			listArticleVendu = articleVenduManager.getArticleDAO().selectAll();
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return listArticleVendu;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws BllException
	 */
	public ArticleVendu getArticleVendu(int id) throws BllException {
		
		ArticleVendu articleVendu = null;
		
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		
		try {
			articleVendu = articleVenduManager.getArticleDAO().selectById(id);
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return articleVendu;
	}
	
	/**
	 * 
	 * @param articleVendu
	 * @return
	 * @throws BllException
	 * @throws ParameterException
	 */
	public DTOOutArticle newArticleVendu(ArticleVendu articleVendu) throws BllException, ParameterException {
		
		DTOOutArticle dtoOutArticle = new DTOOutArticle();
		
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		
		try {
			checkParamInsertArticle(articleVendu);
			
			dtoOutArticle = articleVenduManager.getArticleDAO().insertArticle(articleVendu);
			
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return dtoOutArticle;
	}
	
	/**
	 * 
	 * @param articleVendu
	 * @return
	 * @throws BllException
	 * @throws ParameterException
	 */
	public String modifArticleVendu(ArticleVendu articleVendu) throws BllException, ParameterException {
		
		String s = "";
		
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		
		try {
			checkUpdateArticle(articleVendu);
			
			s = articleVenduManager.getArticleDAO().update(articleVendu);
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return s;
	}
	
	
	public String supprArticleVendu(int id) throws BllException, ParameterException {
		
		String s = "";
		
		ArticleVenduManager articleVenduManager = new ArticleVenduManager();
		
		RetraitManager retraitManager = new RetraitManager();
		
		try {
			s = retraitManager.deleteEnchere(id);
			
			s += articleVenduManager.getArticleDAO().delete(id);
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		return s;
	}
	
	/**
	 * 
	 * @param articleVendu
	 * @throws ParameterException
	 */
	public void checkParamInsertArticle(ArticleVendu articleVendu) throws ParameterException {
		
		CheckArticleVendu.checkArticleVendu(articleVendu);
		
		CheckArticleVendu.checkNomArticle(articleVendu.getNomArticle());
		
		CheckArticleVendu.checkDescription(articleVendu.getDescription());
		
		CheckArticleVendu.checkDateDebutEncheres(articleVendu.getDateDebutEncheres());
		
		CheckArticleVendu.checkDateFinEncheres(articleVendu.getDateFinEncheres());
		
		CheckArticleVendu.checkMiseAPrix(articleVendu.getMiseAPrix());
		
		CheckArticleVendu.checkPrixVente(articleVendu.getPrixVente());
		
		CheckArticleVendu.checkEtatVente(articleVendu.getEtatVente());
	}
	
	/**
	 * 
	 * @param articleVendu
	 * @throws ParameterException
	 */
	public void checkUpdateArticle(ArticleVendu articleVendu) throws ParameterException {
		
		checkParamInsertArticle(articleVendu);
		
		CheckArticleVendu.checkNoArticle(String.valueOf(articleVendu.getNoArticle()));
	}
}
