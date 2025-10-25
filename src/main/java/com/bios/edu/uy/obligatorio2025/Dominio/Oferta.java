package com.bios.edu.uy.obligatorio2025.Dominio;

import java.time.LocalDate;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;


import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;


@Entity
@Table(name="ofertas")
public class Oferta {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY) 
private Integer id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fechaPublicacion", nullable = false)
    private LocalDate fechaPublicacion; 
      

    @NotNull(message = "{NotNull.oferta.fechaCierre}")
    @Future(message = "{Future.oferta.fechaCierre}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_Cierre", nullable = false)
    private LocalDate fechaCierre;

    @NotNull(message = "{NotBlank.usuario.usuario}")
    @ManyToOne (optional = false)
    @JoinColumn(name = "cliente",nullable = false)
    private Cliente cliente;

    @NotNull(message = "{NotBlank.area.nombre}")
    @ManyToOne (optional = false)
    @JoinColumn(name = "area",nullable = false)
    private Area area;

    @Column(name = "descripcion",nullable = false, length = 4000)
    @NotBlank(message = "{NotBlank.oferta.descripcion}")
    private String descripcion; 
    
    @Column(name="titulo",nullable = false, length = 100)
    @NotBlank(message = "{NotBlank.oferta.titulo}")
    private String titulo;    

    @Column(name="cantidadVacantes",nullable = false)
    @NotNull(message = "NotNull.oferta.cantidadVacantes")
    @Min(0)
    private Integer cantidadVacantes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    public LocalDate getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDate fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalDate getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(LocalDate fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setArea(Area area){
        this.area=area;
    }

    public Area getArea(){
        return area;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getCantidadVacantes() {
        return cantidadVacantes;
    }

    public void setCantidadVacantes(Integer cantidadVacantes) {
        this.cantidadVacantes = cantidadVacantes;
    }

    //constructor vacío para que JPA pueda hacer la consulta en la bd
    //Hibernate necesita un constructor público vacío en todas las entidades (@Entity). Esto es obligatorio para poder instanciarlas al leer datos desde la base de datos.
    public Oferta(){}

    public Oferta(Integer id, @NotNull LocalDate fechaPublicacion, @NotNull @Future LocalDate fechaCierre,
            Cliente cliente, @NotBlank(message = "Ingrese una descripción") String descripcion,
            @NotBlank(message = "Ingrese el título") String titulo, @NotNull(message = "Ingrese una área") Area area,
            @NotNull(message = "Ingrese la cantidad de puestos vacantes") @Min(0) Integer cantidadVacantes) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCierre = fechaCierre;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.area = area;
        this.cantidadVacantes = cantidadVacantes;
    }
   



    public Oferta(Integer id, @NotNull LocalDate fechaPublicacion, @NotNull @Future LocalDate fechaCierre,
            Cliente cliente, @NotNull(message = "Ingrese una área") Area area,
            @NotNull(message = "Ingrese una descripción") String descripcion,
            @NotNull(message = "Ingrese el título") String titulo,
            @NotNull(message = "Ingrese la cantidad de puestos vacantes") @Min(0) Integer cantidadVacantes,
            List<Postulacion> postulaciones) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCierre = fechaCierre;
        this.cliente = cliente;
        this.area = area;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.cantidadVacantes = cantidadVacantes;
       /*  this.postulaciones = postulaciones; */
    }

    @Override
    public String toString() {
        return "Oferta [id=" + id + ", fechaPublicacion=" + fechaPublicacion + ", fechaCierre=" + fechaCierre
                + ", cliente=" + cliente + ", descripcion=" + descripcion + ", titulo=" + titulo + ", area=" + area
                + ", cantidadVacantes=" + cantidadVacantes + "]";
    }
    
}
