package fr.Enchere.JDBCConnection;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class ConnectionProvider {
	
	private static DataSource dataSource;

	 static {
		 Context context;
		 try {
			 context = new InitialContext();
			 ConnectionProvider.dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx_avis");
		 } catch(NamingException namingException) {
			 namingException.printStackTrace();
			 throw new RuntimeException("imposible de'acc�eder BDD");
		 }
	}

	public static Connection getConnectionProvider() throws SQLException{
		return ConnectionProvider.dataSource.getConnection();
	}

}
