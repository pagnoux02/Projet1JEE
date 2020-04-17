package fr.Enchere.BLL;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.util.CheckDataUtil;
import fr.Enchere.util.Constantes;

public class GetDonneesUtilisationService {
	
	/**
	 * 
	 * @return
	 * @throws BllException
	 */
	public List<Utilisateur> selectAllUtisateur() throws BllException{
		
		List<Utilisateur> listUtilisateurs = new ArrayList<>();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			listUtilisateurs = utilisateurManager.getUtilisation().selectAll();
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		
		return listUtilisateurs;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws BllException
	 */
	public Utilisateur selectById(int id) throws BllException {
		
		Utilisateur utilisateur = new Utilisateur();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			utilisateur = utilisateurManager.getUtilisation().selectById(id);
		} catch (DAOException daoException) {
			// TODO Auto-generated catch block
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			// TODO Auto-generated catch block
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		return utilisateur;
	}
	
	/**
	 * 
	 * @param pseudo
	 * @param pass
	 * @return
	 * @throws BllException
	 */
	public Utilisateur selectUserInBDD(String pseudo,String pass) throws BllException {
		
		Utilisateur utilisateur = new Utilisateur();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			System.out.println(CheckDataUtil.convertirMotdePasse(pass));
			
			utilisateur = utilisateurManager.getUtilisation().userEstDansLaBase(pseudo, CheckDataUtil.convertirMotdePasse(pass));
		} catch (FunctionnalException functionnalException) {
			// TODO Auto-generated catch block
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		} catch (DAOException daoException) {
			// TODO Auto-generated catch block
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (NoSuchAlgorithmException nuAlgorithmException) {
			// TODO Auto-generated catch block
			nuAlgorithmException.printStackTrace();
			throw new BllException("Erreur pour :" + nuAlgorithmException.getMessage());
		}
		
		return utilisateur;
	}
	
	/**
	 * 
	 * @param email
	 * @throws BllException
	 */
	public void seleteEmailInBDD(String email) throws BllException {
		
		List<String> listEmail = new ArrayList<>();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			listEmail = utilisateurManager.getUtilisation().emailInBDD();
			
			if(listEmail.contains(email)) {
				throw new FunctionnalException("L'email exit deja !");
			}
		} catch (DAOException daoException) {
			// TODO Auto-generated catch block
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			// TODO Auto-generated catch block
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
	}
	
	/**
	 * 
	 * @param email
	 * @throws BllException
	 */
	public void seletePseudoInBDD(String email) throws BllException {
		
		List<String> listEmail = new ArrayList<>();
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			listEmail = utilisateurManager.getUtilisation().pseudoInBDD();
			
			if(listEmail.contains(email)) {
				throw new FunctionnalException("le pssudo exite deja !");
			}
		} catch (DAOException daoException) {
			// TODO Auto-generated catch block
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			// TODO Auto-generated catch block
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
	}
}
