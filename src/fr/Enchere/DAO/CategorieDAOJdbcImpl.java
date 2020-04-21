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
			+ "SELECT no_categorie, "
			+ "		  libelle "
			+ "FROM CATEGORIES ";
	
	private static final String sqlSelectById = ""
			+ "SELECT no_categorie, "
			+ "		  libelle "
			+ "FROM CATEGORIES "
			+ "WHERE no_categorie = ? ";
	
	private static final String sqlInsert = ""
			+ "INSERT INTO CATEGORIES (libelle) "
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
			
			if(lc == null || lc.isEmpty()) {
				throw new FunctionnalException("il n'y a aucune catégorie");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
		}
		return lc;
	}

	@Override
	public Categorie selectById(int id) throws DAOException, FunctionnalException {
		ResultSet rs = null;
		Categorie c = null;
		try (Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlSelectById);){
			
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			
			if(rs.next()) {
				c = new Categorie(rs.getInt("no_categorie"),
								  rs.getString("libelle"));
			}
			
			if(c == null) {
				throw new FunctionnalException("la catégorie n'a pas été trouvé");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
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
			} else {
				throw new FunctionnalException(Constantes.DAO_SQL_INSERT_ECHEC);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
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
			} else {
				throw new FunctionnalException(Constantes.DAO_SQL_UPDATE_ECHEC);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
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
			} else {
				throw new FunctionnalException(Constantes.DAO_SQL_DELETE_ECHEC);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
		}
		
		return result;
	}

}
