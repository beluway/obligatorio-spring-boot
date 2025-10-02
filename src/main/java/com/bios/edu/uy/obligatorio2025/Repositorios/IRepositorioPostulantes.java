package com.bios.edu.uy.obligatorio2025.Repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Dominio.Usuario;


public interface IRepositorioPostulantes extends JpaRepository<Postulante,String>{
    
     //Usuario findByUsuario (String usuario);    

    /*  @NativeQuery
     Postulante Postulante 
 */


 //@Query("select * from usuarios u inner join postulantes p on u.usuario = p.usuario")
 

 @Query("select u from usuarios u inner join postulantes p where u.usuario WHERE u.usuario=:usuario")
 Postulante buscarPostulante (@Param("usuario")String usuario);
 


}
