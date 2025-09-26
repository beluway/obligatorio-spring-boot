package com.bios.edu.uy.obligatorio2025.Dominio;


import java.util.Date;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


@Entity
@Table(name = "postulaciones")
public class postulaciones {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @PastOrPresent
    @Column(name = "fechaPostulacion")
    private Date fechaPostulacion; 

    @NotNull
    @ManyToOne //UNA POSTULACIÓN SOLO TIENE UNA OFERTA
    @JoinColumn(name = "oferta", nullable = false)
    private ofertas oferta;

    @NotNull
    @ManyToOne //UNA POSTULACIÓN SOLO TIENE UN POSTULANTE
    @JoinColumn (name="postulante",nullable = false)
    private postulantes postulante;

    //CLAVES COMPUESTAS
  /*   @EmbeddedId
    private ClavesCompuestas foreignKeyUsu; */

    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(Date fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public ofertas getOferta() {
        return oferta;
    }

    public void setOferta(ofertas oferta) {
        this.oferta = oferta;
    }

    public postulantes getPostulante() {
        return postulante;
    }

    public void setPostulante(postulantes postulante) {
        this.postulante = postulante;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public postulaciones(Integer id, @NotNull @PastOrPresent Date fechaPostulacion, @NotNull ofertas oferta,
            @NotNull postulantes postulante) {
        this.id = id;
        this.fechaPostulacion = fechaPostulacion;
        this.oferta = oferta;
        this.postulante = postulante;
    }

    @Override
    public String toString() {
        return "postulaciones [id=" + id + ", fechaPostulacion=" + fechaPostulacion + ", oferta=" + oferta
                + ", postulante=" + postulante + "]";
    }

   
   
 
 /*   @Embeddable
   public class ClavesCompuestas implements Serializable{

    @Column(name = "usuario", nullable = false)
    private String usuario;   
    
    @Column(name = "id", nullable = false)
    private Integer id;
    
   } 
    */
 
    

}


