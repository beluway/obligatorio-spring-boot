package com.bios.edu.uy.obligatorio2025.Configuracion;

import com.bios.edu.uy.obligatorio2025.Controladores.ControladorAreas;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad 
{

   
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filtroSeguridad (HttpSecurity seguridadHttp) throws Exception
    {
       seguridadHttp
                        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/home/index").permitAll()
                        /* .requestMatchers("/clientes/crear",
                                                    "/clientes/modificar",
                                                    "/clientes/eliminar",
                                                    "/clientes/lista",
                                                    "/clientes/ver",
                                                    "/areas/crear",
                                                    "/areas/eliminar",
                                                    "/areas/lista",
                                                    "/areas/modificar",
                                                    "/consultores/crer",
                                                    "/consultores/eliminar",
                                                    "/consultores/modificar",
                                                    "/consultores/lista",
                                                    "/consultores/ver").hasAuthority("consultor") */
                                .requestMatchers(
                                                    "/home/index",      // tu página con fondo
                                                    "/home/ingresar",   // primer paso del login
                                                    "/home/login",      // paso de contraseña
                                                    "/home/registro",   // página pública de registro
                                                    "/css/**", "/js/**", "/img/**"                                                  
                                                  ).permitAll()
                                                    .anyRequest()
                                                    .authenticated()               
                                .requestMatchers("/postulaciones/crear",
                                                    "/postulaciones/eliminar",
                                                    "/postulaciones/lista").hasAuthority("postulante")                  
                                .requestMatchers("/ofertas/crear",
                                                    "/ofertas/eliminar",
                                                    "/ofertas/modificar").hasAuthority("cliente")
                                .requestMatchers("/ofertas/**").hasAnyAuthority("postulante","cliente")  
                                .requestMatchers("/postulaciones/**").hasAnyAuthority("cliente","postulante")
                                .requestMatchers("/clientes/**","/areas/**","/consultores/**").hasAuthority("consultor")
                                .requestMatchers("/ofertas/listaPorCliente").hasAuthority("cliente")    
                                .requestMatchers("/postulantes/crear").anonymous()
                                .requestMatchers("/clientes/ver").hasAnyAuthority("postulante","cliente")
                                .requestMatchers("/postulantes/ver").hasAnyAuthority("postulante","cliente","consultor")
                                .anyRequest().authenticated())                   
                               .formLogin(login -> login
                               .loginPage("/home/login")
                               .defaultSuccessUrl("/home/main")
                               .permitAll())
                               
                               .logout(logout -> logout
                               .logoutUrl("/home/deslogueo")
                               .logoutSuccessUrl("home/index?logout")
                               .permitAll()
                               )

                             //  .exceptionHandling(excepcion-> excepcion.authenticationEntryPoint(request,response,authExrepc))
                               
                               ;   
                               
                               

                               return seguridadHttp.build();
                         
    
                    }



}

