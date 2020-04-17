package fr.Enchere.DAO;

import java.util.List;

import fr.Enchere.BO.Enchere;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;

public interface EnchereDAO extends GenericDAO<Enchere> {

	public Enchere FindEnchereByIdArticle(int id) throws DAOException, FunctionnalException;
	
	public List<Enchere> SelectByIdEnchereEnCours(int id) throws DAOException, FunctionnalException;
	
	public List<Enchere> SelectByIdEnchereWIn(int id) throws DAOException, FunctionnalException;
	
}
