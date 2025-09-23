package com.bios.edu.uy.obligatorio2025.Dominio;

import javax.imageio.ImageIO;
import java.io.File;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;



@Entity
@Table(name="postulantes")
public class postulantes extends usuarios{
    
    @Column(name="cantidadPostulaciones", nullable=false)
    @Min(0)
    private int cantidadPostulaciones;

    @Column(name = "cedula",unique = true,nullable = false,length = 8)
    @NotNull(message = "Ingrese la cedula")
    private Long cedula;

    @Column(name = "primerNombre", nullable = false, length = 15)
    @NotNull(message = "Ingrese el nombre")
    private String Primernombre;

    @Optional
    //(message = "Ingrese el segundo nombre ")
    @Column(name = "segundoNombre",nullable = false, length = 15)
    private String segundoNombre;

    @NotNull(message = "ingrese el apellido")
    @Column(name="primerApellido", nullable = false,length=15)
    private String primerApellido;

    @NotNull(message = "ingrese el segundo apellido")
    @Column(name="segundoApellido", nullable = false,length=15)
    private String segundoApellido;
    
    @NotNull (message = "Seleccione la fecha de nacimiento")
    @PastOrPresent
    @Column(name = "fechanacimiento",nullable = false)
    private Date fechanacimiento;    

    @Column(name="departamento",length = 20,nullable = false)
    @NotNull (message = "Seleccione el departamento")
    private String departamento;

    //VER SI ESTO SIRVE PARA PDF
    @NotNull (message = "Seleccione un .pdf para subirlo")
    private File pdf;
   
    //VER SI ESTO FUNCIONA COMO IMAGEN
    private ImageIO imagen;

    public Long getCedula() {
        return cedula;
    }


    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }


    public String getPrimernombre() {
        return Primernombre;
    }


    public void setPrimernombre(String primernombre) {
        Primernombre = primernombre;
    }


    public String getPrimerApellido() {
        return primerApellido;
    }


    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }


    public String getSegundoApellido() {
        return segundoApellido;
    }


    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }


    public String getSegundoNombre() {
        return segundoNombre;
    }


    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }


    public Date getFechanacimiento() {
        return fechanacimiento;
    }


    public void setFechanacimiento(Date fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }


    public String getDepartamento() {
        return departamento;
    }


    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

  
    public void setImagen(ImageIO imagen) {
        this.imagen = imagen;
    }


    public File getPdf() {
        return pdf;
    }


    public void setPdf(File pdf) {
        this.pdf = pdf;
    }


    public int getCantidadPostulaciones() {
        return cantidadPostulaciones;
    }


    public void setCantidadPostulaciones(int cantidadPostulaciones) {
        this.cantidadPostulaciones = cantidadPostulaciones;
    }


    public postulantes(String usuario, String clave, int cantidadPostulaciones,
            @NotNull(message = "Ingrese la cedula") Long cedula, @NotNull String primernombre,
            @NotNull String primerApellido, @NotNull String segundoApellido, String segundoNombre,
            @NotNull(message = "Seleccione la fecha de nacimiento") Date fechanacimiento,
            @NotNull(message = "Seleccione el departamento") String departamento) {
        super(usuario, clave);
        this.cantidadPostulaciones = cantidadPostulaciones;
        this.cedula = cedula;
        Primernombre = primernombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.segundoNombre = segundoNombre;
        this.fechanacimiento = fechanacimiento;
        this.departamento = departamento;
    }


    @Override
    public String toString() {
        return "postulantes [cantidadPostulaciones=" + cantidadPostulaciones + ", cedula=" + cedula + ", Primernombre="
                + Primernombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido
                + ", segundoNombre=" + segundoNombre + ", fechanacimiento=" + fechanacimiento + ", departamento="
                + departamento + ", pdf=" + pdf + ", imagen=" + imagen + "]";
    }
  


}
