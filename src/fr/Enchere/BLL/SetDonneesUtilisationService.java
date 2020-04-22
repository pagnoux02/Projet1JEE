package fr.Enchere.BLL;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.sun.org.apache.regexp.internal.recompile;

import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.Exception.ParameterException;
import fr.Enchere.util.CheckDataUtil;
import fr.Enchere.util.Constantes;

public class SetDonneesUtilisationService {
	
	/**
	 * 
	 * @param utilisateur
	 * @return
	 * @throws BllException
	 * @throws ParameterException
	 */
	public String insertUtilisateur(Utilisateur utilisateur) throws BllException, ParameterException  {
		
		String string = "";
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			checkParamInsertUtilisateur(utilisateur);
			
			utilisateur.setMotDePasse(CheckDataUtil.convertirMotdePasse(utilisateur.getMotDePasse()));
			
			System.out.println(utilisateur.getMotDePasse());
			
			string = utilisateurManager.getUtilisation().insert(utilisateur);
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		} catch (NoSuchAlgorithmException nuAlgorithmException) {
			nuAlgorithmException.printStackTrace();
			throw new BllException("Erreur pour :" + nuAlgorithmException.getMessage());
		}
		
		return string;
	}
	
	/**
	 * 
	 * @param utilisateur
	 * @return
	 * @throws BllException
	 * @throws ParameterException
	 */
	public String updateUtilisateur(Utilisateur utilisateur) throws BllException, ParameterException {
		
		String string = "";
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			checkUpdateUtilisateur(utilisateur);
			
			utilisateur.setMotDePasse(CheckDataUtil.convertirMotdePasse(utilisateur.getMotDePasse()));
			
			string = utilisateurManager.getUtilisation().update(utilisateur);
		} catch (DAOException daoException) {
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		} catch (NoSuchAlgorithmException nuAlgorithmException) {
			nuAlgorithmException.printStackTrace();
			throw new BllException("Erreur pour :" + nuAlgorithmException.getMessage());
		}
		
		return string;
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 * @throws BllException
	 */
	public String deleteUtilisateur(int id) throws BllException {
		
		String string = "";
		
		UtilisateurManager utilisateurManager = new UtilisateurManager();
		
		try {
			string = utilisateurManager.getUtilisation().delete(id);
		} catch (DAOException daoException) {
			// TODO Auto-generated catch block
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			// TODO Auto-generated catch block
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		}
		return string;
	}
	
	/**
	 * 
	 * @param utilisateur
	 * @throws ParameterException
	 */
	public void checkParamInsertUtilisateur(Utilisateur utilisateur) throws ParameterException {
		
		CheckDataUtil.checkUtilisateur(utilisateur);
		
		CheckDataUtil.checkPseudo(utilisateur.getPseudo());
		
		CheckDataUtil.checkNom(utilisateur.getNom());
		
		CheckDataUtil.checkPrenom(utilisateur.getPrenom());
		
		CheckDataUtil.checkEmail(utilisateur.getEmail());
		
		CheckDataUtil.checkTelephonne(utilisateur.getTelephone());
		
		CheckDataUtil.checkRue(utilisateur.getRue());
		
		CheckDataUtil.checkCodePostal(utilisateur.getCodePostal());
		
		CheckDataUtil.checkVille(utilisateur.getVille());
		
		CheckDataUtil.checkMotDePasse(utilisateur.getMotDePasse());
		
	}
	
	/**
	 * 
	 * @param utilisateur
	 * @throws ParameterException
	 */
	private void checkUpdateUtilisateur(Utilisateur utilisateur) throws ParameterException {
		
		checkParamInsertUtilisateur((utilisateur));
		
		CheckDataUtil.checkNumeroUtilisateur(String.valueOf(utilisateur.getNumeroUtilisateur()));
	}
	
}
