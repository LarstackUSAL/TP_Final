
--drop table materia_prima;
create table materia_prima(
	
	--id serial primary key,
	id int IDENTITY(1,1) PRIMARY KEY,
	codigo varchar(50) not null,
	descripcion varchar(80) not null,
	cantidad int not null default 0
);

--drop table producto;
create table producto(
	
	--id serial primary key,
	id int IDENTITY(1,1) PRIMARY KEY,
	codigo varchar(50) not null,
	descripcion varchar(80) not null
);

--drop table persona;
create table persona(
	
	--id serial primary key,
	id int IDENTITY(1,1) PRIMARY KEY,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	legajo varchar(20) not null
);

--drop table orden_trabajo;
create table orden_trabajo(

	--id serial primary key,
	id int IDENTITY(1,1) PRIMARY KEY,
	numero varchar(10) not null,
	producto_id int not null,
	descripcion varchar(50) null,
	cantidad_requerida int not null default 1,
	es_urgente bit not null default 0,
	fecha_estimada_finalizacion date null,
	fecha_finalizacion datetime null,
	fecha_alta datetime not null default getdate(),
	usuario_alta_id int not null,
	
	constraint fk_orden_trabajo_producto foreign key (producto_id) references producto(id)
);

--drop table orden_compra_materia_prima;
create table orden_compra_materia_prima(

	--id serial primary key,
	id int IDENTITY(1,1) PRIMARY KEY,
	materia_prima_id int not null,
	cantidad int not null default 1,
	fecha_alta datetime not null default getdate(),
	es_exterior bit not null default 0,
	deposito_1 varchar(200) null,
	deposito_2 varchar(200) null,
	deposito_3 varchar(200) null,
	
	constraint fk_orden_compra_materia_prima foreign key (materia_prima_id) references materia_prima(id)
);

--drop table orden_trabajo_instruccion_persona;
create table orden_trabajo_instruccion_persona(

	--id serial primary key,
	id int IDENTITY(1,1) PRIMARY KEY,
	orden_trabajo_id int not null,
	instruccion_id int not null, --id del archivo de instrucciones
	persona_id int not null,
	es_finalizado bit not null default 0,
	fecha_inicio datetime null,
	fecha_finalizacion datetime null,
	fecha_asignacion datetime not null default getdate(),
	usuario_asignacion_id int not null,
	
	constraint fk_instruccion_persona foreign key (persona_id) references persona(id)	
); 

insert into materia_prima(codigo, descripcion, cantidad) values('0001PH', 'Peroxido de hidrogeno', 2);
insert into materia_prima(codigo, descripcion, cantidad) values('0002AP', 'Acido peracetico', 3);
insert into producto(codigo, descripcion) values('000P1', 'Letal Peroxo');

insert into materia_prima(codigo, descripcion, cantidad) values('0003HS', 'Hipoclorito sodico', 8);
insert into materia_prima(codigo, descripcion, cantidad) values('0004EC', 'Estabilizador de cloro', 1);
insert into producto(codigo, descripcion) values('000P2', 'Actival Clorado');

insert into materia_prima(codigo, descripcion, cantidad) values('0005AC', 'Amonios cuaternarios', 12);
insert into materia_prima(codigo, descripcion, cantidad) values('0006TC', 'Tensioactivo cationico', 0);
insert into producto(codigo, descripcion) values('000P3', 'Fungicida Plus');	

insert into producto(codigo, descripcion) values('000P4', 'Limpiador bactericida');

insert into persona(nombre, apellido, legajo) values('Jimmy', 'Wales', 'WP001INF');
insert into persona(nombre, apellido, legajo) values('George', 'Boole', 'BO002ALG');
insert into persona(nombre, apellido, legajo) values('Tim', 'Berners-Lee', 'HT004WWW');
insert into persona(nombre, apellido, legajo) values('Ada', 'Lovelace', 'PR003PRO');
insert into persona(nombre, apellido, legajo) values('John', 'Von Neumann', 'MQ001INF');

