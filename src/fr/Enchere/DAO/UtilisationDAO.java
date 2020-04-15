package fr.Enchere.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.Enchere.BO.Utilisateur;
import fr.Enchere.Exception.DAOException;
import fr.Enchere.Exception.FunctionnalException;
import fr.Enchere.JDBCConnection.ConnectionProvider;
import fr.Enchere.util.GestionDAO;

public abstract class UtilisationDAO implements GenericDAO<Utilisateur> {
	
	private static final String SELECT_ALL = "select * from utilisateur";
	
	private static final String SELECT_BY_ID = "select * from utilisateur where no_utilisateur = ?";

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
				utilisateur.setPrenon(resultat.getString("prenom"));
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listUtilisateurs;
	}

	@Override
	public Utilisateur sellectByid(int id) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert(Utilisateur t) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String update(Utilisateur t) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Delect(Utilisateur t) throws DAOException, FunctionnalException {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
