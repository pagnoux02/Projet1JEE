/**
 * 
 */
package fr.Enchere.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.Categorie;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.CheckCategorie;
import fr.Enchere.util.Constantes;

/**
 * @author ilang
 *
 */
public class CategorieService {
	
	/**
	 * 
	 * @return
	 * @throws BllException
	 */
	public List<Categorie> getAllCategorie() throws BllException {
		
		List<Categorie> listCategorie = new ArrayList<>();
		
		CategorieManager categorieManager = new CategorieManager();
		
		try {
			listCategorie = categorieManager.getCategorie().selectAll();
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return listCategorie;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws BllException
	 */
	public Categorie getCategorie(int id) throws BllException {
		
		Categorie categorie = null;
		
		CategorieManager categorieManager = new CategorieManager();
		
		try {
			categorie = categorieManager.getCategorie().selectById(id);
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return categorie;
	}
	
	/**
	 * 
	 * @param categorie
	 * @return
	 * @throws BllException
	 * @throws ParameterException
	 */
	public String newCategorie(Categorie categorie) throws BllException, ParameterException {
		
		String s = "";
		
		CategorieManager categorieManager = new CategorieManager();
		
		try {
			checkParamInsert(categorie);
			
			s = categorieManager.getCategorie().insert(categorie);
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
	 * @param categorie
	 * @return
	 * @throws BllException
	 * @throws ParameterException
	 */
	public String modifCategorie(Categorie categorie) throws BllException, ParameterException {
		
		String s = "";
		
		CategorieManager categorieManager = new CategorieManager();
		
		try {
			checkUpdate(categorie);
			
			s = categorieManager.getCategorie().update(categorie);
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
	 * @param id
	 * @return
	 * @throws BllException
	 */
	public String supprCategori(int id) throws BllException {
		
		String s = "";
		
		CategorieManager categorieManager = new CategorieManager();
		
		try {
			s = categorieManager.getCategorie().delete(id);
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
	 * @param categorie
	 * @throws ParameterException
	 */
	private void checkParamInsert(Categorie categorie) throws ParameterException {
		
		CheckCategorie.checkCategorie(categorie);
		
		CheckCategorie.checkLibelleCategorie(categorie.getLibelle());
	}
	
	/**
	 * 
	 * @param categorie
	 * @throws ParameterException
	 */
	private void checkUpdate(Categorie categorie) throws ParameterException {
		
		checkParamInsert(categorie);
		
		CheckCategorie.checkNoCategorie(String.valueOf(categorie.getNoCategorie()));
	}
}
