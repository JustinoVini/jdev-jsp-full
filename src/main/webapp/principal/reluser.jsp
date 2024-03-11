<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="model.ModelLogin"%>

<!DOCTYPE html>
<html lang="en">


<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->

	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Relat�rio de Usu�rio</h4>
														<form class="form-material"
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="get" id="formUser">

															<input type="hidden" name="acao" id="acaoRelatorioImprimirTipo"
																value="imprimirRelatorioUser" />

															<div class="form-row align-items-center">
																<div class="col-auto">
																	<label class="sr-only" for="inlineFormInput">Data
																		Inicial</label> <input value="${dataInicial}" type="text"
																		class="form-control mb-2" id="dataInicial"
																		name="dataInicial" placeholder="Data Inicial">
																</div>
																<div class="col-auto">
																	<label class="sr-only" for="dataFinal">Data
																		Final</label>
																	<div class="input-group mb-2">
																		<input value="${dataFinal}" type="text"
																			class="form-control" id="dataFinal" name="dataFinal"
																			placeholder="Data Final">
																	</div>
																</div>
																<div class="col-auto">
																	<button type="button" onclick="imprimirHtml();" class="btn btn-primary mb-2">Imprimir
																		Relat�rio</button>
																	<button type="button" onclick="imprimirPdf();" class="btn btn-primary mb-2">Imprimir
																		PDF</button>
																	<button type="button" onclick="imprimirExcel();" class="btn btn-primary mb-2">Imprimir
																		Excel</button>
																</div>
															</div>

														</form>

														<div class="card">
															<div style="height: 300px; overflow: scroll;">
																<table class="table" id="tabelaresultadosview">
																	<thead>
																		<tr>
																			<th scope="col">ID</th>
																			<th scope="col">Nome</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:forEach items='${listaUser}' var='ml'>
																			<tr>
																				<td><c:out value="${ml.id}"></c:out></td>
																				<td><c:out value="${ml.nome}"></c:out></td>
																				<td>
																					<c:forEach items="${ml.telefones}" var="fone">
																						<td><c:out value="${fone.numero}"></c:out></td>
																					</c:forEach>
																				</td>
																			</tr>
																		</c:forEach>
																	</tbody>
																</table>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>

									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="javascripfile.jsp"></jsp:include>

	<script type="text/javascript">
	
		function imprimirHtml() {
			document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioUser';
			$("#formUser").submit();
		}
		
		function imprimirPdf() {
			document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioPDF';
			
			$("#formUser").submit();
			
			return false
		}
		
		function imprimirExcel() {
			document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioExcel';
			
			$("#formUser").submit();
			
			return false
		}
	
		$(function() {

			$("#dataInicial")
					.datepicker(
							{
								dateFormat : 'dd/mm/yy',
								dayNames : [ 'Domingo', 'Segunda', 'Ter�a',
										'Quarta', 'Quinta', 'Sexta', 'S�bado' ],
								dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S',
										'S', 'D' ],
								dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'S�b', 'Dom' ],
								monthNames : [ 'Janeiro', 'Fevereiro', 'Mar�o',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ],
								monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
										'Mai', 'Jun', 'Jul', 'Ago', 'Set',
										'Out', 'Nov', 'Dez' ],
								nextText : 'Pr�ximo',
								prevText : 'Anterior'
							});
		});

		$(function() {

			$("#dataFinal")
					.datepicker(
							{
								dateFormat : 'dd/mm/yy',
								dayNames : [ 'Domingo', 'Segunda', 'Ter�a',
										'Quarta', 'Quinta', 'Sexta', 'S�bado' ],
								dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S',
										'S', 'D' ],
								dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'S�b', 'Dom' ],
								monthNames : [ 'Janeiro', 'Fevereiro', 'Mar�o',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ],
								monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
										'Mai', 'Jun', 'Jul', 'Ago', 'Set',
										'Out', 'Nov', 'Dez' ],
								nextText : 'Pr�ximo',
								prevText : 'Anterior'
							});
		});
	</script>
</body>

</html>
