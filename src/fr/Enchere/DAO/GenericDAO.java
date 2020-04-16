package fr.Enchere.DAO;

import java.util.List;

import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;

public interface GenericDAO<T> {
	
	public List<T> selectAll() throws DAOException,FunctionnalException;
	
	public T selectById(int id) throws DAOException,FunctionnalException;
	
	public String insert(T t) throws DAOException,FunctionnalException;
	
	public String update(T t) throws DAOException,FunctionnalException;
	
	public String delete(int t) throws DAOException,FunctionnalException;


}
