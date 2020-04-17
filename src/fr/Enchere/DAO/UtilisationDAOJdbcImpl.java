package fr.Enchere.DAO;

import java.awt.image.CropImageFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.regexp.internal.recompile;

import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.JDBCConnection.ConnectionProvider;
import fr.Enchere.util.Constantes;
import fr.Enchere.util.GestionDAO;

public class UtilisationDAOJdbcImpl implements UtilisationInterfaceDAO {
	
	private static final String SELECT_ALL = "select * from utilisateurs";
	
	private static final String SELECT_BY_ID = "select * from utilisateurs where no_utilisateur = ?";
	
	private static final String INSERT_UTILISATEUR = "insert into utilisateurs (pseudo,nom,prenom,email,telephone"
			+ ",rue,code_postal,ville,mot_de_passe,credit,administrateur) values (?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE_UTILISATEUR = "update utilisateurs set pseudo = ? , nom = ? , prenom = ? , email = ?"
			+ ", telephone = ? , rue = ? , code_postal = ? , ville = ? , mot_de_passe = ? , credit = ?"
			+ ", administrateur = ? where no_utilisateur = ?";
	
	private static final String DELETE_UTILISATEUR = "delete from utilisateurs where no_utilisateur = ?";
	
	private static final String SELECT_UTILISATEUR_BY_PSEUDO_AND_PASSWORD = "select * from utilisateurs where pseudo = ? "
			+ "and mot_de_passe = ?";
	
	private static final String SELECT_EMAIL_IN_BDD = "select email from utilisateurs";
	
