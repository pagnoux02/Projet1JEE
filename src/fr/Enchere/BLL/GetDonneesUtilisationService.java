package fr.Enchere.BLL;

import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.BllException;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
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
			throw new BllException("Erreur DAO pour :" + daoException.getMessage());
		} catch (FunctionnalException functionnalException) {
			functionnalException.printStackTrace();
			throw new BllException("Erreur functionelle pour :" + functionnalException.getMessage());
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
			utilisateur = utilisateurManager.getUtilisation().userEstDansLaBase(pseudo, pass);
		} catch (FunctionnalException functionnalException) {
			// TODO Auto-generated catch block
			functionnalException.printStackTrace();
			throw new BllException(Constantes.ERREUR_FUNCTIONELLE_POUR + functionnalException.getMessage());
		} catch (DAOException daoException) {
			// TODO Auto-generated catch block
			daoException.printStackTrace();
			throw new BllException(Constantes.ERREUR_DAO_POUR + daoException.getMessage());
		}
		
		return utilisateur;
	}
}
