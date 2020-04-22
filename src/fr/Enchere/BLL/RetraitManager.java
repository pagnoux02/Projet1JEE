package fr.Enchere.BLL;

import fr.Enchere.BO.DTOOutArticle;
import fr.Enchere.BO.Retrait;
import fr.Enchere.DAO.GenericDAOFactory;
import fr.Enchere.DAO.RetraitDAOjdbcimpl;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.Constantes;

public class RetraitManager {
	
	 public RetraitDAOjdbcimpl getRetrait() {
		 return (RetraitDAOjdbcimpl) GenericDAOFactory.getRetraitDAO();
	 }

		public String insertEnchere(Retrait retrait) throws BllException, ParameterException  {
			
			
			String string = "";
			
			try {
				string = getRetrait().insert(retrait);
				
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
			
			try {
				string = getRetrait().update(retrait);
				
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
			
			try {
				string = getRetrait().delete(idArticle);
				
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
			
			try {
				retrait = getRetrait().selectById(idArticle);
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
}
