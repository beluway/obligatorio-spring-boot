package com.bios.edu.uy.obligatorio2025.Dominio;


import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;


@Entity
@Table(name="ofertas")
public class ofertas {
    
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer id;

    @NotNull
    @PastOrPresent
    @Column(name = "fechaPublicacion", nullable = false)
    private Date fechaPublicacion; 
    
    @NotNull
    @PastOrPresent
    @Column(name = "fechaCierre", nullable = false)
    private Date fechaCierre;

    @Column(name = "cliente",nullable = false)    
    @NotNull(message = "seleccione el cliente")
    @ManyToOne // UN CLIENTE PUEDE TENER MUCHAS OFERTAS
    private clientes cliente;

    @Column(name = "descripcion",nullable = false, length = 4000)
    @NotNull(message = "Ingrese una descripción")
    private String descripcion; 
    
    @Column(name="titulo",nullable = false, length = 100)
    @NotNull(message = "Ingrese el título")
    private String titulo;

    @Column(name = "area", nullable = false)
    @NotNull(message = "Ingrese una área")
    @OneToOne
    private areas area;

    @Column(name="cantidadVacantes",nullable = false)
    @NotNull(message = "Ingrese la cantidad de puestos vacantes")
    @Min(0)
    private Integer cantidadVacantes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public clientes getCliente() {
        return cliente;
    }

    public void setCliente(clientes cliente) {
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

    public void setArea(areas area){
        this.area=area;
    }

    public areas getArea(){
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

    public ofertas(Integer id, @NotNull @PastOrPresent Date fechaPublicacion, @NotNull @PastOrPresent Date fechaCierre,
            @NotNull(message = "seleccione el cliente") clientes cliente,
            @NotNull(message = "Ingrese una descripción") String descripcion,
            @NotNull(message = "Ingrese el título") String titulo,
            @NotNull(message = "Ingrese una área") areas area,
            @NotNull(message = "Ingrese la cantidad de puestos vacantes") Integer cantidadVacantes) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCierre = fechaCierre;
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.area = area;
        this.cantidadVacantes = cantidadVacantes;
    }

    
    @Override
    public String toString() {
        return "ofertas [id=" + id + ", fechaPublicacion=" + fechaPublicacion + ", fechaCierre=" + fechaCierre
                + ", cliente=" + cliente + ", descripcion=" + descripcion + ", titulo=" + titulo + "área= "+area+", cantidadVacantes="
                + cantidadVacantes + "]";
    }
    
}
