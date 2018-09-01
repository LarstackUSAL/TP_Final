
--drop table materia_prima;
create table materia_prima(
	
	id serial primary key,
	codigo varchar(50) not null,
	descripcion varchar(80) not null,
	cantidad int not null default 0
);

--drop table producto;
create table producto(
	
	id serial primary key,
	codigo varchar(50) not null,
	descripcion varchar(80) not null
);

--drop table operario;
create table operario(
	
	id serial primary key,
	nombre varchar(50) not null,
	apellido varchar(50) not null,
	legajo varchar(20) not null
);

--drop table orden_trabajo;
create table orden_trabajo(

	id serial primary key,
	numero varchar(10) not null,
	producto_id int not null,
	descripcion varchar(50) null,
	cantidad_requerida int not null default 1,
	es_urgente bool not null default false,
	fecha_alta timestamp not null default now(),
	fecha_estimada_finalizacion date null,
	fecha_finalizacion timestamp null,
	
	constraint fk_orden_trabajo_producto foreign key (producto_id) references producto(id)
);

--drop table orden_trabajo_instruccion_operario;
create table orden_trabajo_instruccion_operario(

	id serial primary key,
	orden_trabajo_id int not null,
	instruccion_id int not null, --id del archivo de instrucciones
	operario_id int not null,
	es_finalizado bool not null default false,
	fecha_inicio timestamp null,
	fecha_finalizacion timestamp null,
	
	constraint fk_instruccion_operario foreign key (operario_id) references operario(id)	
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

insert into operario(nombre, apellido, legajo) values('Jimmy', 'Wales', 'WP001INF');
insert into operario(nombre, apellido, legajo) values('George', 'Boole', 'BO002ALG');
insert into operario(nombre, apellido, legajo) values('Tim', 'Berners-Lee', 'HT004WWW');
insert into operario(nombre, apellido, legajo) values('Ada', 'Lovelace', 'PR003PRO');
insert into operario(nombre, apellido, legajo) values('John', 'Von Neumann', 'MQ001INF');

