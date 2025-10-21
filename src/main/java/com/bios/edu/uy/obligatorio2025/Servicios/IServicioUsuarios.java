package com.bios.edu.uy.obligatorio2025.Servicios;
import com.bios.edu.uy.obligatorio2025.Dominio.Usuario;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioUsuarios {
    
    Usuario usuarioParaLogin(String usuario) throws ExcepcionBiosWork;

    Usuario usuarioLogueado (String usuario, String clave) throws ExcepcionBiosWork;
}
