package fr.Enchere.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.ArticleVendu;
import fr.Enchere.BO.Enchere;
import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.JDBCConnection.ConnectionProvider;

public class EnchereDAOJdbcImpl implements EnchereDAO {

	private static final String SELECT_BY_IDARTICLE_MAX_MONTANT = "select no_article, no_utilisateur ,date_enchere, montant_enchere from encheres where no_article = ? ORDER BY montant_enchere desc";
	private static final String SELECT_BY_UTILISATEUR_ENCOURS= "select montant_enchere, date_enchere ,e.no_article,e.no_utilisateur\r\n" + 
			"from encheres e\r\n" + 
			"join ARTICLES_VENDUS av on e.no_article = av.no_article\r\n" + 
			"where e.date_enchere between av.date_debut_encheres and av.date_fin_encheres\r\n" + 
			"and e.no_utilisateur = ?\r\n" + 
			"";
	private static final String SELECT_BY_WIN = "SELECT e.montant_enchere, e.no_article,e.no_utilisateur, e.date_enchere\r\n" + 
			"  FROM ENCHERES e\r\n" + 
			" INNER JOIN ARTICLES_VENDUS av ON e.no_article = av.no_article\r\n" + 
			" WHERE e.date_enchere > av.date_fin_encheres\r\n" + 
			"   AND e.no_article = ?\r\n" + 
			"   AND e.montant_enchere = av.prix_vente;";
	private static final String UPDATE_ENCHERE = "update encheres set date_enchere=? , montant_enchere=? where no_utilisateur=? and no_article =?";
	private static final  String INSERT_ENCHERE = "insert into encheres  values no_utilisateur, no_article, date_enchere, montant_enchere (?,?,?,?)";
	private static final String DELETE_ENCHERE = "delete from encheres where id=?";

	@Override
	public String insert(Enchere enchere) throws DAOException, FunctionnalException {
		if(enchere==null)
		{
			throw new FunctionnalException("erreur insert enchere vide");
		}

		String string = "";

		try(Connection Connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement statementInsert = Connection.prepareStatement(INSERT_ENCHERE)){

			statementInsert.setInt(1, enchere.getNoUtilisateur());
			statementInsert.setInt(2, enchere.getNoArticle());
			statementInsert.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			statementInsert.setInt(4, enchere.getMontant_enchere());
			statementInsert.executeUpdate();

			string = "Success l'insertion s'est bien passé";

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} catch (RuntimeException e1) {
			throw new DAOException(e1.getMessage());
		}

		return string;
	}

	@Override
	public String update(Enchere enchere) throws DAOException, FunctionnalException {
		if(enchere==null)
		{
			throw new FunctionnalException("erreur update enchere vide");
		}

		String string = "";

		try(Connection Connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement statementInsert = Connection.prepareStatement(UPDATE_ENCHERE)){

		
			statementInsert.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
			statementInsert.setInt(2, enchere.getMontant_enchere());
			statementInsert.setInt(3, enchere.getNoUtilisateur());
			statementInsert.setInt(4, enchere.getNoArticle());
			statementInsert.executeUpdate();

			string = "Success l'update s'est bien passé";

		} catch (SQLException e) {
			throw new DAOException(e.getMessage());
		} catch (RuntimeException e1) {
			throw new DAOException(e1.getMessage());
		}

		return string;
	}

	@Override
	public String delete(int id) throws DAOException, FunctionnalException {
		String string = "";
		try(Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement pstmt = cnx.prepareStatement(DELETE_ENCHERE);)
		{
			string = "Succï¿½es la mise a jour s'est bien passï¿½";
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur delete Retrait");
		}
		return string ;
	}
//  Pour get le nom et le prix le plus cher 
	@Override
	public Enchere FindEnchereByIdArticle(int id) throws DAOException , FunctionnalException {
		Enchere enchere = new Enchere();
		try(Connection cnx = ConnectionProvider.getConnectionProvider(); 
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_IDARTICLE_MAX_MONTANT);)
		{

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();


			if(rs.next())
			{
				enchere.setNoArticle(rs.getInt("no_article")); 
				enchere.setNoUtilisateur(rs.getInt("no_utilisateur"));
				enchere.setDateEnchere(rs.getDate("date_enchere"));
				enchere.setMontant_enchere(rs.getInt("montant_enchere"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur findUser  enchere");
		}
		return enchere;
	}

	// Listes d'enchere en cours
	@Override
	public List<Enchere> SelectByIdEnchereEnCours(int id) throws DAOException, FunctionnalException {
		List<Enchere> listEnchere = new ArrayList<Enchere>();
		try(Connection cnx = ConnectionProvider.getConnectionProvider(); 
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_UTILISATEUR_ENCOURS);)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				
			listEnchere.add(new Enchere(rs.getInt("e.no_utilisateur"),rs.getInt("e.no_article"),GenericDAOFactory.getArticleVenduDAO().selectById(rs.getInt("e.no_article")),
					GenericDAOFactory.getUtilisateurDao().selectById(rs.getInt("e.no_utilisateur")),rs.getDate("date_enchere"),rs.getInt("montant_enchere") ));
			
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur select enchere courant");
		}
		return listEnchere;
	}
//	enchères remportées
	@Override
	public List<Enchere> SelectByIdEnchereWIn(int id) throws DAOException, FunctionnalException {
		List<Enchere> listEnchere = new ArrayList<Enchere>();
		try(Connection cnx = ConnectionProvider.getConnectionProvider(); 
				PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_WIN);)
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			while(rs.next())
			{
				
			listEnchere.add(new Enchere(rs.getInt("e.no_utilisateur"),rs.getInt("e.no_article"),GenericDAOFactory.getArticleVenduDAO().selectById(rs.getInt("e.no_article")),
					GenericDAOFactory.getUtilisateurDao().selectById(rs.getInt("e.no_utilisateur")),rs.getDate("date_enchere"),rs.getInt("montant_enchere") ));
			
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur select enchere courant");
		}
		return listEnchere;
	}

	@Override
	public List<Enchere> selectAll() throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectById(int id) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

}
