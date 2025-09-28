package com.bios.edu.uy.obligatorio2025.Dominio;

import java.io.File;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;




@Entity
@Table(name="postulantes")
@PrimaryKeyJoinColumn(name="usuario", referencedColumnName = "usuario")
public class Postulante extends Usuario{
    
    //ESTE ES EL NOMBRE COMPLETO COMPUESTO EMBEBIDO
    @Embedded
    public NombreCompleto nombreCompleto;


  public NombreCompleto getNombreCompleto() {
        return nombreCompleto;
    }


    public void setNombreCompleto(NombreCompleto nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }


    public String getTipo_usuario() {
        return tipo_usuario;
    }


    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }



  private String tipo_usuario;

    //ESTA ES LA CLAVE FORÁNEA DE LA ENTIDAD USUARIO EMBEBIDA
/*     @EmbeddedId
    private ClaveFK foreignKeyUsu; */

    @Min(1)
    private int cantidadPostulaciones;

    @Column(name = "cedula",unique = true,nullable = false,length = 8)
    @NotNull(message = "Ingrese la cedula")
    private Long cedula;
     
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
   
  
    public Long getCedula() {
        return cedula;
    }


    public void setCedula(Long cedula) {
        this.cedula = cedula;
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

    //constructor vacío para JPA
    public Postulante(){}





    //Esto es la inner class del atributo multivaluado

    public Postulante(NombreCompleto nombreCompleto, String tipo_usuario, @Min(1) int cantidadPostulaciones,
            @NotNull(message = "Ingrese la cedula") Long cedula,
            @NotNull(message = "Seleccione la fecha de nacimiento") @PastOrPresent Date fechanacimiento,
            @NotNull(message = "Seleccione el departamento") String departamento) {
        this.nombreCompleto = nombreCompleto;
        this.tipo_usuario = tipo_usuario;
        this.cantidadPostulaciones = cantidadPostulaciones;
        this.cedula = cedula;
        this.fechanacimiento = fechanacimiento;
        this.departamento = departamento;
    }





    @Embeddable
    public class NombreCompleto {
    
        
    @Column(name = "primerNombre", nullable = false, length = 15)
    @NotNull(message = "Ingrese el nombre")
    private String primerNombre;

  //(message = "Ingrese el segundo nombre ")
    @Column(name = "segundoNombre",nullable = false, length = 15)
    private String segundoNombre; 

    @NotNull(message = "ingrese el apellido")
    @Column(name="primerApellido", nullable = false,length=15)
    private String primerApellido;

    @NotNull(message = "ingrese el segundo apellido")
    @Column(name="segundoApellido", nullable = false,length=15)
    private String segundoApellido;   
    }

    //Esto es para definir la clave compuesta
    
  /*   @Embeddable 
    public class ClaveFK implements Serializable {
        @Column(name = "usuario" ,nullable=false)
        private String usuario;
   
    }
 */

}
