package com.bios.edu.uy.obligatorio2025.Dominio;

import java.io.File;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import jakarta.persistence.Entity;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;




@Entity
public class Postulante extends Usuario{
    
    //ESTE ES EL NOMBRE COMPLETO COMPUESTO EMBEBIDO
    @Embedded
    public NombreCompleto nombreCompleto;

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

    public Postulante(String usuario, String clave, NombreCompleto nombreCompleto, @Min(1) int cantidadPostulaciones,
            @NotNull(message = "Ingrese la cedula") Long cedula,
            @NotNull(message = "Seleccione la fecha de nacimiento") @PastOrPresent Date fechanacimiento,
            @NotNull(message = "Seleccione el departamento") String departamento,
            @NotNull(message = "Seleccione un .pdf para subirlo") File pdf) {
        super(usuario, clave);
        this.nombreCompleto = nombreCompleto;
        this.cantidadPostulaciones = cantidadPostulaciones;
        this.cedula = cedula;
        this.fechanacimiento = fechanacimiento;
        this.departamento = departamento;
        this.pdf = pdf;
     
    }




    @Override
    public String toString() {
        return "postulantes [nombreCompleto=" + nombreCompleto + ", cantidadPostulaciones=" + cantidadPostulaciones
                + ", cedula=" + cedula + ", fechanacimiento=" + fechanacimiento + ", departamento=" + departamento
                + ", pdf=" + pdf + "]";
    }



    //Esto es la inner class del atributo multivaluado

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
