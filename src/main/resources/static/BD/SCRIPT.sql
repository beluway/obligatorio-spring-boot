CREATE DATABASE obligatorioSpring2025;

USE obligatorioSpring2025;

CREATE TABLE USUARIO (
	usuario VARCHAR(15) primary key NOT NULL,
    clave VARCHAR (15) CHECK (clave regexp '[A-Z]' AND clave regexp '[%&$/?¡@]') NOT NULL
);

CREATE TABLE CLIENTES (
	usuario VARCHAR (15),
    PRIMARY KEY (usuario),
	FOREIGN KEY (usuario) REFERENCES USUARIO (usuario),
    rut BIGINT (12) NOT NULL,
    nombre VARCHAR (15) NOT NULL,
    url VARCHAR (40) CHECK (url LIKE '%@%.com%')
);

CREATE TABLE POSTULANTES (
	 usuario VARCHAR (15),
     PRIMARY KEY (usuario),
	 FOREIGN KEY (usuario) REFERENCES USUARIO (usuario),
     cedula BIGINT (8) NOT NULL,
     primerNombre VARCHAR(15) NOT NULL,
     segundoNombre VARCHAR(15),
     primerApellido VARCHAR (15) NOT NULL,
     segundoApellido VARCHAR(15) NOT NULL,
     fechaNacimiento DATE NOT NULL,
     departamento VARCHAR (20) NOT NULL    
);

CREATE TABLE OFERTAS (
	id INT auto_increment,
    titulo VARCHAR (50) NOT NULL,
    descripcion VARCHAR (2000) NOT NULL,
    cliente VARCHAR (15),
    FOREIGN KEY (cliente) REFERENCES CLIENTE (cliente),
    cantidadVacantes INT NOT NULL,
    fechaPublicacion DATE NOT NULL,
    fechaCierra DATE NOT NULL,
    PRIMARY KEY (id, cliente)
);

CREATE PROCEDURE POSTULACIONES(
	FOREIGN KEY (id) REFERENECES OFERTAS (id),
    FOREIGN KEY 
);
