package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}

	/* Método para gravar o usuario */
	public void gravarUser(ModelLogin objeto) throws SQLException {

		String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, objeto.getLogin());
		statement.setString(2, objeto.getSenha());
		statement.setString(3, objeto.getNome());
		statement.setString(4, objeto.getEmail());
		
		statement.execute();

		connection.commit();
		
	}

}
