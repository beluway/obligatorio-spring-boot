package com.bios.edu.uy.obligatorio2025.Controladores;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;

import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionNoExiste;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioClientes;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioOfertas;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/micuentaC")
public class ControladorMiCuentaCliente  {
    
    @Autowired
  private IServicioClientes servicioClientes;

  @Autowired
  private IServicioOfertas servicioOfertas;

  @Autowired
  PasswordEncoder codificador; 


    @GetMapping("/ver")
    public String ver(Principal usuarioLogueado, Model modelo) throws Exception {
       
        Cliente cliente= servicioClientes.obtener(usuarioLogueado.getName());

        cliente.setClave("");   
             
        //carga la cantidad de ofertas que tiene ese cliente
        int cantidadOfertas = servicioOfertas.listaOfertasCliente(cliente).size();
        modelo.addAttribute("cantidadOfertas", cantidadOfertas);

        modelo.addAttribute("cliente", cliente);

        return "/micuentaC/ver";

    }
           
    

   @PostMapping("/ver") 
public String procesarModificar(
    @Valid @ModelAttribute("cliente") Cliente cliente,
    BindingResult resultado,
    @RequestParam(required = false) String nuevaClave,
    RedirectAttributes attributes,
    Model modelo) throws Exception {

    // Verificar si el cliente existe
    Cliente clienteExistente = servicioClientes.obtener(cliente.getUsuario());
    if (clienteExistente == null) {
        modelo.addAttribute("error", "Cliente no encontrado.");
        modelo.addAttribute("aviso", "El nombre de usuario no es modificable.");
        return "micuentaC/ver";
    }


    // Si hay errores de validación, mostrar de nuevo el formulario
    if (resultado.hasErrors()) {
        modelo.addAttribute("error", "Por favor corrija los errores antes de volver a enviar el formulario.");
        //carga la cantidad de ofertas que tiene ese cliente
        int cantidadOfertas = servicioOfertas.listaOfertasCliente(cliente).size();
        modelo.addAttribute("cantidadOfertas", cantidadOfertas);
        modelo.addAttribute("aviso", "El nombre de usuario no es modificable.");
        return "micuentaC/ver";
    }

    try {
        // Si se ingresó una nueva clave, la actualiza
        /* if (nuevaClave != null && !nuevaClave.isBlank()) {
            clienteExistente.setClave(codificador.encode(nuevaClave));
        } */

        // Guardar cambios
        servicioClientes.modificar(cliente);
        attributes.addFlashAttribute("exito", "Cliente modificado correctamente");

        return "redirect:/micuentaC/ver";

    } catch (Exception ex) {
        modelo.addAttribute("error", "Ocurrió un error al modificar el cliente: " + ex.getMessage());
        return "micuentaC/ver";
    }
}

        

      @PostMapping("/eliminar")
      public String eliminar (Model modelo, RedirectAttributes attributes,  
       @RequestParam("codigoCliente")String codigoPostulante, 
       Principal usuarioLogueado) throws Exception 
      {         
       
         Cliente encontrado = servicioClientes.obtener(codigoPostulante);
            /* Optional<Postulacion> postulacionExistente = servicioPostulaciones.findByOfertaAndPostulante(postulacion.getOferta(),postulacion.getPostulante());   */
            
            if (encontrado==null) {

                throw new ExcepcionNoExiste("El postulante no se encontró");              
            } 

            try
            {
           
              servicioClientes.eliminar(encontrado.getUsuario());        

              attributes.addFlashAttribute("mensaje","Postulación eliminada con éxito.");
              return "redirect:/home/index";

            } 
            catch (Exception ex) {
            modelo.addAttribute("mensaje", "Error " + ex.getMessage());

            return "micuentaC/ver";
            }                
      }


}