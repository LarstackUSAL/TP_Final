USE [db_tp_final]
GO

INSERT INTO MATERIA_PRIMA (codigo, descripcion, cantidad) VALUES ('0001PH', 'Peroxido de hidrogeno', 2);
INSERT INTO MATERIA_PRIMA (codigo, descripcion, cantidad) VALUES ('0002AP', 'Acido peracetico', 3);
INSERT INTO PRODUCTO (codigo, descripcion) VALUES ('000P1', 'Letal Peroxo');

INSERT INTO MATERIA_PRIMA (codigo, descripcion, cantidad) VALUES ('0003HS', 'Hipoclorito sodico', 8);
INSERT INTO MATERIA_PRIMA (codigo, descripcion, cantidad) VALUES ('0004EC', 'Estabilizador de cloro', 1);
INSERT INTO PRODUCTO (codigo, descripcion) VALUES ('000P2', 'Actival Clorado');

INSERT INTO MATERIA_PRIMA (codigo, descripcion, cantidad) VALUES ('0005AC', 'Amonios cuaternarios', 12);
INSERT INTO MATERIA_PRIMA (codigo, descripcion, cantidad) VALUES ('0006TC', 'Tensioactivo cationico', 0);
INSERT INTO PRODUCTO (codigo, descripcion) VALUES ('000P3', 'Fungicida Plus');	

INSERT INTO PRODUCTO (codigo, descripcion) VALUES ('000P4', 'Limpiador bactericida');

INSERT INTO PERSONA (nombre, apellido, legajo) VALUES ('Jimmy', 'Wales', 'WP001INF');
INSERT INTO PERSONA (nombre, apellido, legajo) VALUES ('George', 'Boole', 'BO002ALG');
INSERT INTO PERSONA (nombre, apellido, legajo) VALUES ('Tim', 'Berners-Lee', 'HT004WWW');
INSERT INTO PERSONA (nombre, apellido, legajo) VALUES ('Ada', 'Lovelace', 'PR003PRO');
INSERT INTO PERSONA (nombre, apellido, legajo) VALUES ('John', 'Von Neumann', 'MQ001INF');

INSERT INTO SISTEMA_SEGURIDAD_USUARIO (usuario, password) VALUES ('supervisor1', 'tpfinal');
INSERT INTO SISTEMA_SEGURIDAD_USUARIO (usuario, password) VALUES ('supervisor2', 'tpfinal');
INSERT INTO SISTEMA_SEGURIDAD_USUARIO (usuario, password) VALUES ('timlee', 'tpfinal');
INSERT INTO SISTEMA_SEGURIDAD_USUARIO (usuario, password) VALUES ('adalovelace', 'tpfinal');
INSERT INTO SISTEMA_SEGURIDAD_USUARIO (usuario, password) VALUES ('vonneumann', 'tpfinal');

INSERT INTO SISTEMA_SEGURIDAD_ROL (codigo, descripcion) VALUES ('ROL_SUPERVISOR', 'Supervisor');
INSERT INTO SISTEMA_SEGURIDAD_ROL (codigo, descripcion) VALUES ('ROL_OPERARIO', 'Operario');

INSERT INTO SISTEMA_SEGURIDAD_USUARIO_ROL (usuario_id, rol_id) VALUES (1, 1);
INSERT INTO SISTEMA_SEGURIDAD_USUARIO_ROL (usuario_id, rol_id) VALUES (2, 1);
INSERT INTO SISTEMA_SEGURIDAD_USUARIO_ROL (usuario_id, rol_id) VALUES (3, 2);
INSERT INTO SISTEMA_SEGURIDAD_USUARIO_ROL (usuario_id, rol_id) VALUES (4, 2);

INSERT INTO SISTEMA_SEGURIDAD_USUARIO_MODELO (usuario_id, PERSONA_id) VALUES (1, 1);
INSERT INTO SISTEMA_SEGURIDAD_USUARIO_MODELO (usuario_id, PERSONA_id) VALUES (2, 2);
INSERT INTO SISTEMA_SEGURIDAD_USUARIO_MODELO (usuario_id, PERSONA_id) VALUES (3, 3);
INSERT INTO SISTEMA_SEGURIDAD_USUARIO_MODELO (usuario_id, PERSONA_id) VALUES (4, 4);
INSERT INTO SISTEMA_SEGURIDAD_USUARIO_MODELO (usuario_id, PERSONA_id) VALUES (5, 5);
