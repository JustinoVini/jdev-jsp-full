package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.ModelLogin;

@WebFilter(urlPatterns = { "/principal/*" }) /* Intercepta todas as requisições que vierem do projeto ou mapeamento */
public class FilterAutenticacao extends HttpFilter implements Filter {

	private static final long serialVersionUID = 8231324182035280309L;

	public FilterAutenticacao() {

	}

	/* Encerra os processos quando o server é parado */
	public void destroy() {
	}

	/* Intercepta todas as req do projeto e res do sistema */
	/* Tudo que fizer no sistema vai fazer por aqui */
	/* Validação de autenticação */
	/* Dar commit e rollback de transações no banco */
	/* Validar e fazer redirecionamento de paginas */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();

		/* Pegar usuario logado */
		String usuarioLogado = (String) session.getAttribute("usuario");

		String urlParaAutenticar = req.getServletPath(); /* url que está sendo acessada */

		/* Validar se está logado senão redireciona para login */
		if (usuarioLogado == null && 
				!urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) { /* Não está logado */

			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor realize o login!");

			redireciona.forward(request, response);
			return; /* Para a execução e redireciona para o login */
		} else {
			chain.doFilter(request, response);
		}

	}

	/* inicia os processos ou recursos quando o servido sobe o projeto */
	// iniciar a conexão com o banco
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
