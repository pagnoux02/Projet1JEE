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
import fr.Enchere.BO.DTOOutArticle;
import fr.Enchere.BO.EtatVente;
import fr.Enchere.BO.Utilisateur;
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
			+ "SELECT no_article, "
			+ "		  nom_article, "
			+ "		  description, "
			+ "		  date_debut_encheres, "
			+ "		  date_fin_encheres, "
			+ "		  prix_initial, "
			+ "		  prix_vente, "
			+ "		  no_utilisateur, "
			+ "		  no_categorie, "
			+ "		  etat_vente "
			+ "FROM ARTICLES_VENDUS ";
	
	private static final String sqlSelectById = ""
			+ "SELECT no_article, "
			+ "		  nom_article, "
			+ "		  description, "
			+ "		  date_debut_encheres, "
			+ "		  date_fin_encheres, "
			+ "		  prix_initial, "
			+ "		  prix_vente, "
			+ "		  no_utilisateur, "
			+ "		  no_categorie, "
			+ "		  etat_vente "
			+ "FROM ARTICLES_VENDUS "
			+ "WHERE no_article = ?";
	
	private static final String sqlInsert = ""
			+ "INSERT INTO ARTICLES_VENDUS (nom_article, "
			+ "								description, "
			+ "								date_debut_encheres, "
			+ "								date_fin_encheres, "
			+ "								prix_initial, "
			+ "								prix_vente, "
			+ "								no_utilisateur, "
			+ "								no_categorie, "
			+ "								etat_vente) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	private static final String sqlUpdate = ""
			+ "UPDATE ARTICLES_VENDUS "
			+ "SET nom_article = ?, "
			+ "	   description = ?, "
			+ "	   date_debut_encheres = ?, "
			+ "	   date_fin_encheres = ?, "
			+ "	   prix_initial = ?, "
			+ "	   prix_vente = ?, "
			+ "	   no_utilisateur = ?, "
			+ "	   no_categorie = ?, "
			+ "	   etat_vente = ? "
			+ "WHERE no_article = ? ";
	
	private static final String sqlDelete = ""
			+ "DELETE FROM ARTICLES_VENDUS "
			+ "WHERE no_article = ? ";
	
	private static final String sqlFilterSelectFrom = ""
			+ "SELECT av.no_article, "
			+ "		  av.nom_article, "
			+ "		  av.description, "
			+ "		  av.date_debut_encheres, "
			+ "		  av.date_fin_encheres, "
			+ "		  av.prix_initial, "
			+ "		  av.prix_vente, "
			+ "		  av.no_utilisateur, "
			+ "		  av.no_categorie, "
			+ "		  av.etat_vente "
			+ "FROM ARTICLES_VENDUS av "
			+ "LEFT JOIN ENCHERES e ON e.no_article = av.no_article "
			+ "LEFT JOIN CATEGORIES c ON c.no_categorie = av.no_categorie "
			+ "WHERE av.nom_article LIKE ? ";
	
	private static final String sqlFilterUserClause = ""
			+ "AND av.no_utilisateur = ? ";
	
	private static final String sqlFilterEntredBidClause = ""
			+ "AND e.no_utilisateur = ? ";
	
	private static final String sqlFilterCategorieClause = ""
			+ "AND av.no_categorie = ? ";
	
	private static final String sqlFilterOpenBidClause = ""
			+ "AND av.date_debut_encheres < GETDATE() ";
	
	private static final String sqlFilterNotOpenBidClause = ""
			+ "AND av.date_debut_encheres > GETDATE() ";
	
	private static final String sqlFilterEndedBidClause = ""
			+ "AND av.date_fin_encheres < GETDATE() ";
	
	private static final String sqlFilterNotEndedBidClause = ""
			+ "AND av.date_fin_encheres > GETDATE() ";
	
	private static final String sqlFilterWonBidClause = ""
			+ "AND e.montant_enchere = av.prix_vente ";
	
	private static final String sqlUnion = ""
			+ "UNION ";
	
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
									  GenericDAOFactory.getRetraitDAO().selectById(rs.getInt("no_article")),
									  rs.getInt("prix_vente"),
									  EtatVente.StringToEtatVente(rs.getString("etat_vente")));
				lav.add(av);
			}
			
			if(lav == null || lav.isEmpty()) {
				throw new FunctionnalException("il n'y a aucun article à vendre");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
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
									  GenericDAOFactory.getRetraitDAO().selectById(rs.getInt("no_article")),
									  rs.getInt("prix_vente"),
									  EtatVente.StringToEtatVente(rs.getString("etat_vente")));
			}
			
			if(av == null) {
				throw new FunctionnalException("l'article n'a pas été trouvé");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
		}
		return av;
	}

	@Override
	public DTOOutArticle insertArticle(ArticleVendu t) throws DAOException, FunctionnalException {
		
		DTOOutArticle dtoOutArticle = new DTOOutArticle();

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
			
			int nbRows = rqt.executeUpdate();
			
			
			if(nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()) {
					t.setNoArticle(rs.getInt(1));
					dtoOutArticle.setIdArticle(t.getNoArticle());
				}
				dtoOutArticle.setMessage(Constantes.DAO_SQL_INSERT_REUSSITE);
			} else {
				throw new FunctionnalException(Constantes.DAO_SQL_INSERT_ECHEC);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
		}
		
		return dtoOutArticle;
	}

	@Override
	public String update(ArticleVendu t) throws DAOException, FunctionnalException {
		
		String result = Constantes.DAO_SQL_UPDATE_ECHEC;
		try(Connection cnx = ConnectionProvider.getConnectionProvider();
				PreparedStatement rqt = cnx.prepareStatement(sqlUpdate);){
			

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
			if(nbRows == 0) {
				throw new FunctionnalException(Constantes.DAO_SQL_DELETE_ECHEC);
			}
			result = Constantes.DAO_SQL_DELETE_REUSSITE;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
		}
		
		return result;
	}

	@Override
	public String insert(ArticleVendu t) throws DAOException, FunctionnalException {
		
		String s = Constantes.DAO_SQL_INSERT_ECHEC;
		
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
			
			int nbRows = rqt.executeUpdate();
			
			
			if(nbRows == 1) {
				ResultSet rs = rqt.getGeneratedKeys();
				if(rs.next()) {
					t.setNoArticle(rs.getInt(1));
					
				}
				s = Constantes.DAO_SQL_INSERT_REUSSITE;
			} else {
				throw new FunctionnalException(Constantes.DAO_SQL_INSERT_ECHEC);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
		}
		return s;
	}

	@Override
	public List<ArticleVendu> selectFilter(Utilisateur currentUser, String search, int idCategorie, String achatsVentes, boolean chkOpenBid,
			boolean chkMyCurrentBid, boolean chkMyWonBid, boolean chkMyCurrentSales, boolean chkMyNotStartedSales, boolean chkMyEndedSales)
			throws DAOException, FunctionnalException {
		
		List<ArticleVendu> lav = new ArrayList<ArticleVendu>();
		
		if(achatsVentes.equals("achats") && !chkOpenBid && !chkMyCurrentBid && !chkMyWonBid) {
			lav = selectAll();
		} else {
			
			String FilterQuery = sqlFilterSelectFrom;
			ResultSet rs = null;
			ArticleVendu av = null;
			
			// on construit la requete en fonction des filtres de recherché séléctionnés
			if(idCategorie > 0) {
				FilterQuery += sqlFilterCategorieClause;
			}
			if(achatsVentes.equals("achats")) {
				if(chkOpenBid) {
					FilterQuery += sqlFilterOpenBidClause + sqlFilterNotEndedBidClause;
				}
				if(chkMyCurrentBid) {
					if(!chkOpenBid) {
						FilterQuery += sqlFilterOpenBidClause + sqlFilterNotEndedBidClause;
					}
					FilterQuery += sqlFilterEntredBidClause;
				}
				if(chkMyWonBid) {
					if(chkOpenBid || chkMyCurrentBid) {
						FilterQuery += sqlUnion + sqlFilterSelectFrom;
						if(idCategorie > 0) {
							FilterQuery += sqlFilterCategorieClause;
						}
					}
					FilterQuery += sqlFilterEntredBidClause + sqlFilterEndedBidClause + sqlFilterWonBidClause;
				}
			}
			if(achatsVentes.equals("ventes")) {
				FilterQuery += sqlFilterUserClause;
				if(chkMyCurrentSales) {
					FilterQuery += sqlFilterOpenBidClause + sqlFilterNotEndedBidClause;
				}
				if(chkMyNotStartedSales) {
					if(chkMyCurrentSales) {
						FilterQuery += sqlUnion + sqlFilterSelectFrom + sqlFilterUserClause;
						if(idCategorie > 0) {
							FilterQuery += sqlFilterCategorieClause;
						}
					}
					FilterQuery += sqlFilterNotOpenBidClause;
				}
				if(chkMyEndedSales) {
					if(chkMyCurrentSales || chkMyNotStartedSales) {
						FilterQuery += sqlUnion + sqlFilterSelectFrom + sqlFilterUserClause;
						if(idCategorie > 0) {
							FilterQuery += sqlFilterCategorieClause;
						}
					}
					FilterQuery += sqlFilterEndedBidClause;
				}
			}
			
			System.out.println(FilterQuery + "||" + search + "||" + idCategorie + "||" + currentUser.getNumeroUtilisateur());

			try (Connection cnx = ConnectionProvider.getConnectionProvider();
					PreparedStatement rqt = cnx.prepareStatement(FilterQuery);){
				
				String s = "%" + search + "%";
				
				rqt.setString(1, s);
				
				if(idCategorie > 0) {
					rqt.setInt(2, idCategorie);
				}
				if(achatsVentes.equals("achats")) {
					if(chkMyWonBid) {
						if(chkMyCurrentBid) {
							if(idCategorie > 0) {
								rqt.setString(4, s);
								rqt.setInt(5, idCategorie);
								rqt.setInt(6, currentUser.getNumeroUtilisateur());
							} else {
								rqt.setString(3, s);
								rqt.setInt(4, currentUser.getNumeroUtilisateur());
							}
						} else {
							if(idCategorie > 0) {
								rqt.setInt(3, currentUser.getNumeroUtilisateur());
							} else {
								rqt.setInt(2, currentUser.getNumeroUtilisateur());
							}
						}
					} else {
						if(chkMyCurrentBid) {
							if(idCategorie > 0) {
								rqt.setInt(3, currentUser.getNumeroUtilisateur());
							} else {
								rqt.setInt(2, currentUser.getNumeroUtilisateur());
							}
						}
					}
				}
				if(achatsVentes.equals("ventes")) {
					if(idCategorie > 0) {
						rqt.setInt(3, currentUser.getNumeroUtilisateur());
					} else {
						rqt.setInt(2, currentUser.getNumeroUtilisateur());
					}
					if((chkMyEndedSales && chkMyNotStartedSales) || (chkMyEndedSales && chkMyCurrentSales) || (chkMyNotStartedSales && chkMyCurrentSales)) {
						if(chkMyCurrentSales && chkMyNotStartedSales && chkMyCurrentSales) {
							if(idCategorie > 0) {
								rqt.setString(7, s);
								rqt.setInt(8, idCategorie);
								rqt.setInt(9, currentUser.getNumeroUtilisateur());
							} else {
								rqt.setString(5, s);
								rqt.setInt(6, currentUser.getNumeroUtilisateur());
							}
						} else {
							if(idCategorie > 0) {
								rqt.setString(4, s);
								rqt.setInt(5, idCategorie);
								rqt.setInt(6, currentUser.getNumeroUtilisateur());
							} else {
								rqt.setString(3, s);
								rqt.setInt(4, currentUser.getNumeroUtilisateur());
							}
						}
					}
				}
				
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
										  GenericDAOFactory.getRetraitDAO().selectById(rs.getInt("no_article")),
										  rs.getInt("prix_vente"),
										  EtatVente.StringToEtatVente(rs.getString("etat_vente")));
					lav.add(av);
				}
				
				if(lav == null || lav.isEmpty()) {
					throw new FunctionnalException("il n'y a aucun article à vendre");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
			}
		}
		return lav;
	}
	
	
}
