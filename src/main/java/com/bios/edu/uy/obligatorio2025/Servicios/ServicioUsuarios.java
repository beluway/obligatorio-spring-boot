package com.bios.edu.uy.obligatorio2025.Servicios;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.Usuario;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioUsuarios;

@Service
public class ServicioUsuarios implements IServicioUsuarios{
    

    @Autowired
    private IRepositorioUsuarios repositorioUsuario;

        @Override
        public Usuario usuarioParaLogin(String usuario) throws ExcepcionBiosWork
        {           
             return repositorioUsuario.findByUsuarioAndActivoTrue(usuario)
            .orElse(null);
        }

        @Override
        public Usuario usuarioLogueado (String usuario, String clave) throws ExcepcionBiosWork
        {
            return repositorioUsuario.findByUsuarioAndClave(usuario, clave);
        }


       

}
