

CREATE TABLE USUARIO (
    usuario VARCHAR(15) primary key NOT NULL,
    clave VARCHAR (15) CHECK (clave regexp '[A-Z]' AND clave regexp '[%&$/?¡@]') NOT NULL
);

CREATE TABLE CLIENTES (
    usuario VARCHAR (15),
    PRIMARY KEY (usuario),
    FOREIGN KEY (usuario) REFERENCES USUARIO (usuario),
    rut BIGINT NOT NULL,
    nombre VARCHAR (15) NOT NULL,
    url VARCHAR (40) CHECK (url LIKE '%@%.com%')
);

CREATE TABLE POSTULANTES (
     usuario VARCHAR (15),
     PRIMARY KEY (usuario),
     FOREIGN KEY (usuario) REFERENCES USUARIO (usuario),
     cedula BIGINT  NOT NULL UNIQUE,
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
    cliente VARCHAR (15) NOT NULL,
    FOREIGN KEY (cliente) REFERENCES CLIENTES (usuario),
    cantidadVacantes INT NOT NULL,
    fechaPublicacion DATE NOT NULL, 
    fechaCierra DATE NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (cliente) REFERENCES CLIENTES(usuario)
);

CREATE TABLE POSTULACIONES(
    idOferta INT AUTO_INCREMENT,
    usuario VARCHAR (15),
    PRIMARY KEY(idOferta, usuario),
    FOREIGN KEY (idOferta) REFERENCES OFERTAS(id),
    FOREIGN KEY (usuario) REFERENCES POSTULANTES(usuario)
);


CREATE TABLE AREAS (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR (15),
    asignada boolean
);