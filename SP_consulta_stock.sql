
CREATE PROCEDURE [dbo].[sp_consulta_stock]
	
	@codigoMateriaPrima varchar(max),
	@stock int output
AS
	
	set @stock =
		
		(select cantidad
		from materia_prima
		where codigo = @codigoMateriaPrima);
	
		PRINT 'Stock: ' + cast(@stock as varchar)

		return @stock;

--select sp_consulta_stock('0002AP');
--drop function sp_consulta_stock(varchar);
CREATE OR REPLACE FUNCTION  sp_consulta_stock(codigoMateriaPrima varchar(200)) RETURNS INTEGER
AS $$
declare stock integer; 
   BEGIN

	   stock := (select cantidad
		from materia_prima
		where codigo = codigoMateriaPrima);

	return stock;
	
   END;
$$ LANGUAGE plpgsql;
