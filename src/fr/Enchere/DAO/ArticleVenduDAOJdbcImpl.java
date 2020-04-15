/**
 * 
 */
package fr.Enchere.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.Categorie;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.JDBCConnection.ConnectionProvider;

/**
 * @author ilang
 *
 */
public class ArticleVenduDAOJdbcImpl implements ArticleVenduDAO{
	
	private static final String sqlSelectAll = ""
			+ "SELECT av.no_article, "
			+ "		  av.nom_article, "
			+ "		  av.description, "
			+ "		  av.date_debut_encheres, "
			+ "		  av.date_fin_encheres, "
			+ "		  av.prix_initial, "
			+ "		  av.prix_vente, "
			+ "		  av.no_utilisateur, "
			+ "		  av.no_categorie "
			+ "FROM ARTICLES_VENDUS ";
	
	private static final String sqlSelectById = ""
			+ "SELECT av.no_article, "
			+ "		  av.nom_article, "
			+ "		  av.description, "
			+ "		  av.date_debut_encheres, "
			+ "		  av.date_fin_encheres, "
			+ "		  av.prix_initial, "
			+ "		  av.prix_vente, "
			+ "		  av.no_utilisateur, "
			+ "		  av.no_categorie "
			+ "FROM ARTICLES_VENDUS "
			+ "WHERE av.no_article = ?";
	
	private static final String sqlInsert = ""
			+ "INSERT INTO ARTICLES_VENDUS (av.nom_article, "
			+ "		  						av.description, "
			+ "		  						av.date_debut_encheres, "
			+ "		  						av.date_fin_encheres, "
			+ "		  						av.prix_initial, "
			+ "		  						av.prix_vente, "
			+ "		  						av.no_utilisateur, "
			+ "		  						av.no_categorie) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String sqlUpdate = ""
			+ "UPDATE ARTICLES_VENDUS"
			+ "SET nom_article = ?, "
			+ "    description = ?, "
			+ "    date_debut_encheres = ?, "
			+ "    date_fin_encheres = ?, "
			+ "    prix_initial = ?, "
			+ "    prix_vente = ?, "
			+ "    no_utilisateur = ?, "
			+ "    no_categorie = ?, "
			+ "WHERE no_article = ? ";
	
	private static final String sqlDelete = ""
			+ "DELETE FROM ARTICLES_VENDUS"
			+ "WHERE no_article = ?";
	
	@Override
	public List<ArticleVendu> selectAll() throws DAOException, FunctionnalException {
//		ResultSet rs = null;
//		ArticleVendu av = null;
//		CategorieDAOJdbcImpl cjdbc = null;
//		List<ArticleVendu> lav = new ArrayList<ArticleVendu>();
//		try (Connection cnx = ConnectionProvider.getConnectionProvider();
//				PreparedStatement rqt = cnx.prepareStatement(sqlSelectAll);){
//			
//			rs = rqt.executeQuery();
//			
//			while(rs.next()) {
//				av = new ArticleVendu(rs.getInt("no_article"),
//									  rs.getString(""),
//									  rs.getString(""),
//									  rs.getDate("").toLocalDate(),
//									  rs.getDate("").toLocalDate(),
//									  rs.getInt(""),
//									  rs.getInt(""),
//									  )
//			}
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return lav;
		return null;
	}

	@Override
	public ArticleVendu selectById(int id) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(ArticleVendu t) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(ArticleVendu t) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(int t) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

}
