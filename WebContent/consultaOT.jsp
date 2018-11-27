<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.dto.OrdenesTrabajos" %>
<%@ page import="model.dao.OrdenesTrabajosDao" %>
<%@ page import="utils.Utilities" %>
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
			<th>Supervisor</th>
			<th>Orden de trabajo</th>
			<th>Fecha Alta</th>
			<th>C&oacutedigo Producto</th>
			<th>Cantidad requerida</th>
			<th>Fecha estimada finalizaci&oacuten</th>
			<th>Fecha finalizaci&oacuten</th>
			<th>Descripci&oacuten</th>
			<th>Es urgente</th>
		</tr>
		<% for(OrdenesTrabajos ot : ordenesList){ %>
			<tr>
				<td><%= ot.getUsuarioCreacion() %></td>
				<td><%= ot.getNumero() %></td>
				<td><%= Utilities.calendarToString(ot.getFechaAlta(), "dd-MM-yyyy") %></td>
				<td><%= ot.getProducto().getCodigo() %></td>
				<td><%= ot.getCantidadRequerida() %></td>
				<td><%= ot.getFechaEstimadaFinalizacion() != null ? Utilities.calendarToString(ot.getFechaEstimadaFinalizacion(), "dd-MM-yyyy") : "" %></td>
				<td><%= ot.getFechaFinalizacion() != null ? Utilities.calendarToString(ot.getFechaFinalizacion(), "dd-MM-yyyy") : "" %></td>
				<td><%= ot.getDescripcion() %></td>
				<td><%= ot.isEsUrgente() %></td>				
			</tr>
		<% }%>
	</table>
	<% if(ordenesList.isEmpty()) {%>
			<p style="font-weight:bold;color:red"> No hay &oacuterdenes de trabajo pendientes </p>
	<% } %>
</body>
</html>
