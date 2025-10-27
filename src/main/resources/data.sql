CREATE PROCEDURE IF NOT EXISTS van_los_datos()
BEGIN
    IF NOT EXISTS(

        SELECT *
        FROM logs 
   )


   THEN 

            INSERT INTO areas (id,asignada,nombre)
            VALUES(1,1,'Educación'),
                    (2,1,'Tecnología'),
                    (3,1,'Limpieza');


    /*admin  admin  TIPO CONSULTOR*/ 
    /* consu1 TTYy444y&&&#!! TIPO CONSULTOR*/
    /* consu2 TTYy444y&&&#!! TIPO CONSULTOR*/
    /* cli1  mEH&&YY66%#eF  TIPO CLIENTE*/
    /* cli2  mEH&&YY66%#eF  TIPO CLIENTE*/
    /* cli3  mEH&&YY66%#eF  TIPO CLIENTE*/
    /* pos1  76&&opPolGGgr$4  TIPO POSTULANTE*/
    /* pos2  76&&opPolGGgr$4  TIPO POSTULANTE*/
    /* pos3  76&&opPolGGgr$4  TIPO POSTULANTE*/



        INSERT INTO usuarios (usuario,activo,clave)

         VALUES('consu1',1,'$2a$12$WjlnNjfnJAXDgYiRLWsHl.AgJMXpIjrhSj7tyUkX82P9PfX2lVYOW'),
                ('consu2',1,'$2a$12$WjlnNjfnJAXDgYiRLWsHl.AgJMXpIjrhSj7tyUkX82P9PfX2lVYOW'),
                ('admin',1,'$2a$12$b3mCSR2Ba3ZP8yvAZqWfUuvtivbkAMBEmVUhhfrWNUxZEvam6qUYq'), 
                ('cli1',1,'$2a$12$euwX2gerQniRIDs6FOese.YtV75MVR3UO643.vAuVU/2I21tvS.Wy'), 
                ('cli2',1,'$2a$12$euwX2gerQniRIDs6FOese.YtV75MVR3UO643.vAuVU/2I21tvS.Wy'), 
                ('cli3',1,'$2a$12$euwX2gerQniRIDs6FOese.YtV75MVR3UO643.vAuVU/2I21tvS.Wy'), 
                ('pos1',1,'$2a$12$O4xZXO7WPApiRXnDXioUQuQme9dyILhmGjvTuIV7Nyn0KbNTVSY4m'),
                ('pos2',1,'$2a$12$O4xZXO7WPApiRXnDXioUQuQme9dyILhmGjvTuIV7Nyn0KbNTVSY4m'),
                ('pos3',1,'$2a$12$O4xZXO7WPApiRXnDXioUQuQme9dyILhmGjvTuIV7Nyn0KbNTVSY4m');
                  
        


        INSERT INTO clientes (nombre,rut,url,usuario)
            VALUES ('Bios Economía',234567983910,'www.biosedco.com','cli1'),
            ('Bios Sistemas',234567983911,'www.bios.com','cli2'),
            ('Globant',234567983367,'www.globant.com','cli3');


            INSERT INTO consultores(usuario)
            VALUES ('consu1'),('consu2'),('admin');


            INSERT INTO postulantes(cantidad_postulaciones,cedula,departamento,fechanacimiento,primer_apellido,primer_nombre,segundo_apellido,segundo_nombre,usuario)
            VALUES (1,59340394,'Montevideo','1980-01-01','Carpa','Armando','Hashimoto','Ryusei','pos1'),
            (1,59385594,'Soriano','1980-03-01','Ramirez','Gonzalo','Pérez','Fabricio','pos2'),
            (1,59343510,'Rocha','1980-03-01','Recoba','Ana','Suárez','María','pos3');

            INSERT INTO ofertas (id,cantidad_vacantes,descripcion,fecha_cierre,fecha_publicacion,titulo,area,cliente)
            VALUES(1,10,'Profesor de Comercio Exterior con experiencia de al menos 1 año','2025-11-18',NOW(),'Porfesor Comercio Exterio',1,'cli1'),
            (2,2,'Se busca personal de limpieza para el instituto.','2025-11-30',NOW(),'Encargado de limpieza',3,'cli1'),
            (3,2,'Se solicita profesor de Contabilidad.','2025-11-17',NOW(),'Profesor Contabilidad',1,'cli1'),
            (4,1,'Buscamos a un instructor para la materia de Genexus.','2026-11-10',NOW(),'Profesor Genexus',2,'cli2'),
            (5,3,'Buscamos algún experto en IT para realizar el mantenimiento de los servidores de la institución.','2025-11-23',NOW(),'Mantenimiento de Servidores',2,'cli2'),
            (6,3,'Buscamos un profesor auxiliar con conocimientos sólidos en base de datos.','2025-12-23',NOW(),'Profesor auxiliar de Base de datos',2,'cli2'),
            (7,1,'Se abre llamado para Ingenieros de Datos.','2025-11-24',NOW(),'Ingeniero de datos',2,'cli3'),
            (8,1,'Se busca Analista de Sistemas con experiencia previa en las tecnologías actuales.','2025-11-24',NOW(),'Analista de Sistemas',2,'cli3'),
            (9,5,'Se buscan desarrolladores Junior para bootcamp.','2026-02-24',NOW(),'Desarrollador Junior',2,'cli3');

            INSERT INTO postulaciones(fecha_postulacion,oferta,postulante)
            VALUES(NOW(),1,'pos1'),
                (NOW(),4,'pos2'),
                (NOW(),5,'pos3');


            INSERT INTO roles (nombre_rol)
            VALUES ('cliente'),
            ('consultor'),
            ('postulante');

        
            INSERT INTO usuarios_roles(usuario_nombre_usuario, rol_nombre_rol)
                VALUES
                    ('cli1', 'cliente'),
                    ('cli2', 'cliente'),
                    ('cli3', 'cliente'),
                    ('consu1', 'consultor'), 
                    ('consu2', 'consultor'),
                    ('admin','consultor'),       
                    ('pos1', 'postulante'),
                    ('pos2', 'postulante'),
                    ('pos3', 'postulante');

                INSERT INTO logs(fecha_hora, mensaje)
                VALUES (NOW(), 'Datos iniciales cargados.');
    END IF;
END^;

CALL van_los_datos()^;