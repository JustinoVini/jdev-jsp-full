package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String url = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String password = "";
	private static String user = "postgres";
	private static Connection connection = null;
	
	public static Connection connection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if (connection == null) {
				Class.forName("org.postgresql.Driver"); /*Carrega o driver do banco*/
				connection = DriverManager.getConnection(url, user, password);
				connection.setAutoCommit(false); /*Para não fazer alterações no banco sem comando*/
				System.out.println("Conectou com sucesso!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
