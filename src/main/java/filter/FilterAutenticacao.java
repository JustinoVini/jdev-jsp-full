package filter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.collections.functors.WhileClosure;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import connection.SingleConnection;
import dao.DaoVersionadorBanco;

@WebFilter(urlPatterns = { "/principal/*" }) /* Intercepta todas as requisições que vierem do projeto ou mapeamento */
public class FilterAutenticacao extends HttpFilter implements Filter {

	private static Connection connection;

	private static final long serialVersionUID = 8231324182035280309L;

	public FilterAutenticacao() {

	}

	/* Encerra os processos quando o server é parado */
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/* Intercepta todas as req do projeto e res do sistema */
	/* Tudo que fizer no sistema vai fazer por aqui */
	/* Validação de autenticação */
	/* Dar commit e rollback de transações no banco */
	/* Validar e fazer redirecionamento de paginas */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			/* Pegar usuario logado */
			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath(); /* url que está sendo acessada */

			/* Validar se está logado senão redireciona para login */
			if (usuarioLogado == null
					&& !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { /* Não está logado */

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor realize o login!");

				redireciona.forward(request, response);
				return; /* Para a execução e redireciona para o login */
			} else {
				chain.doFilter(request, response);
			}

			connection.commit(); /*Se tudo certo, commita as alterações no banco de dados*/
			
		} catch (Exception e) {
			e.printStackTrace();
			
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				connection.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}

	}

	/* inicia os processos ou recursos quando o servido sobe o projeto */
	// iniciar a conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnection.getConnection();

		DaoVersionadorBanco daoVersionadorBanco = new DaoVersionadorBanco();

		String caminhoPastaSql = fConfig.getServletContext().getRealPath("versionadorbancosql") + File.separator;

		File[] files = new File(caminhoPastaSql).listFiles();

		try {
			for (File file : files) {

				boolean arquivoJaRodado = daoVersionadorBanco.arquivoSqlRodado(file.getName());

				if (!arquivoJaRodado) {

					FileInputStream entradaArquivo = new FileInputStream(file);

					Scanner lerArquivo = new Scanner(entradaArquivo, "UTF-8");

					StringBuilder sql = new StringBuilder();

					while (lerArquivo.hasNext()) {

						sql.append(lerArquivo.nextLine());
						sql.append("\n");
					}
					
					connection.prepareStatement(sql.toString()).execute();
					daoVersionadorBanco.gravaArquivoSqlRodado(file.getName());
					connection.commit();
					lerArquivo.close();
				}
			}
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
