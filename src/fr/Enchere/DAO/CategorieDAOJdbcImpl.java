/**
 * 
 */
package fr.Enchere.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.Categorie;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.JDBCConnection.ConnectionProvider;
import fr.Enchere.util.Constantes;

/**
 * @author ilang
 *
 */
public class CategorieDAOJdbcImpl implements CategorieDAO {
	
	private static final String sqlSelectAll = ""
			+ "SELECT c.no_categorie, "
			+ "		  c.libelle "
			+ "FROM CATEGORIES ";
	
	private static final String sqlSelectById = ""
			+ "SELECT c.no_categorie, "
			+ "		  c.libelle "
			+ "FROM CATEGORIES "
			+ "WHERE c.no_categorie = ? ";
	
	private static final String sqlInsert = ""
			+ "INSERT INTO CATEGORIES (c.libelle) "
			+ "VALUES (?) ";
	
	private static final String sqlUpdate = ""
			+ "UPDATE CATEGORIES "
			+ "SET libelle = ? "
			+ "WHERE no_categorie = ? ";
	
	private static final String sqlDelete = ""
			+ "DELETE FROM CATEGORIES "
			+ "WHERE no_categorie = ? ";
	
	@Override
	public List<Categorie> selectAll() throws DAOException, FunctionnalException {
		ResultSet rs = null;
		Categorie c = null;
		List<Categorie> lc = new ArrayList<Categorie>();
		try (Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlSelectAll);){
			
			rs = rqt.executeQuery();
			
			while(rs.next()) {
				c = new Categorie(rs.getInt("no_categorie"),
								  rs.getString("libelle"));
				lc.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lc;
	}

	@Override
	public Categorie selectById(int id) throws DAOException, FunctionnalException {
		ResultSet rs = null;
		Categorie c = null;
		try (Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlSelectAll);){
			
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			
			if(rs.next()) {
				c = new Categorie(rs.getInt("no_categorie"),
								  rs.getString("libelle"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}

	@Override
	public String insert(Categorie t) throws DAOException, FunctionnalException {
		
		String result = Constantes.DAO_SQL_INSERT_ECHEC;
		try(Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);){
			
			rqt.setString(1, t.getLibelle());
			
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()) {
					t.setNoCategorie(rs.getInt(1));
				}
				result = Constantes.DAO_SQL_INSERT_REUSSITE;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String update(Categorie t) throws DAOException, FunctionnalException {
		
		String result = Constantes.DAO_SQL_UPDATE_ECHEC;
		try(Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlUpdate);){
			
			rqt.setString(1, t.getLibelle());
			rqt.setInt(2, t.getNoCategorie());
			
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1) {
				result = Constantes.DAO_SQL_UPDATE_REUSSITE;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public String delete(int t) throws DAOException, FunctionnalException {
		String result = Constantes.DAO_SQL_DELETE_ECHEC;
		try(Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlDelete);){
			
			rqt.setInt(1, t);
			
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1) {
				result = Constantes.DAO_SQL_DELETE_REUSSITE;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
