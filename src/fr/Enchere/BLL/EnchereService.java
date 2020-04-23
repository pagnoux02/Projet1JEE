package fr.Enchere.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.Enchere;
import fr.Enchere.BO.Retrait;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.CheckEnchere;
import fr.Enchere.util.CheckRetrait;
import fr.Enchere.util.Constantes;

public class EnchereService {
	
	public String insertEnchere(Enchere enchere) throws BllException, ParameterException  {
		
		String string = "";
		EnchereManager em = new EnchereManager();
		try {
			CheckInsertUpdateEnchere(enchere);
			string = em.getEnchere().insert(enchere);
			
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
		EnchereManager em = new EnchereManager();
		try {
			CheckInsertUpdateEnchere(enchere);
			string = em.getEnchere().update(enchere);
			
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
		EnchereManager em = new EnchereManager();
		try {
			
			string = em.getEnchere().delete(idArticle);
			
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
		EnchereManager em = new EnchereManager();
		try {
			enchere = em.getEnchere().FindEnchereByIdArticle(idArticle);
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
		EnchereManager em = new EnchereManager();
		try {
			listEncheres = em.getEnchere().SelectByIdEnchereEnCours(idUtilisateur);
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
		EnchereManager em = new EnchereManager();
		try {
			listEncheres = em.getEnchere().SelectByIdEnchereWIn(idUtilisateur);
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return listEncheres;
	}
	
	public Enchere FindEnchere(int idart, int idUti)throws BllException {
		Enchere enchere = new Enchere();
		EnchereManager em = new EnchereManager();
		try {
			enchere = em.getEnchere().FindEnchere(idart,idUti);
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
	

	
private void CheckInsertUpdateEnchere(Enchere enchere) throws ParameterException {
		
	CheckEnchere.checkEncheres(enchere);
	CheckEnchere.checkMontant(enchere.getMontant_enchere());
	CheckEnchere.checkNumeroArtiEnchere(enchere.getNoArticle());
	CheckEnchere.checkNumeroUtiEnchere(enchere.getNoUtilisateur());
	}
}
