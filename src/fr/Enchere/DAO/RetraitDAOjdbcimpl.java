package fr.Enchere.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.Enchere.BO.DTOOutArticle;
import fr.Enchere.BO.Retrait;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.JDBCConnection.ConnectionProvider;
import fr.Enchere.util.Constantes;


public class RetraitDAOjdbcimpl implements RetraitDao{

	private static final String SELECT_BY_ID_RETRAIT = "select rue , code_postal , ville from retraits where no_article = ?";
	private static final String DELETE_RETRAIT = "delete from retraits where no_article=?";
	private static final String INSERT_RETRAIT = "insert into retraits (no_article ,rue , code_postal, ville) values (?,?,?,?)";
	private static final String UPDATE_RETRAIT = "update ARTICLES set rue=? , code_postal=?, ville=? where no_article=?";

	@Override
	public List<Retrait> selectAll() throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retrait selectById(int id) throws DAOException, FunctionnalException {
		Retrait retrait = null;
		try(Connection cnx = ConnectionProvider.getConnectionProvider(); 
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID_RETRAIT);)
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();


			while(rs.next())
			{
				retrait = new Retrait(rs.getString("rue"), rs.getInt("code_postal"),rs.getString("Ville"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur selectById retrait");
		}
		return retrait;
	}


	@Override
	public String insert(Retrait retrait) throws DAOException, FunctionnalException {
		
		
		if(retrait==null)
		{
			throw new FunctionnalException("erreur insert retrait vide");
		}

		String string = "";

		try(Connection Connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement statementInsert = Connection.prepareStatement(INSERT_RETRAIT)){
			
			statementInsert.setInt(1, retrait.getId());
			statementInsert.setString(2, retrait.getRue());
			statementInsert.setInt(3, retrait.getCode_postale());
			statementInsert.setString(4, retrait.getVille());
			int ress = statementInsert.executeUpdate();

			if(ress == 0) {
				throw new FunctionnalException("L'insertion s'est mal pass�");
			}

			string = Constantes.DAO_SQL_INSERT_REUSSITE;

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} catch (RuntimeException e1) {
			throw new DAOException(e1.getMessage());
		}

		return string;
	}

	@Override
	public String update(Retrait retrait) throws DAOException, FunctionnalException {
		
		String string = "";
		
		try(Connection connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement pstmt = connection.prepareStatement(UPDATE_RETRAIT)){
			pstmt.setInt(1, retrait.getId());
			pstmt.setString(2, retrait.getRue());
			pstmt.setInt(3, retrait.getCode_postale());
			pstmt.setString(4, retrait.getVille());
			
			int ress = pstmt.executeUpdate();
			
			
			if(ress == 0) {
				throw new FunctionnalException("la mise � jour c'est mal pass�");
			}
			
			string = Constantes.DAO_SQL_UPDATE_REUSSITE;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur pour :" + e.getMessage());
		}
		
		return string;
	}

	@Override
	public String delete(int id) throws DAOException, FunctionnalException {
		String string = "";
		try(Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_RETRAIT);)
		{
			string = Constantes.DAO_SQL_DELETE_REUSSITE;
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur delete Retrait");
		}
		return string ;

	}


}
