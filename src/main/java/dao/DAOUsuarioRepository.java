package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnection;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}

	/* Método para gravar o usuario */
	public ModelLogin gravarUser(ModelLogin objeto) throws Exception {

		String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, objeto.getLogin());
		statement.setString(2, objeto.getSenha());
		statement.setString(3, objeto.getNome());
		statement.setString(4, objeto.getEmail());
		
		statement.execute();

		connection.commit();
		
		return this.consultaUsuario(objeto.getLogin());
	}
	
	public ModelLogin consultaUsuario(String login) throws Exception {
	    ModelLogin modelLogin = new ModelLogin();
	    
	    /*Passando parametro diretamente na consulta*/
	    String sql = "SELECT * FROM model_login where upper(login) = upper('"+login+"')";
	    
	    /*Preparando o sql*/
	    PreparedStatement statement = connection.prepareStatement(sql);
	    
	    /*Pegando o resultado*/
	    ResultSet resultado = statement.executeQuery(); // Não passa o SQL aqui
	    
	    
	    /*Executa até ter dados*/
	    while (resultado.next()) { /*Se tem resultado*/
	        modelLogin.setId(resultado.getLong("id"));
	        modelLogin.setEmail(resultado.getString("email"));
	        modelLogin.setLogin(resultado.getString("login"));
	        modelLogin.setSenha(resultado.getString("senha"));
	        modelLogin.setNome(resultado.getString("nome"));
	    }
	    
	    /*Retorna o resultado*/
	    return modelLogin;
	}

}