<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.dto.Productos"%>
<%@ page import="model.dto.Pasos"%>
<%@ page import="model.dto.MateriasPrimasCantidad"%>
<%@ page import="model.dto.MateriasPrimas"%>
<%@ page import="model.dao.ProductosDao"%>
<% 
	ProductosDao dao = new ProductosDao();
	ArrayList<Productos> productosList = dao.loadProductos();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Actualizaci&oacuten Instrucciones</title>

<script type="text/javascript">

function submitForm(buscar) {

	document.getElementById('buscar').value = buscar;
	document.getElementById('consultaProducto').submit();			
}

function validateNumber(numero){

	if(numero.value.toString().length > 5){
		numero.value = numero.value.substr(0, 5);
	}
}
</script>

<style type="text/css">
	
	td{
		border: 1px solid black;
	}
	
	.title{
		
		background-color: #E91E63;
		color: #FFFFFF;
		font-weight: bold;
	}
	
	 .editable{
	 	
	 	background-color: #FFEB3B;
		font-weight: italic;
	 }
</style>
</head>
<body>
	<h1>Actualizaci&oacuten Instrucciones</h1>
	<form id="consultaProducto" name="consultaProducto"
		action="./ActualizacionInstrucciones">
		<label>Seleccionar producto:</label> <select id="selectProducto"
			name="consultaProducto">
			<% for(Productos p : productosList){ %>
			<option value="<%= p.getCodigo() %>"><%= p.getDescripcion() %></option>
			<%} %>
			<option>
		</select> <input type="button" value="Buscar" onclick="submitForm(true);" />
	
	<input type="hidden" id="buscar" name="buscar" value="true" />

	<% if(request.getAttribute("mensajeActualizacion")!=null) { %>
		<p><%= request.getAttribute("mensajeActualizacion")%></p>
	<% } %>
	
	<% if(request.getSession().getAttribute("pasos")!=null) { 
		
		ArrayList<Pasos> pasos = (ArrayList<Pasos>)request.getSession().getAttribute("pasos");
	%>
	<br /><br />
	
	<table style="border: 1px solid black;">
		<tr>
			<td class="title">Id</td>
			<td class="title">Producto</td>
			<td class="title">Descripci&oacuten</td>
			<td class="title" colspan="4">Materias Primas</td>
		</tr>
	<%
		for(Pasos p : pasos){
	%>
	<tr>
		<td><%= p.getId() %></td>
		<td><%= p.getProducto().getDescripcion() + "("+ p.getProducto().getCodigo() +")" %></td>
		<td contenteditable class="editable"><input maxlength="40" name="descripcion_<%= p.getId() %>" value="<%= p.getDescripcion() %>" required /></td>
		<% for(MateriasPrimasCantidad mp : p.getMateriasPrimas()){ %>
			
				<td><%= mp.getMateriaPrima().getDescripcion() %></td>
				<td contenteditable class="editable" ><input type="number" onkeyup="validateNumber(this);" name="<%= p.getId() %>_<%= mp.getMateriaPrima().getCodigo() %>" value="<%= mp.getCantidad() %>" required /></td>
			
		<%}%>
	</tr>
		<%}%>
	
	</table>
	<input type="button" value="Actualizar" onclick="submitForm(false);" />
		
	<% request.getSession().setAttribute("pasos",pasos); } %>
	
	</form>
</body>
</html>