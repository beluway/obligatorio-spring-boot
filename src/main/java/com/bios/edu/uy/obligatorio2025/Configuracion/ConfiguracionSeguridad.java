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


   /*  @Bean
    public SecurityFilterChain filtroSeguridad (HttpSecurity seguridadHttp) throws Exception
    {
       seguridadHttp
                        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/home/index").permitAll()
                    
                                .requestMatchers(
                                                    "/home/index",      
                                                    "/home/ingresar",   
                                                    "/home/login",      
                                                    "/home/registro",   
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
 */


    @Bean
    public SecurityFilterChain filtroSeguridad(HttpSecurity seguridadHttp) throws Exception {
    seguridadHttp
    
        .authorizeHttpRequests(autorizar -> autorizar
            // --- Rutas públicas ---
            .requestMatchers(
                "/home/index",
                "/home/ingresar",
                "/home/login",
                "/home/registro",
                "/css/**", "/js/**", "/img/**"
            ).permitAll()

            // --- Rutas de postulante ---
            .requestMatchers(
                "/postulaciones/crear",
                "/postulaciones/eliminar",
                "/postulaciones/lista"
            ).hasAuthority("postulante")

            // --- Rutas de cliente ---
            .requestMatchers(
                "/ofertas/crear",
                "/ofertas/eliminar",
                "/ofertas/modificar",
                "/ofertas/listaPorCliente"
            ).hasAuthority("cliente")

            // --- Rutas compartidas ---
            .requestMatchers("/ofertas/**")
                .hasAnyAuthority("postulante", "cliente")
            .requestMatchers("/postulaciones/**")
                .hasAnyAuthority("cliente", "postulante")
            .requestMatchers("/clientes/ver")
                .hasAnyAuthority("postulante", "cliente")
            .requestMatchers("/postulantes/ver")
                .hasAnyAuthority("postulante", "cliente", "consultor")

            // --- Rutas de consultor ---
            .requestMatchers("/clientes/**", "/areas/**", "/consultores/**")
                .hasAuthority("consultor")

            // --- Registro de postulante (solo anónimo) ---
            .requestMatchers("/postulantes/crear").anonymous()

            // --- Cualquier otra ruta requiere autenticación ---
            .anyRequest().authenticated()
        )

        // --- Configuración del formulario de login ---
        .formLogin(login -> login
            
            .loginPage("/home/login")
            .defaultSuccessUrl("/home/main", true)
            .permitAll()
        )

        // --- Configuración del logout ---
        .logout(logout -> logout
            .logoutUrl("/home/deslogueo")
            .logoutSuccessUrl("/home/index?logout")
            .permitAll()
        )
        
            .exceptionHandling(excepcion -> excepcion.authenticationEntryPoint((request,response,authException)->{
            response.sendRedirect("/bioswork/home/index");
        })
        )

        ;

    return seguridadHttp.build();
}


}

