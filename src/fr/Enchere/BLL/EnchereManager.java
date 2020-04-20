package fr.Enchere.BLL;


import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.Enchere;
import fr.Enchere.DAO.EnchereDAOJdbcImpl;
import fr.Enchere.DAO.GenericDAOFactory;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.Constantes;


public class EnchereManager {
	 public EnchereDAOJdbcImpl getEnchere() {
		 return (EnchereDAOJdbcImpl) GenericDAOFactory.getEnchereDAO();
	 }
	 

		public String insertEnchere(Enchere enchere) throws BllException, ParameterException  {
			
			String string = "";
			
			try {
				string = getEnchere().insert(enchere);
				
			} catch (DAOException daoException) {
				daoException.printStackTrace();
				throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
			} catch (FunctionnalException functionnalException) {
				functionnalException.printStackTrace();
				throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
			}
			
			return string;
		}
		
		public String updateEnchere(Enchere enchere) throws BllException, ParameterException  {
			
			String string = "";
			
			try {
				string = getEnchere().update(enchere);
				
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
				string = getEnchere().delete(idArticle);
				
			} catch (DAOException daoException) {
				daoException.printStackTrace();
				throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
			} catch (FunctionnalException functionnalException) {
				functionnalException.printStackTrace();
				throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
			}
			
			return string;
		}
		
		public Enchere SelectEnchereMax(int idArticle) throws BllException {
			
			Enchere enchere = new Enchere();
			
			try {
				enchere = getEnchere().FindEnchereByIdArticle(idArticle);
			} catch (FunctionnalException functionnalException) {
				// TODO Auto-generated catch block
				functionnalException.printStackTrace();
				throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
			} catch (DAOException daoException) {
				// TODO Auto-generated catch block
				daoException.printStackTrace();
				throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
			} 
			
			return enchere;
		}
		
		
		public List<Enchere> selectAllEnchereEnCours(int idUtilisateur) throws BllException{
			
			List<Enchere> listEncheres = new ArrayList<>();
			
			try {
				listEncheres = getEnchere().SelectByIdEnchereEnCours(idUtilisateur);
			} catch (DAOException daoException) {
				daoException.printStackTrace();
				throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
			} catch (FunctionnalException functionnalException) {
				functionnalException.printStackTrace();
				throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
			}
			
			return listEncheres;
		}
		public List<Enchere> selectAllEnchereWin(int idUtilisateur) throws BllException{
			
			List<Enchere> listEncheres = new ArrayList<>();
			
			try {
				listEncheres = getEnchere().SelectByIdEnchereWIn(idUtilisateur);
			} catch (DAOException daoException) {
				daoException.printStackTrace();
				throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
			} catch (FunctionnalException functionnalException) {
				functionnalException.printStackTrace();
				throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
			}
			
			return listEncheres;
		}
}
