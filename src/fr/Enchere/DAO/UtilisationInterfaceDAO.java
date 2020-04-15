package fr.Enchere.DAO;

import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;

public interface UtilisationInterfaceDAO extends GenericDAO<Utilisateur> {
	
	public Utilisateur userEstDansLaBase(String pseudo,String motDePasse) throws DAOException,FunctionnalException;

}
