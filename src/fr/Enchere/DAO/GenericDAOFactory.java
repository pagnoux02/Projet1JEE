package fr.Enchere.DAO;

import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.Categorie;
import fr.Enchere.BO.Utilisateur;

public class GenericDAOFactory {
	
	// definir les atribue de vos DAO
	
	private static GenericDAO<Utilisateur> DAOUtilisateur;
	
	private static GenericDAO<ArticleVendu> DAOArticleVendu;
	
	private static GenericDAO<Categorie> DAOCategorie;
	
	// definir vos factory  
	
	public static GenericDAO<Utilisateur> getUtilisateurDao(){
		if(GenericDAOFactory.DAOUtilisateur == null) {
			return GenericDAOFactory.DAOUtilisateur = new UtilisationDAO();
		}
		return DAOUtilisateur;
	}
	
	public static GenericDAO<ArticleVendu> getArticleVenduDAO(){
		if(GenericDAOFactory.DAOArticleVendu == null) {
			return GenericDAOFactory.DAOArticleVendu = new ArticleVenduDAOJdbcImpl();
		}
		return DAOArticleVendu;
	}
	
	public static GenericDAO<Categorie> getCategorieDAO(){
		if(GenericDAOFactory.DAOCategorie == null) {
			return GenericDAOFactory.DAOCategorie = new CategorieDAOJdbcImpl();
		}
		return DAOCategorie;
	}
	
}
