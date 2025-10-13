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
    public SecurityFilterChain filtroSeguridad(HttpSecurity seguridadHttp) throws Exception {
    seguridadHttp
    
        .authorizeHttpRequests(autorizar -> autorizar
            //rutas publicas
            .requestMatchers(
                "/home/index",
                "/home/ingresar",
                "/home/login",
                "/home/registro",
                "/css/**", "/js/**", "/img/**"
            ).permitAll()

            //postulante
            .requestMatchers(
                "/postulaciones/crear",
                "/postulaciones/eliminar",
                "/postulaciones/lista").hasAuthority("postulante")

            //cliente
            .requestMatchers(
                "/ofertas/crear",
                "/ofertas/eliminar",
                "/ofertas/modificar",
                "/ofertas/listaPorCliente").hasAuthority("cliente")

            //urls que usan todos los usuarios logueados
            .requestMatchers("/ofertas/**").hasAnyAuthority("postulante", "cliente")
            .requestMatchers("/postulaciones/**").hasAnyAuthority("cliente", "postulante")
            .requestMatchers("/clientes/ver").hasAnyAuthority("postulante", "cliente")
            .requestMatchers("/postulantes/ver").hasAnyAuthority("postulante", "cliente", "consultor")

            //consultor
            .requestMatchers("/clientes/**", "/areas/**", "/consultores/**")
                .hasAuthority("consultor")

            //esta es publica (postulante anonimo que se autoregistra)
            .requestMatchers("/postulantes/crear").anonymous()

            //todas las demas urls piden autenticarse
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
            //si el usuarion no esta logueado y quiere entrar a una url, queda redirigido al home/index
            .exceptionHandling(excepcion -> excepcion.authenticationEntryPoint((request,response,authException)->{
            response.sendRedirect("/bioswork/home/index");
        })
        )

        ;

    return seguridadHttp.build();
}


}

