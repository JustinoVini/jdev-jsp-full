<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Curso JSP</title>
</head>
<body>
	
	<h1>Bem vindo ao curso de JSP</h1>
	
	<%
	out.print("Seu sucesso garantido");
	%>
	
	<form action="receber-nome.jsp">
	
	<input name="nome"/>
	<input name="idade"/>
	
	<input type="submit" value="enviar">
	
	</form>

</body>
</html>