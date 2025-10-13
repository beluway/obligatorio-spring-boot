package com.bios.edu.uy.obligatorio2025.Servicios;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bios.edu.uy.obligatorio2025.Dominio.*;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioConsultores;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioPostulantes;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepositorioUsuarios;
import com.bios.edu.uy.obligatorio2025.Repositorios.IRepostorioClientes;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Service
public class ServicioDetallesUsuario implements UserDetailsService{
    
    @Autowired
    private IRepositorioUsuarios repositorioUsuarios;

    @Autowired
    private IRepositorioConsultores repositorioConsultores;

    @Autowired
    private IRepositorioPostulantes repositorioPostulantes;

    @Autowired
    private IRepostorioClientes repositorioClientes;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Usuario usuario = repositorioUsuarios.findById(username).orElse(null);

        if(usuario==null)        
        {
           usuario = repositorioClientes.findById(username).orElse(null);
        }           

        if(usuario==null)        
        {
            usuario = repositorioConsultores.findById(username).orElse(null);
        }

        if(usuario==null)
        {
            usuario = repositorioPostulantes.findById(username).orElse(null);
        }

        if(usuario==null || !usuario.isActivo())
        {
           throw new UsernameNotFoundException("el usuario no existe");
        }

       Set<GrantedAuthority> roles = new HashSet<>();
       
       for(Rol r : usuario.getRoles())
       {
            roles.add(new SimpleGrantedAuthority(r.getNombreRol()));
       }
       
        return new User(usuario.getUsuario(), usuario.getClave(), usuario.isActivo(),true,true,true,roles);
            
    } 


}
