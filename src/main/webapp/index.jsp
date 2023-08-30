<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<title>Acesso - Login</title>

	<style type="text/css">
		form {
		position: absolute;
		top: 40%;
		left: 33%;
		}
		
		h5 {
		position: absolute;
		top: 30%;
		left: 33%;
		}
		
		h3 {
		position: absolute;
		top: 70%;
		left: 33%;
		font-size: 15px;
		color: red;
		}
	</style>

</head>
<body>

	
				<h5>Bem Vindo! Faça Login com seus dados.</h5>


		<form action="/curso-jsp/ServletLogin" method="post" class="row g-3 needs-validation" novalidate>
			
			<input type="hidden" value="<%=request.getParameter("url")%>" name="url" /> 
			
			
			<div class="col-md-12">
				<label class="form-label">Login: </label> 
				<input class="form-control" placeholder="Entre com o Login" type="text" name="login" required/>
					<div class="invalid-feedback">
      					Obrigatorio
    				</div>
					<div class="valid-feedback">
      					ok
    				</div>
			</div>
			
			<div class="col-md-12">
				<label class="form-label">Senha:</label>
				<input class="form-control" placeholder="Entre com a Senha" type="password" name="senha" required/>
				<div class="invalid-feedback">
      					Obrigatorio
    			</div>
				<div class="valid-feedback">
      					ok
    			</div>
			</div>
			
			<input type="submit" value="Enviar" class="btn btn-primary">

		</form>

		<h3>${msg}</h3>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
		
	<script type="text/javascript">
	// Example starter JavaScript for disabling form submissions if there are invalid fields
	(function () {
	  'use strict'

	  // Fetch all the forms we want to apply custom Bootstrap validation styles to
	  var forms = document.querySelectorAll('.needs-validation')

	  // Loop over them and prevent submission
	  Array.prototype.slice.call(forms)
	    .forEach(function (form) {
	      form.addEventListener('submit', function (event) {
	        if (!form.checkValidity()) {
	          event.preventDefault()
	          event.stopPropagation()
	        }

	        form.classList.add('was-validated')
	      }, false)
	    })
	})()
	</script>

</body>
</html>