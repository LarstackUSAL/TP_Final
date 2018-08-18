<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta de stock</title>
</head>
<body>
	<h1>Consulta de Stock de Materias Primas</h1>
	
	<form name="consultaStockMenu" action="ConsultaStock">
		<label>Ingresar código de materia prima</label>
		<input type="text" name="codigoMateriaPrima" />
		<input type="submit" value="Buscar" />
	</form>
</body>
</html>