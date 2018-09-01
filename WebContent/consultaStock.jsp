<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta de stock</title>
<script type="text/javascript">
	function validarCodigo() {
		
		if(document.getElementById('codigoMateriaPrima').value == null || document.getElementById('codigoMateriaPrima').value == ''){
			
			document.getElementById('codigoError').innerHTML = 'Controlar el c&oacutedigo ingresado';
	
		} else {
			
			document.getElementById('consultaStockMenu').submit();	
		}
					
	}
</script>
</head>
<body>
	<h1>Consulta de Stock de Materias Primas</h1>
	<form id="consultaStockMenu" name="consultaStockMenu" action="./ConsultaStock">
		<label>Ingresar código de materia prima</label>
		<input type="text" id="codigoMateriaPrima" name="codigoMateriaPrima" />
		<p id="codigoError" style="color:red; font-weight:bold;font-style:italic;"></p>
		<input type="button" value="Buscar" onclick="validarCodigo();" />
	</form>
	
	<% if(request.getAttribute("stock")!=null) { %>
		<p id="stock">Cantidad en stock: <%=request.getAttribute("stock") %></p>
	<%} %>
</body>
</html>