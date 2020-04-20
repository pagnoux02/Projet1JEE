/**
 * 
 */
package fr.Enchere.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.EtatVente;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.JDBCConnection.ConnectionProvider;
import fr.Enchere.util.Constantes;


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
			+ "		  av.no_categorie, "
			+ "		  av.etat_vent "
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
			+ "		  av.no_categorie, "
			+ "		  av.etat_vent "
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
			+ "		  						av.no_categorie, "
			+ "								av.etat_vent) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String sqlUpdate = ""
			+ "UPDATE ARTICLES_VENDUS "
			+ "SET nom_article = ?, "
			+ "    description = ?, "
			+ "    date_debut_encheres = ?, "
			+ "    date_fin_encheres = ?, "
			+ "    prix_initial = ?, "
			+ "    prix_vente = ?, "
			+ "    no_utilisateur = ?, "
			+ "    no_categorie = ?, "
			+ "	   etat_vente = ? "
			+ "WHERE no_article = ? ";
	
	private static final String sqlDelete = ""
			+ "DELETE FROM ARTICLES_VENDUS "
			+ "WHERE no_article = ? ";
	
	@Override
	public List<ArticleVendu> selectAll() throws DAOException, FunctionnalException {
		ResultSet rs = null;
		ArticleVendu av = null;
		List<ArticleVendu> lav = new ArrayList<ArticleVendu>();
		try (Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlSelectAll);){
			
			rs = rqt.executeQuery();
			
			while(rs.next()) {
				av = new ArticleVendu(rs.getInt("no_article"),
									  rs.getString("nom_article"),
									  rs.getString("description"),
									  rs.getDate("date_debut_encheres").toLocalDate(),
									  rs.getDate("date_fin_encheres").toLocalDate(),
									  rs.getInt("prix_initial"),
									  GenericDAOFactory.getCategorieDAO().selectById(rs.getInt("no_categorie")),
									  GenericDAOFactory.getUtilisateurDao().selectById(rs.getInt("no_utilisateur")),
									  rs.getInt("prix_vente"),
									  EtatVente.StringToEtatVente(rs.getString("etat_vente")));
				lav.add(av);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lav;
	}

	@Override
	public ArticleVendu selectById(int id) throws DAOException, FunctionnalException {
		ResultSet rs = null;
		ArticleVendu av = null;
		try (Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlSelectById);){
			
			rqt.setInt(1, id);
			rs = rqt.executeQuery();
			
			if(rs.next()) {
				av = new ArticleVendu(rs.getInt("no_article"),
									  rs.getString("nom_article"),
									  rs.getString("description"),
									  rs.getDate("date_debut_encheres").toLocalDate(),
									  rs.getDate("date_fin_encheres").toLocalDate(),
									  rs.getInt("prix_initial"),
									  GenericDAOFactory.getCategorieDAO().selectById(rs.getInt("no_categorie")),
									  GenericDAOFactory.getUtilisateurDao().selectById(rs.getInt("no_utilisateur")),
									  rs.getInt("prix_vente"),
									  EtatVente.StringToEtatVente(rs.getString("etat_vente")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return av;
	}

	@Override
	public String insert(ArticleVendu t) throws DAOException, FunctionnalException {
		
		String result = Constantes.DAO_SQL_INSERT_ECHEC;
		try(Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);){
			
			rqt.setString(1, t.getNomArticle());
			rqt.setString(2, t.getDescription());
			rqt.setDate(3, Date.valueOf(t.getDateDebutEncheres()));
			rqt.setDate(4, Date.valueOf(t.getDateFinEncheres()));
			rqt.setInt(5, t.getMiseAPrix());
			rqt.setInt(6, t.getPrixVente());
			rqt.setInt(7, t.getUtilisateur().getNumeroUtilisateur());
			rqt.setInt(8, t.getCategorie().getNoCategorie());
			rqt.setString(9, t.getEtatVente().toString());
			rqt.setInt(10, t.getNoArticle());
			
			int nbRows = rqt.executeUpdate();
			if(nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()) {
					t.setNoArticle(rs.getInt(1));
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
	public String update(ArticleVendu t) throws DAOException, FunctionnalException {
		
		String result = Constantes.DAO_SQL_UPDATE_ECHEC;
		try(Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlUpdate);){
			
			rqt.setString(1, t.getDescription());
			rqt.setDate(2, Date.valueOf(t.getDateDebutEncheres()));
			rqt.setDate(3, Date.valueOf(t.getDateFinEncheres()));
			rqt.setInt(4, t.getMiseAPrix());
			rqt.setInt(2, t.getPrixVente());
			rqt.setInt(3, t.getUtilisateur().getNumeroUtilisateur());
			rqt.setInt(7, t.getCategorie().getNoCategorie());
			rqt.setString(8, t.getEtatVente().toString());
			rqt.setString(9, t.getNomArticle());
			
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
