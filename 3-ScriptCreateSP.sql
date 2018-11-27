USE [db_tp_final]
GO

CREATE PROCEDURE sp_consulta_stock
	
	@codigoMateriaPrima varchar(max),
	@stock int output

AS
	
	set @stock =
		
		(select cantidad
		from materia_prima
		where upper(codigo) = upper(@codigoMateriaPrima));
	
		PRINT 'Stock: ' + cast(@stock as varchar)

		return @stock;
GO
