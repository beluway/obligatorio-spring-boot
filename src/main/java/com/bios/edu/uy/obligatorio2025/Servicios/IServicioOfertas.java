package com.bios.edu.uy.obligatorio2025.Servicios;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.bios.edu.uy.obligatorio2025.Dominio.Cliente;
import com.bios.edu.uy.obligatorio2025.Dominio.Oferta;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulacion;
import com.bios.edu.uy.obligatorio2025.Dominio.Postulante;
import com.bios.edu.uy.obligatorio2025.Excepciones.ExcepcionBiosWork;

public interface IServicioOfertas {

    void agregar (Oferta oferta) throws ExcepcionBiosWork;
    void modificar (Oferta oferta) throws ExcepcionBiosWork;
    void eliminar (Integer codigo) throws ExcepcionBiosWork;
    List<Oferta> listaOfertas() throws ExcepcionBiosWork;
    Oferta obtener(Integer id) throws ExcepcionBiosWork;
    List<Oferta> listaOfertasCliente (Cliente cliente) throws ExcepcionBiosWork;
    public List<Oferta> listaOfertasVigentes() throws ExcepcionBiosWork;
/*     List<Oferta> listaOfertasPostulante( Postulante postulante) throws Exception; */
    List<Oferta> buscarPorCriterio(String criterio) throws ExcepcionBiosWork;
    
}
