package com.bios.edu.uy.obligatorio2025.Dominio;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;


@Entity
@Table(name="postulantes")
/* @PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "usuario")
@DiscriminatorValue("Postulante")
 */

public class Postulante extends Usuario{
    
    //ESTE ES EL NOMBRE COMPLETO COMPUESTO EMBEBIDO
/*     @Embedded
    public NombreCompleto nombreCompleto;
 */

    @Column(name = "cantidadPostulaciones")
    private int cantidadPostulaciones;

 
    @Column(name = "cedula",unique = true,nullable = false,length = 8)
    @NotNull(message = "Ingrese la cedula")
    private Long cedula;
     
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull (message = "Seleccione la fecha de nacimiento")
    @PastOrPresent
    @Column(name = "fechanacimiento",nullable = false)
    private LocalDate fechanacimiento;    

    @Column(name="departamento",length = 20,nullable = false)
    @NotBlank (message = "Seleccione el departamento")
    private String departamento;
         
    @Column(name = "primerNombre", nullable = false, length = 15)
    @NotBlank(message = "Ingrese el nombre")
    private String primerNombre;

    @Column(name = "segundoNombre",nullable = true, length = 15)
    private String segundoNombre; 

    @NotBlank(message = "ingrese el apellido")
    @Column(name="primerApellido", nullable = false,length=15)
    private String primerApellido;

    @NotBlank(message = "ingrese el segundo apellido")
    @Column(name="segundoApellido", nullable = false,length=15)
    private String segundoApellido;   

    
    //VER SI ESTO SIRVE PARA PDF (CAMPO QUE NO VA A LA BD)
    @Transient 
    @NotNull (message = "Seleccione un .pdf para subirlo")
    private MultipartFile pdf;
   
  
    public Long getCedula() {
        return cedula;
    }


    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }


    public LocalDate getFechanacimiento() {
        return fechanacimiento;
    }


    public void setFechanacimiento(LocalDate fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }


    public String getDepartamento() {
        return departamento;
    }


    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
 


    public MultipartFile getPdf() {
        return pdf;
    }


    public void setPdf(MultipartFile pdf) {
        this.pdf = pdf;
    }


    public int getCantidadPostulaciones() {
        return cantidadPostulaciones;
    }


    public void setCantidadPostulaciones(int cantidadPostulaciones) {
        this.cantidadPostulaciones = cantidadPostulaciones;
    }

    //constructor vacío para JPA
   

    public String getPrimerNombre() {
        return primerNombre;
    }


    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }


    public String getSegundoNombre() {
        return segundoNombre;
    }


    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
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


    public Postulante(@NotBlank(message = "ingrese el usuario") String usuario,
            @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[!#$%&/()=?]).{6,15}$", message = "La clave debe tener entre 6 y 15 caracteres, al menos una mayúscula y un caracter especial") @NotBlank(message = "ingrese la clave") String clave,
            Boolean activo, int cantidadPostulaciones, @NotNull(message = "Ingrese la cedula") Long cedula,
            @NotNull(message = "Seleccione la fecha de nacimiento") @PastOrPresent LocalDate fechanacimiento,
            @NotBlank(message = "Seleccione el departamento") String departamento,
            @NotBlank(message = "Ingrese el nombre") String primerNombre, String segundoNombre,
            @NotBlank(message = "ingrese el apellido") String primerApellido,
            @NotBlank(message = "ingrese el segundo apellido") String segundoApellido,
            @NotNull(message = "Seleccione un .pdf para subirlo") MultipartFile pdf) {
        super(usuario, clave, activo);
        
        this.cantidadPostulaciones = cantidadPostulaciones;
        this.cedula = cedula;
        this.fechanacimiento = fechanacimiento;
        this.departamento = departamento;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.pdf = pdf;
    }


        public Postulante(){};

    
}
