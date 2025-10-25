package com.bios.edu.uy.obligatorio2025.Controladores;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Usuario.Crear;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioClientes;
import com.bios.edu.uy.obligatorio2025.Servicios.IServicioOfertas;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;


@Controller
@RequestMapping("/clientes")

public class ControladorClientes {

    @Autowired
    private final IServicioClientes servicioClientes;

    private final IServicioOfertas servicioOfertas;

    ControladorClientes(IServicioOfertas servicioOfertas, IServicioClientes servicioClientes) {
        this.servicioOfertas = servicioOfertas;
        this.servicioClientes = servicioClientes;
    }

    @GetMapping("/crear")
    public String clienteCrear(Model modelo, Principal usuarioLogueado) throws ExcepcionBiosWork
    {        
        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("cliente", new Cliente());
        return "clientes/crear";        
    }

    //ACA VA FLASH ATTRIBUTES Y REDIRECT 
    @PostMapping("/crear")
    public String clienteProcesarCrear (@ModelAttribute @Validated({Default.class, Crear.class}) Cliente cliente, 
    BindingResult resultado,
    Model modelo,   
    RedirectAttributes attributes) throws ExcepcionBiosWork 
    {              
         if(resultado.hasErrors()){

            modelo.addAttribute("cliente", cliente);
            return "clientes/crear";
          }

          if(servicioClientes.obtener(cliente.getUsuario())!=null)
          {
             modelo.addAttribute("mensaje", "Ya existe el usuario");
             return "clientes/crear";
          }

          List<Cliente> clientesExistentes = servicioClientes.listarActivos();

          for (Cliente c : clientesExistentes) {
            if (c.getRut().toString().equals(cliente.getRut().toString())) {
                modelo.addAttribute("mensaje", "Ya existe un cliente con ese RUT, corregir por favor.");
                return "clientes/crear";
            }
          }

          for (Cliente c : clientesExistentes) {
            if (c.getUsuario().toString().equals(cliente.getUsuario().toString())) {
                modelo.addAttribute("mensaje", "Ya existe un cliente con ese nombre de usuario, usar otro por favor.");
                return "clientes/crear";
            }
          }

          try
          {
            //le seteo el activo 
            cliente.setActivo(true);

            servicioClientes.agregar(cliente); 

            attributes.addFlashAttribute("mensaje","Cliente agregado con éxito.");
            return "redirect:/clientes/lista";
          
          }
          catch(ExcepcionBiosWork e)
          {
            modelo.addAttribute("mensaje","Hubo un error: "+ e.getMessage());
            modelo.addAttribute("cliente", cliente);
            return "clientes/crear";
          }
   }         
        
    


    @GetMapping("/eliminar")
    public String clienteEliminar(@RequestParam String usuario,Model modelo, Principal usuarioLogueado) throws ExcepcionBiosWork {

    // Buscar el cliente
    Cliente cliente = servicioClientes.obtener(usuario);

    if (cliente == null) {
        modelo.addAttribute("mensaje", "No se encontró el cliente con el usuario: "+usuario+".");
        return "clientes/lista";
    }

        //carga la cantidad de ofertas que tiene ese cliente
        int cantidadOfertas = servicioOfertas.listaOfertasCliente(cliente).size();
        modelo.addAttribute("cantidadOfertas", cantidadOfertas);

        modelo.addAttribute("cliente", cliente);
        return "clientes/eliminar";

    }



    @PostMapping("/eliminar")
    public String clienteEliminar(@RequestParam String usuario, 
    Model modelo, 
    RedirectAttributes attributes) throws ExcepcionBiosWork  
    {
              
    // Buscar el cliente
    Cliente clienteEncontrado = servicioClientes.obtener(usuario);

    if (clienteEncontrado == null) {
        attributes.addFlashAttribute("error", "Cliente no encontrado.");
        return "redirect:/clientes/lista";
    }

    try 
    {

        servicioClientes.eliminar(clienteEncontrado.getUsuario());
        attributes.addFlashAttribute("exito", "Cliente eliminado.");
    }
     catch (ExcepcionBiosWork ex) {
       modelo.addAttribute("error", "Hubo un error al eliminar el cliente: " + ex.getMessage());
       return "clientes/eliminar";
    }

    return "redirect:/clientes/lista";
    }


       
    

    @GetMapping("/modificar")
    public String clienteModificar(@RequestParam String usuario,Model modelo, Principal usuarioLogueado) throws ExcepcionBiosWork {

        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("mensaje", "El nombre de usuario no es modificable.");

        Cliente cliente = servicioClientes.obtener(usuario);

        if (cliente==null) {
             modelo.addAttribute("mensaje", "Cliente no encontrado");
            return "redirect:/clientes/lista";
        }

        modelo.addAttribute("cliente", cliente);

        return "clientes/modificar";
    }
    

    @PostMapping("/modificar")
    public String procesarModificar(@ModelAttribute @Valid Cliente cliente,BindingResult resultado, 
    @RequestParam(required = false)String nuevaClave,
    RedirectAttributes attributes, Model modelo) throws Exception{
        //llamo al servicio para buscar el cliente
        Cliente clienteExistente = servicioClientes.obtener(cliente.getUsuario());
        //si hay errores cancelo y vuelvo a mostrar la vista con los mensajes
        if(resultado.hasErrors())
        {
            modelo.addAttribute("cliente", cliente);
            modelo.addAttribute("mensaje", "Corrija los errores");
            return "clientes/modificar";
        }
        //si cliente no existe también muestro mensaje de error
        if (clienteExistente == null){
            modelo.addAttribute("mensaje", "cliente no encontrado");
            return "clientes/modificar";
        }
        try
        {

        // llama al servicio que maneja clave opcional
        servicioClientes.modificar(cliente);
        //uso attributes porque estoy haciendo un redirect a otra vista
            attributes.addFlashAttribute("exito", "Cliente modificado correctamente");
            return "redirect:/clientes/lista";
        }

        catch (ExcepcionBiosWork ex)
        {
            modelo.addAttribute("error", "Hubo un error al eliminar el cliente: " + ex.getMessage());
            return "clientes/modificar";
        }
    }
    

    
    @GetMapping("/ver")    
    public String clienteVer(@RequestParam String usuario, Model modelo, Principal usuarioLogueado) throws ExcepcionBiosWork {
       
        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
        Cliente cliente = servicioClientes.obtener(usuario);
        modelo.addAttribute("cliente", cliente);
        return "clientes/ver";
    }    


    @GetMapping("/lista")    
    public String clientesListar(@ModelAttribute Cliente clientes, Model modelo,Principal usuarioLogueado,String criterio) throws ExcepcionBiosWork {
       
         //Muestro solo los clientes que están activos (no tiene baja lógica)
        List<Cliente> listaClientes = servicioClientes.listarActivos();

        if (criterio != null && !criterio.isEmpty()) {
                listaClientes = servicioClientes.buscarPorCriterio(criterio);
            }
            else {
                listaClientes = servicioClientes.listarActivos();
            }

        modelo.addAttribute("usuarioLogueado", servicioClientes.obtener(usuarioLogueado.getName()));
        modelo.addAttribute("clientes", listaClientes);
        
        return "clientes/lista";

    } 



//ESTE ATRIBUTO SE DIFUNDE A TODAS LAS VISTAS QUE PERTENECEN AL CONTROLADOR, 
//ES UN ATRIBUTO GENERAL (COMO NOMBRE DE USUARIO LOGUEADO)
    @ModelAttribute("tituloEjemplo")
    public String cargarTituloEjemplo()
    {
        return "manejo de Errores";
    }



}
