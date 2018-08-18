<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Menu - TP Final</title>
		
		<script type="text/javascript">
			function validarMenu() {
				
				var menuList = document.getElementById("menuForm").elements.namedItem("menu");
				var ok=false;
				for (var i = 0; i < menuList.length; i++) {
					
					var menuIt = menuList[i];
					if(menuIt.checked){
						
						ok = true;
						break;
					}
				}
				if(ok == false){
					document.getElementById('menuError').innerHTML = 'Debe seleccionar una opción.';
					
				}
				else{
					document.getElementById('menuError').innerHTML = '';
					document.getElementById("menuForm").submit();
				}
			}
		</script>
	</head>
	<body>
		<h2>Elegir una opción</h2>

	<form id="menuForm" action="./Menu" method="get">
		
		<div style="display:block">
				<input type="radio" value="ConsultaStock" name="menu" />
				<label>Consulta de stock</label>
		</div>
		<div style="display:block">
				<input type="radio" value="ConsultaOT" name="menu" />
				<label>Consulta de OT</label>
		</div>
		<div style="display:block">
				<input type="radio" value="ActualizacionInstrucciones" name="menu" />
				<label>Actualizacion de instrucciones</label>
		</div>
		<div style="display:block">
				<input type="radio" value="RegistroCompras" name="menu" />
				<label>Registro de compras de materias primas</label>
		</div>
		<br /><br />	
		<p id="menuError" style="color:red;font-weight:bold;font-style:italic;"></p>
		<br /><br />
		<input value="Ingresar" type="button" onClick="validarMenu();">
		
	</form>
		
	</body>

</html>