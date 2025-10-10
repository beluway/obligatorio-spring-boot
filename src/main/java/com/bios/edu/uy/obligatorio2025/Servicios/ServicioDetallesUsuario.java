package com.bios.edu.uy.obligatorio2025.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioConsultores;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulantes;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioUsuarios;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepostorioClientes;

import com.bios.edu.uy.obligatorio2025.Dominio.*;


@Service
public class ServicioDetallesUsuario implements UserDetailsService{


    @Autowired
    private IRepositorioConsultores repositorioConsultores;

    @Autowired 
    private IRepositorioPostulantes repositorioPostulantes;

    @Autowired
    private IRepositorioUsuarios repositorioUsuarios;

    @Autowired
    private IRepostorioClientes repositorioClientes;


///ESTO CONTINUA

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {      
       
        Usuario usuario = repositorioClientes.findByUsuario(username).orElse(null);

        if(usuario ==null)
        {
            usuario = repositorioConsultores.findByUsuario(username).orElse(null);
        }
        
        else if(usuario ==null)
        {
            usuario = repositorioPostulantes.findByUsuario(username).orElse(null);
        }

       /*  if(usuario == null || !usuario.isActivo) */

    }
    
}