	private static final String SELECT_PSEUDO_IN_BDD = "select pseudo from utilisateurs";
	
	
	@Override
	public List<Utilisateur> selectAll() throws DAOException, FunctionnalException {
		
		ArrayList<Utilisateur> listUtilisateurs = new ArrayList<>();
		
		try(Connection connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement selectAllUtilisateur = connection.prepareStatement(SELECT_ALL)){
			
			ResultSet resultat = selectAllUtilisateur.executeQuery();
			
			while(resultat.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNumeroUtilisateur(resultat.getInt("no_utilisateur"));
				utilisateur.setPseudo(resultat.getString("pseudo"));
				utilisateur.setNom(resultat.getString("nom"));
				utilisateur.setPrenom(resultat.getString("prenom"));
				utilisateur.setEmail(resultat.getString("email"));
				utilisateur.setTelephone(resultat.getString("telephone"));
				utilisateur.setRue(resultat.getString("rue"));
				utilisateur.setCodePostal(resultat.getString("code_postal"));
				utilisateur.setVille(resultat.getString("ville"));
				utilisateur.setMotDePasse(resultat.getString("mot_de_passe"));
				utilisateur.setCredit(resultat.getInt("credit"));
				utilisateur.setAdministrateur(GestionDAO.recupBoolean(resultat.getString("administrateur")));
				listUtilisateurs.add(utilisateur);
			}
			
			if(listUtilisateurs == null || listUtilisateurs.isEmpty()) {
				throw new FunctionnalException("il aucun utilisateur");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur pour :" + e.getMessage());
		}
		//System.out.println(listUtilisateurs.toString());
		return listUtilisateurs;
	}

	@Override
	public Utilisateur selectById(int id) throws DAOException, FunctionnalException {

		Utilisateur utilisateur = new Utilisateur();
		
		try(Connection connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement selectById = connection.prepareStatement(SELECT_BY_ID)){
			
			selectById.setInt(1, id);
			
			ResultSet resultat = selectById.executeQuery();
			
			while(resultat.next()) {
				utilisateur.setNumeroUtilisateur(resultat.getInt("no_utilisateur"));
				utilisateur.setPseudo(resultat.getString("pseudo"));
				utilisateur.setNom(resultat.getString("nom"));
				utilisateur.setPrenom(resultat.getString("prenom"));
				utilisateur.setEmail(resultat.getString("email"));
				utilisateur.setTelephone(resultat.getString("telephone"));
				utilisateur.setRue(resultat.getString("rue"));
				utilisateur.setCodePostal(resultat.getString("code_postal"));
				utilisateur.setVille(resultat.getString("ville"));
				utilisateur.setMotDePasse(resultat.getString("mot_de_passe"));
				utilisateur.setCredit(resultat.getInt("credit"));
				utilisateur.setAdministrateur(GestionDAO.recupBoolean(resultat.getString("administrateur")));
			}
			
			if(utilisateur == null) {
				throw new FunctionnalException("Il y a aucun utilisateur");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur pour :" + e.getMessage());
		}
		
		return utilisateur;
	}

	@Override
	public String insert(Utilisateur utilisateur) throws DAOException, FunctionnalException {

		String string = "";
		
		try(Connection connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement insertUtilisateur = connection.prepareStatement(INSERT_UTILISATEUR,PreparedStatement.RETURN_GENERATED_KEYS)){
			
			insertUtilisateur.setString(1, utilisateur.getPseudo());
			insertUtilisateur.setString(2, utilisateur.getNom());
			insertUtilisateur.setString(3, utilisateur.getPrenom());
			insertUtilisateur.setString(4, utilisateur.getEmail());
			insertUtilisateur.setString(5, utilisateur.getTelephone());
			insertUtilisateur.setString(6, utilisateur.getRue());
			insertUtilisateur.setString(7, utilisateur.getCodePostal());
			insertUtilisateur.setString(8, utilisateur.getVille());
			insertUtilisateur.setString(9, utilisateur.getMotDePasse());
			insertUtilisateur.setInt(10, utilisateur.getCredit());
			insertUtilisateur.setByte(11, GestionDAO.recupBit(utilisateur.getAdministrateur()));
			
			System.out.println(GestionDAO.recupBit(utilisateur.getAdministrateur()));
			
			int resultat = insertUtilisateur.executeUpdate();
			
			ResultSet ress = insertUtilisateur.getGeneratedKeys();
			
			while(ress.next()) {
				utilisateur.setNumeroUtilisateur(ress.getInt(1));
			}
			
			if(resultat == 0) {
				throw new FunctionnalException("L'insertion c'est mal passer");
			}
			
			string = "Succèes l'insertion a été pris en compte";
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new DAOException("Erreur pour :" + e.getMessage());
		}
		
		return string;
	}

	@Override
	public String update(Utilisateur utilisateur) throws DAOException, FunctionnalException {
		
		String string = "";
		
		try(Connection connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement updateUtilisateur = connection.prepareStatement(UPDATE_UTILISATEUR)){
			
			updateUtilisateur.setString(1, utilisateur.getPseudo());
			updateUtilisateur.setString(2, utilisateur.getNom());
			updateUtilisateur.setString(3, utilisateur.getPrenom());
			updateUtilisateur.setString(4, utilisateur.getEmail());
			updateUtilisateur.setString(5, utilisateur.getTelephone());
			updateUtilisateur.setString(6, utilisateur.getRue());
			updateUtilisateur.setString(7, utilisateur.getCodePostal());
			updateUtilisateur.setString(8, utilisateur.getVille());
			updateUtilisateur.setString(9, utilisateur.getMotDePasse());
			updateUtilisateur.setInt(10, utilisateur.getCredit());
			updateUtilisateur.setByte(11, GestionDAO.recupBit(utilisateur.getAdministrateur()));
			updateUtilisateur.setInt(12, utilisateur.getNumeroUtilisateur());
		
			int ress = updateUtilisateur.executeUpdate();
			
			if(ress == 0) {
				throw new FunctionnalException("la mise à jour c'est mal passer");
			}
			
			string = "Succèes la mise a jour c'est bien passer";
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur pour :" + e.getMessage());
		}
		
		return string;
	}

	@Override
	public String delete(int id) throws DAOException, FunctionnalException {
		
		String string = "";
		
		try(Connection connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement deleteUtilisateur = connection.prepareStatement(DELETE_UTILISATEUR)){
			
			deleteUtilisateur.setInt(1, id);
			
			int ress = deleteUtilisateur.executeUpdate();
			
			if(ress == 0) {
				throw new FunctionnalException("La suppretion c'est mal passer");
			}
			
			string = "La suppretion c'est bien passer";
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur pour :" + e.getMessage());
		}
		
		return string;
	}

	@Override
	public Utilisateur userEstDansLaBase(String pseudo, String motDePasse) throws FunctionnalException, DAOException {
		
		Utilisateur utilisateur = new Utilisateur();
		
		try(Connection connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement userInBDD = connection.prepareStatement(SELECT_UTILISATEUR_BY_PSEUDO_AND_PASSWORD)){
			
			userInBDD.setString(1, pseudo);
			userInBDD.setString(2, motDePasse);
			
			ResultSet resultat = userInBDD.executeQuery();
			
			while(resultat.next()) {
				utilisateur.setNumeroUtilisateur(resultat.getInt("no_utilisateur"));
				utilisateur.setPseudo(resultat.getString("pseudo"));
				utilisateur.setNom(resultat.getString("nom"));
				utilisateur.setPrenom(resultat.getString("prenom"));
				utilisateur.setEmail(resultat.getString("email"));
				utilisateur.setTelephone(resultat.getString("telephone"));
				utilisateur.setRue(resultat.getString("rue"));
				utilisateur.setCodePostal(resultat.getString("code_postal"));
				utilisateur.setVille(resultat.getString("ville"));
				utilisateur.setMotDePasse(resultat.getString("mot_de_passe"));
				utilisateur.setCredit(resultat.getInt("credit"));
				utilisateur.setAdministrateur(GestionDAO.recupBoolean(resultat.getString("administrateur")));
			}
			
			if(utilisateur == null || utilisateur.getPseudo() == null || utilisateur.getPseudo().isEmpty()) {
				throw new FunctionnalException("aucun utilisateur de trouver");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("Erreur pour :" + e.getMessage());
		}
		
		return utilisateur;
	}

	@Override
	public List<String> emailInBDD() throws DAOException, FunctionnalException {

		List<String> listStrings = new ArrayList<>();
		
		try(Connection connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement selectEmail = connection.prepareStatement(SELECT_EMAIL_IN_BDD)){
			
			ResultSet resultal = selectEmail.executeQuery();
			
			while(resultal.next()) {
				String ress = "";
				ress = resultal.getString("email");
				listStrings.add(ress);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
		}
		
		return listStrings;
		
	}

	@Override
	public List<String> pseudoInBDD() throws DAOException, FunctionnalException {

		List<String> listStrings = new ArrayList<>();
		
		try(Connection connection = ConnectionProvider.getConnectionProvider();
				PreparedStatement selectPseudo = connection.prepareStatement(SELECT_PSEUDO_IN_BDD)){
			
			ResultSet resultal = selectPseudo.executeQuery();
			
			while(resultal.next()) {
				String ress = "";
				ress = resultal.getString("pseudo");
				listStrings.add(ress);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new DAOException(Constantes.ERREUR_DAO_POUR + e.getMessage());
		}
		
		return listStrings;
	}
}
