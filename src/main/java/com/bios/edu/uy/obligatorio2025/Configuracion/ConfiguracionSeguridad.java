package com.bios.edu.uy.obligatorio2025.Configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filtroSeguridad(HttpSecurity seguridadHttp) throws Exception {

        seguridadHttp
            .authorizeHttpRequests(autorizar -> autorizar

                // === RUTAS PÚBLICAS (acceso libre) ===
                .requestMatchers(
                    "/home/index",
                    "/home/ingresar",
                    "/home/login",
                    "/home/registro",
                    "/css/**",
                    "/js/**",
                    "/img/**",
                    "/postulantes/crear",
                    "/postulantes/crear/**"
                ).permitAll()

                // === POSTULANTE ===
                .requestMatchers(
                    "/postulaciones/crear",
                    "/postulaciones/eliminar",
                    "/postulaciones/lista"
                ).hasAuthority("postulante")

                // === CLIENTE ===
                .requestMatchers(
                    "/ofertas/crear",
                    "/ofertas/eliminar",
                    "/ofertas/modificar",
                    "/ofertas/listaPorCliente"
                ).hasAuthority("cliente")
                .requestMatchers(
                    "/pdf/**").hasAnyAuthority("postulante","cliente")
                // === URLs accesibles para usuarios logueados ===
                .requestMatchers("/ofertas/**").hasAnyAuthority("postulante", "cliente")
                .requestMatchers("/postulaciones/**").hasAnyAuthority("cliente", "postulante")
                .requestMatchers("/clientes/ver").hasAnyAuthority("postulante", "cliente", "consultor")
                .requestMatchers("/postulantes/ver").hasAnyAuthority("postulante", "cliente", "consultor")

                // === CONSULTOR ===
                .requestMatchers(
                    "/clientes/**",
                    "/areas/**",
                    "/consultores/**"
                ).hasAuthority("consultor")

                // === TODAS LAS DEMÁS URLs REQUIEREN AUTENTICACIÓN ===
                .anyRequest().authenticated()
            )

            // --- Configuración del formulario de login ---
            .formLogin(login -> login
                .loginPage("/home/login")              // tu formulario personalizado
                .defaultSuccessUrl("/home/main", true) // redirección tras login exitoso
                .permitAll()
            )

            // --- Configuración del logout ---
            /* .logout(logout -> logout
                .logoutUrl("/home/deslogueo")
                .logoutSuccessUrl("/home/index?logout")
                .permitAll()
            ) */
                .logout(logout -> logout
                .logoutUrl("/home/deslogueo")
                .logoutSuccessUrl("/home/index?logout")
                .invalidateHttpSession(true) 
                .clearAuthentication(true)   
                .deleteCookies("JSESSIONID") 
                .permitAll())
                // --- Manejo de excepciones (acceso denegado o no autenticado) --- 
.exceptionHandling(excepcion -> excepcion .authenticationEntryPoint((request, response, authException) -> { response.sendRedirect("/bioswork/home/index"); }) ); return seguridadHttp.build(); } }
