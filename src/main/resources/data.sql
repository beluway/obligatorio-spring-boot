CREATE PROCEDURE IF NOT EXISTS van_los_datos()
BEGIN
    IF NOT EXISTS(

        SELECT *
        FROM logs 
   )


   THEN 

            INSERT INTO areas (id,asignada,nombre)
            VALUES(1,1,'pibes hackers');


        /*admin  admin*/
    /* consu TTYy444y&&&#!! */
    /* cli1  mEH&&YY66%#eF */
    /* pos1  76&&opPolGGgr$4 */

       /* pos1  7TT56Y&&pPolGGgr$4 */


        INSERT INTO usuarios (usuario,activo,clave)

         VALUES('consu',1,'$2a$12$WjlnNjfnJAXDgYiRLWsHl.AgJMXpIjrhSj7tyUkX82P9PfX2lVYOW'), 
                ('cli1',1,'$2a$12$euwX2gerQniRIDs6FOese.YtV75MVR3UO643.vAuVU/2I21tvS.Wy'), 
                ('pos1',1,'$2a$12$O4xZXO7WPApiRXnDXioUQuQme9dyILhmGjvTuIV7Nyn0KbNTVSY4m'),
                ('admin',1,'$2a$12$b3mCSR2Ba3ZP8yvAZqWfUuvtivbkAMBEmVUhhfrWNUxZEvam6qUYq');   
        


        INSERT INTO clientes (nombre,rut,url,usuario)
            VALUES ('betito suarez',234567983910,'www.java.com','cli1');


            INSERT INTO consultores(usuario)
            VALUES ('consu'),('admin');


            INSERT INTO postulantes(cantidad_postulaciones,cedula,departamento,fechanacimiento,primer_apellido,primer_nombre,segundo_apellido,segundo_nombre,usuario)
            VALUES (1,59340394,'Montevideo','1980-01-01','carpa','armando','la','juan','pos1');

            INSERT INTO ofertas (id,cantidad_vacantes,descripcion,fecha_cierre,fecha_publicacion,titulo,area,cliente)
            VALUES(1,10,'habia una vez','2025-11-18',NOW(),'nada',1,'cli1');

            
            INSERT INTO roles (nombre_rol)
            VALUES ('cliente'),
            ('consultor'),
            ('postulante');

        
            INSERT INTO usuarios_roles(usuario_nombre_usuario, rol_nombre_rol)
                VALUES
                    ('cli1', 'cliente'),
                    ('consu', 'consultor'),        
                    ('pos1', 'postulante'),
                    ('admin','consultor'); 

                INSERT INTO logs(fecha_hora, mensaje)
                VALUES (NOW(), 'Datos iniciales cargados.');
    END IF;
END^;

CALL van_los_datos()^;