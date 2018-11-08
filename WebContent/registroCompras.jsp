<%@page import="model.dao.MateriasPrimasDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.dto.MateriasPrimas"%>
<%
	MateriasPrimasDao materiasPrimasDao = new MateriasPrimasDao();
	ArrayList<MateriasPrimas> materiasPrimas = materiasPrimasDao.findAll();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registro de Materia Prima</title>

<script type="text/javascript">
	function submitForm() {
		
		if((document.getElementById('cantidad').value == null || document.getElementById('cantidad').value == '')
				&& (document.getElementById('deposito1').value == null || document.getElementById('deposito1').value == '')
				&& (document.getElementById('deposito2').value == null || document.getElementById('deposito2').value == '')
				&& (document.getElementById('deposito3').value == null || document.getElementById('deposito3').value == '')
				){
			
			document.getElementById('codigoError').innerHTML = 'Todos los datos son obligatorio';
	
		} else {
			
			document.getElementById('registroMateriaPrimaForm').submit();	
		}
					
	}
</script>
</head>
<body>


	<h1>Registro de Materia Prima</h1>
	<form id="registroMateriaPrimaForm" name="registroMateriaPrimaForm"
		action="./RegistroMateriaPrima">
		<label>Seleccionar Materia Prima:</label> <select
			id="selectMateriaPrima" name="selectMateriaPrima">
			<%
				for (MateriasPrimas p : materiasPrimas) {
			%>
			<option value="<%=p.getId()%>"><%=p.getDescripcion()%></option>
			<%
				}
			%>
			<option>
		</select>
		
		<label>Indicar cantidad</label>
		<input type="text" id="cantidad" name="cantidad" />
		
		<label>Exterior</label>
		<input type="checkbox" name="exterior" id="exterior">
		
		<label>Inserte 3 posibles dep&oacutesitos:</label>
		<input type="text" id="deposito1" name="deposito1" />
		<input type="text" id="deposito2" name="deposito2" />
		<input type="text" id="deposito3" name="deposito3" />
		
		<input type="button" value="Registrar" onclick="submitForm();"/>
		
		<p id="codigoError"></p>
		
	</form>
</body>
</html>