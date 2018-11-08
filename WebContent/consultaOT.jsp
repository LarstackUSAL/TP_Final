<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dto.OrdenesTrabajos" %>
<%@ page import="model.dao.OrdenesTrabajosDao" %>
<% 
	OrdenesTrabajosDao dao = new OrdenesTrabajosDao();
	ArrayList<OrdenesTrabajos> ordenesList = dao.getOrdenesTrabajos(true);
%>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta de &Oacuterdenes de Trabajo Pendientes</title>
</head>
<body>
	<table>
		<tr>
			<td>Supervisor</td>
			<td>Orden de trabajo</td>
			<td>Fecha Alta</td>
			<td>C&oacutedigo Producto</td>
			<td>Cantidad requerida</td>
			<td>Fecha estimada finalizaci&oacuten</td>
			<td>Fecha finalizaci&oacuten</td>
			<td>Descripci&oacuten</td>
			<td>Es urgunte</td>
		</tr>
		<% for(OrdenesTrabajos ot : ordenesList){ %>
			<tr>
				<td><%= ot.getUsuarioCreacion() %></td>
				<td><%= ot.getNumero() %></td>
				<td><%= ot.getFechaAlta() %></td>							AJUSTAR FORMATO FECHAS
				<td><%= ot.getProducto().getCodigo() %></td>
				<td><%= ot.getCantidadRequerida() %></td>
				<td><%= ot.getFechaEstimadaFinalizacion() %></td>
				<td><%= ot.getFechaFinalizacion() %></td>
				<td><%= ot.getDescripcion() %></td>
				<td><%= ot.isEsUrgente() %></td>				
			</tr>
		<% }%>
	</table>
</body>
</html>
