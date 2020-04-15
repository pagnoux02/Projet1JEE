package fr.Enchere.DAO;

import fr.Enchere.BO.Utilisateur;

public class GenericDAOFactory {
	
	// definir les atribue de vos DAO
	
	private static GenericDAO<Utilisateur> genericDAOUtilisateur;
	
	
	// definir vos factory  
	
	public static GenericDAO<Utilisateur> getUtilisateurDao(){
		if(GenericDAOFactory.genericDAOUtilisateur == null) {
			return GenericDAOFactory.genericDAOUtilisateur = new UtilisationDAO();
		}
		return genericDAOUtilisateur;
	}

}
