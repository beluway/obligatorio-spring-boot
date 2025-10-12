CREATE PROCEDURE IF NOT EXISTS van_los_datos()
BEGIN
    IF NOT EXISTS(

        SELECT *
        FROM logs 
   )


   THEN 

            INSERT INTO areas (id,asignada,nombre)
            VALUES(1,1,'pibes hackers');


    /*consu TTYy444y&&&#!!*/
    /*cli1 mEH&&YY66%#eF*/
    /*pos1 76&&opPolGGgr$4*/

        INSERT INTO usuarios (usuario,activo,clave)
         VALUES('consu',1,'efbffd06235e6eae148f661aa2cd17693b95c93ef091659c4428999d601534e6'), 
                ('cli1',1,'2c8bd5f66442b46cdbb457c3e559995edabf10440d3c388b94b789d8182b831f'), 
                ('pos1',1,'b97916712a16f70a887ebd54005c87a0d874f9cb431eb99cce2ca0fe6008c8ce');   
        


        INSERT INTO clientes (nombre,rut,url,usuario)
            VALUES ('betito suarez',234567983910,'www.java.com','cli1');


            INSERT INTO consultores(usuario)
            VALUES ('consu');


            INSERT INTO postulantes(cantidad_postulaciones,cedula,departamento,fechanacimiento,primer_apellido,primer_nombre,segundo_apellido,segundo_nombre,usuario)
            VALUES (1,59340394,'Montevideo','1980-01-01','carpa','armando','la','juan','pos1');

            INSERT INTO ofertas (id,cantidad_vacantes,descripcion,fecha_cierre,fecha_publicacion,titulo,area,cliente)
            VALUES(1,10,'habia una vez','2025-11-18',NOW(),'nada',1,'cli1');

            INSERT INTO postulaciones(fecha_postulacion,oferta,postulante)
            VALUES (NOW(),1,'pos1');
            
            INSERT INTO roles (nombre_rol)
            VALUES ('cliente'),
            ('consultor'),
            ('postulante');

        
            INSERT INTO usuarios_roles(usuario_nombre_usuario, rol_nombre_rol)
                VALUES
                    ('cli1', 'cliente'),
                    ('consu', 'consultor'),        
                    ('pos1', 'postulante'); 

                INSERT INTO logs(fecha_hora, mensaje)
                VALUES (NOW(), 'Datos iniciales cargados.');
    END IF;
END^;

CALL van_los_datos()^;