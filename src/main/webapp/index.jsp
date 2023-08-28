<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP - Login</title>
</head>
<body>

	<h1>Bem Vindo! Faça Login com seus dados.</h1>

	<form action="/curso-jsp/ServletLogin" method="post">

	<input type="hidden" value="<%= request.getParameter("url") %>" name="url"/>

		<table>
			<tr>
				<td><label>Login: </label></td>
			</tr>
			<tr>
				<td><input placeholder="Entre com o Login" type="text"
					name="login" /></td>
			</tr>
			<tr>
				<td><label>Senha: </label></td>
			</tr>
			<tr>
				<td><input placeholder="Entre com a Senha" type="password"
					name="senha" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Enviar"></td>
			</tr>
		</table>
	</form>
	
	<h4>${msg}</h4>

</body>
</html>