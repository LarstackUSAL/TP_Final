--drop table sistema_seguridad_usuario;
create table sistema_seguridad_usuario(
	
	id serial primary key,
	usuario varchar(50) not null,
	password varchar(50) not null
);

--drop table sistema_seguridad_rol;
create table sistema_seguridad_rol(
	
	id serial primary key,
	codigo varchar(50) not null,
	descripcion varchar(50) not null
);

--drop table sistema_seguridad_usuario_rol;
create table sistema_seguridad_usuario_rol(
	
	id serial primary key,
	usuario_id int4 not null,
	rol_id int4 not null,
	
	constraint fk_seguridad_usuario foreign key (usuario_id) references sistema_seguridad_usuario(id),
	constraint fk_seguridad_usuario_rol foreign key (rol_id) references sistema_seguridad_rol(id)
);

--drop table sistema_seguridad_usuario_modelo;
create table sistema_seguridad_usuario_modelo(
	
	id serial primary key,
	usuario_id int4 not null,
	persona_id int4 not null,
	
	constraint fk_seguridad_modelo_usuario foreign key (usuario_id) references sistema_seguridad_usuario(id),
	constraint fk_seguridad_modelo_persona foreign key (persona_id) references persona(id)
);

insert into sistema_seguridad_usuario(usuario, password) values('supervisor1', 'tpfinal');
insert into sistema_seguridad_usuario(usuario, password) values('supervisor2', 'tpfinal');
insert into sistema_seguridad_usuario(usuario, password) values('timlee', 'tpfinal');
insert into sistema_seguridad_usuario(usuario, password) values('adalovelace', 'tpfinal');
insert into sistema_seguridad_usuario(usuario, password) values('vonneumann', 'tpfinal');

insert into sistema_seguridad_rol(codigo, descripcion) values('ROL_SUPERVISOR', 'Supervisor');
insert into sistema_seguridad_rol(codigo, descripcion) values('ROL_OPERARIO', 'Operario');

insert into sistema_seguridad_usuario_rol(usuario_id, rol_id) values(1, 1);
insert into sistema_seguridad_usuario_rol(usuario_id, rol_id) values(2, 1);
insert into sistema_seguridad_usuario_rol(usuario_id, rol_id) values(3, 2);
insert into sistema_seguridad_usuario_rol(usuario_id, rol_id) values(4, 2);

insert into sistema_seguridad_usuario_modelo(usuario_id, persona_id) values(1, 1);
insert into sistema_seguridad_usuario_modelo(usuario_id, persona_id) values(2, 2);
insert into sistema_seguridad_usuario_modelo(usuario_id, persona_id) values(3, 3);
insert into sistema_seguridad_usuario_modelo(usuario_id, persona_id) values(4, 4);
insert into sistema_seguridad_usuario_modelo(usuario_id, persona_id) values(5, 5);
id |usuario     |password |
---|------------|---------|
1  |supervisor1 |tpfinal  |
2  |supervisor2 |tpfinal  |
3  |timlee      |tpfinal  |
4  |adalovelace |tpfinal  |
