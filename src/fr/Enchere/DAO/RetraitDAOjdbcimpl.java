package fr.Enchere.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import fr.Enchere.BO.Retrait;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.JDBCConnection.ConnectionProvider;
import fr.eni.javaee.gestionlistescourses.BusinessException;
import fr.eni.javaee.gestionlistescourses.dal.CodesResultatDAL;

public class RetraitDAOjdbcimpl implements RetraitDao{

	private static final String SELECT_BY_ID = "select rue , code_postal , ville from retraits where no_article = 1";
	private static final String DELETE_Retrait = "delet from retraits where id=?";
	private static final String SQL_INSERT_RETRAIT = "insert into retraits rue , code_postal values (?,?,?)";
	

	@Override
	public List<Retrait> selectAll() throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retrait sellectByid(int id) throws DAOException, FunctionnalException {
			Retrait retrait;
			try(Connection cnx = ConnectionProvider.getConnection(); 
					PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);)
			{
				
				pstmt.setInt(1, id);
				ResultSet rs = pstmt.executeQuery();
				
				
				while(rs.next())
				{
					retrait = new Retrait(rs.getString("rue"), rs.getInt("code_postal"),rs.getString("Ville"));
				}
			}
	catch (Exception e) {
		// TODO: handle exception
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
                PreparedStatement statementInsert = Connection.prepareStatement(SQL_INSERT_RETRAIT , PreparedStatement.RETURN_GENERATED_KEYS)){

            statementInsert.setString(1, retrait.getRue());
            statementInsert.setInt(2, retrait.getCode_postale());
            statementInsert.setString(3, retrait.getVille());
            int ress = statementInsert.executeUpdate();

            ResultSet ressul = statementInsert.getGeneratedKeys();

            if(ressul.next()) {
            	retrait.setId(ressul.getInt(1));
            }

            if(ress == 0) {
                throw new FunctionnalException("L'insertion s'est mal pass�");
            }

            string = "Success l'insertion s'est bien pass�";

        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } catch (RuntimeException e1) {
            throw new DAOException(e1.getMessage());
        }

        return string;
	}

	@Override
	public String update(Retrait t) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Delet(int id) throws DAOException, FunctionnalException {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_Retrait);)
		{
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			
			} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur delete Retrait");
		}
		return "good";
		
	}


}
