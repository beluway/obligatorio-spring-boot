package com.bios.edu.uy.obligatorio2025.Dominio;
import com.bios.edu.uy.obligatorio2025.Dominio.clientes;

import java.sql.Date;


import jakarta.validation.constraints.*;

public class ofertas {
    
 private int id;

    @NotNull
    @PastOrPresent
    private Date fechaPublicacion, fechaCierre;

    @NotNull(message = "seleccione el cliente")
    private clientes cliente;

    @NotNull(message = "Ingrese una descripción")
    private String descripcion;

    @NotNull(message = "Ingrese la cantidad de puestos vacantes")
    private int cantidadVacantes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    } 

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadVacantes() {
        return cantidadVacantes;
    }

    public void setCantidadVacantes(int cantidadVacantes) {
        this.cantidadVacantes = cantidadVacantes;
    }
  

    public clientes getCliente() {
        return cliente;
    }

    public void setCliente(clientes cliente) {
        this.cliente = cliente;
    }


    public ofertas(int id, @NotNull @PastOrPresent Date fechaPublicacion, @NotNull @PastOrPresent Date fechaCierre,
            @NotNull(message = "seleccione el cliente") clientes cliente,
            @NotNull(message = "Ingrese una descripción") String descripcion,
            @NotNull(message = "Ingrese la cantidad de puestos vacantes") int cantidadVacantes) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCierre = fechaCierre;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.cantidadVacantes = cantidadVacantes;
    }


    @Override
    public String toString() {
        return "ofertas [id=" + id + ", fechaPublicacion=" + fechaPublicacion + ", fechaCierre=" + fechaCierre
                + ", cliente=" + cliente + ", descripcion=" + descripcion + ", cantidadVacantes=" + cantidadVacantes
                + "]";
    }


    
   


}
