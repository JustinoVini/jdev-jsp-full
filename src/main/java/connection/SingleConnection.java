package connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class SingleConnection {
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
				Class.forName("org.postgresql.Driver"); /* Carrega o driver do banco */

				// Especifique o caminho absoluto do arquivo database-config.txt
				String filePath = "E:\\ws-jdev-curso\\jdev-jsp-full\\database-config.txt";

				// Crie um objeto FileInputStream com o caminho absoluto
				InputStream inputStream = new FileInputStream(filePath);

				Properties properties = new Properties();
				properties.load(inputStream);
				String dbUrl = properties.getProperty("db.url");
				String dbUser = properties.getProperty("db.username");
				String dbPassword = properties.getProperty("db.password");

				connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				connection.setAutoCommit(false); /* Para não fazer alterações no banco sem comando */
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
