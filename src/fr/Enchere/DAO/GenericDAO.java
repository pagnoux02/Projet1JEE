package fr.Enchere.DAO;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
	
	public List<T> selectAll() throws SQLException;
	
	

}
