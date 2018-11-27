USE [db_tp_final]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE MATERIA_PRIMA 
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	CODIGO VARCHAR(50) NOT NULL,
	DESCRIPCION VARCHAR(80) NOT NULL,
	CANTIDAD INT NOT NULL DEFAULT 0
)
GO

CREATE TABLE PRODUCTO 
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	CODIGO VARCHAR(50) NOT NULL,
	DESCRIPCION VARCHAR(80) NOT NULL
)
GO

CREATE TABLE PERSONA 
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	NOMBRE VARCHAR(50) NOT NULL,
	APELLIDO VARCHAR(50) NOT NULL,
	LEGAJO VARCHAR(20) NOT NULL
)
GO

CREATE TABLE ORDEN_TRABAJO 
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	NUMERO VARCHAR(10) NOT NULL,
	PRODUCTO_ID INT NOT NULL,
	DESCRIPCION VARCHAR(50) NULL,
	CANTIDAD_REQUERIDA INT NOT NULL DEFAULT 1,
	ES_URGENTE BIT NOT NULL DEFAULT 0,
	FECHA_ESTIMADA_FINALIZACION DATE NULL,
	FECHA_FINALIZACION DATETIME NULL,
	FECHA_ALTA DATETIME NOT NULL DEFAULT GETDATE(),
	USUARIO_ALTA_ID INT NOT NULL,
	
	CONSTRAINT FK_ORDEN_TRABAJO_PRODUCTO FOREIGN KEY (PRODUCTO_ID) REFERENCES PRODUCTO(ID)
)
GO

CREATE TABLE ORDEN_COMPRA_MATERIA_PRIMA 
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	MATERIA_PRIMA_ID INT NOT NULL,
	CANTIDAD INT NOT NULL DEFAULT 1,
	FECHA_ALTA DATETIME NOT NULL DEFAULT GETDATE(),
	ES_EXTERIOR BIT NOT NULL DEFAULT 0,
	DEPOSITO_1 VARCHAR(200) NULL,
	DEPOSITO_2 VARCHAR(200) NULL,
	DEPOSITO_3 VARCHAR(200) NULL,
	
	CONSTRAINT FK_ORDEN_COMPRA_MATERIA_PRIMA FOREIGN KEY (MATERIA_PRIMA_ID) REFERENCES MATERIA_PRIMA(ID)
)
GO

CREATE TABLE ORDEN_TRABAJO_INSTRUCCION_PERSONA 
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	ORDEN_TRABAJO_ID INT NOT NULL,
	INSTRUCCION_ID INT NOT NULL, --ID DEL ARCHIVO DE INSTRUCCIONES
	PERSONA_ID INT NOT NULL,
	ES_FINALIZADO BIT NOT NULL DEFAULT 0,
	FECHA_INICIO DATETIME NULL,
	FECHA_FINALIZACION DATETIME NULL,
	FECHA_ASIGNACION DATETIME NOT NULL DEFAULT GETDATE(),
	USUARIO_ASIGNACION_ID INT NOT NULL,
	
	CONSTRAINT FK_INSTRUCCION_PERSONA FOREIGN KEY (PERSONA_ID) REFERENCES PERSONA(ID)	
)
GO

CREATE TABLE SISTEMA_SEGURIDAD_USUARIO
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	USUARIO VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(50) NOT NULL
)
GO

CREATE TABLE SISTEMA_SEGURIDAD_ROL 
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	CODIGO VARCHAR(50) NOT NULL,
	DESCRIPCION VARCHAR(50) NOT NULL
)
GO

CREATE TABLE SISTEMA_SEGURIDAD_USUARIO_ROL
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	USUARIO_ID INT NOT NULL,
	ROL_ID INT NOT NULL,
	
	CONSTRAINT FK_SEGURIDAD_USUARIO FOREIGN KEY (USUARIO_ID) REFERENCES SISTEMA_SEGURIDAD_USUARIO(ID),
	CONSTRAINT FK_SEGURIDAD_USUARIO_ROL FOREIGN KEY (ROL_ID) REFERENCES SISTEMA_SEGURIDAD_ROL(ID)
)
GO

CREATE TABLE SISTEMA_SEGURIDAD_USUARIO_MODELO 
(
	ID INT IDENTITY(1,1) PRIMARY KEY,
	USUARIO_ID INT NOT NULL,
	PERSONA_ID INT NOT NULL,
	
	CONSTRAINT FK_SEGURIDAD_MODELO_USUARIO FOREIGN KEY (USUARIO_ID) REFERENCES SISTEMA_SEGURIDAD_USUARIO(ID),
	CONSTRAINT FK_SEGURIDAD_MODELO_PERSONA FOREIGN KEY (PERSONA_ID) REFERENCES PERSONA(ID)
)
GO