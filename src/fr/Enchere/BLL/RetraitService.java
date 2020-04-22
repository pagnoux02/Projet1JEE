package fr.Enchere.BLL;

import fr.Enchere.BO.Retrait;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.CheckRetrait;
import fr.Enchere.util.Constantes;

public class RetraitService {
	public String insertEnchere(Retrait retrait) throws BllException, ParameterException  {
		
		String string = "";
		RetraitManager rm = new RetraitManager();
		try {
			checkRetraitInsert_update(retrait);
			string = rm.getRetrait().insert(retrait);
			
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return string;
	}
	
	public String updateEnchere(Retrait retrait) throws BllException, ParameterException  {
		
		String string = "";
		RetraitManager rm = new RetraitManager();
		try {
			checkRetraitInsert_update(retrait);
			string = rm.getRetrait().update(retrait);
			
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return string;
	}
	
	public String deleteEnchere(int idArticle) throws BllException, ParameterException  {
		
		String string = "";
		RetraitManager rm = new RetraitManager();
		try {
			string = rm.getRetrait().delete(idArticle);
			
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return string;
	}
	
	public Retrait SelectEnchereMax(int idArticle) throws BllException {
		
		Retrait retrait = new Retrait();
		RetraitManager rm = new RetraitManager();
		try {
			retrait = rm.getRetrait().selectById(idArticle);
		} catch (FunctionnalException functionnalException) {
			// TODO Auto-generated catch block
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		} catch (DAOException daoException) {
			// TODO Auto-generated catch block
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} 
		
		return retrait;
	}
	
private void checkRetraitInsert_update(Retrait retrait) throws ParameterException {
		
		CheckRetrait.checkRetrait(retrait);
		
		CheckRetrait.checkarti(retrait.getId());
		
		CheckRetrait.checkRue(retrait.getRue());
		
		CheckRetrait.checkVille(retrait.getVille());
		
		CheckRetrait.checkCode_postale(retrait.getCode_postale());
	}
}
