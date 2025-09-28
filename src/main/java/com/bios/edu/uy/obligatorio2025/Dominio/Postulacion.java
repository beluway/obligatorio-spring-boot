package com.bios.edu.uy.obligatorio2025.Dominio;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


@Entity
@Table(name = "postulaciones")
public class Postulacion {
    
   /*  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; */

    //CLAVE COMPUESTA (OFERTA + POSTULANTE)
    @EmbeddedId
    private PostulacionId id;
 
    @NotNull
    @PastOrPresent
    @Column(name = "fechaPostulacion")
    private Date fechaPostulacion; 

    //LA COLUMNA OFERTA ES UNA UNION (JOIN) CON LA TABLA OFERTA Y ADEMAS FORMA LA PK COMPUESTA    
    @NotNull
    @MapsId("idOferta")
    @OneToOne //UNA POSTULACIÓN SOLO TIENE UNA OFERTA
    @JoinColumn(name = "oferta", nullable = false)
    private Oferta oferta;

    //LA COLUMNA POSTULANTE ES UNA UNION (JOIN) CON LA TABLA POSTULANTE Y ADEMAS FORMA LA PK COMPUESTA    
    @NotNull
    @MapsId("usuarioPostulante")
    @OneToOne //UNA POSTULACIÓN SOLO TIENE UN POSTULANTE
    @JoinColumn (name="postulante",nullable = false)
    private Postulante postulante;


    public Date getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(Date fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }

    public Oferta getOferta() {
        return oferta;
    }

    public void setOferta(Oferta oferta) {
        this.oferta = oferta;
    }

    public Postulante getPostulante() {
        return postulante;
    }

    public void setPostulante(Postulante postulante) {
        this.postulante = postulante;
    }

    //constructor vacío
    public Postulacion(){}

    public Postulacion(Integer id, @NotNull @PastOrPresent Date fechaPostulacion, @NotNull Oferta oferta,
            @NotNull Postulante postulante) {
        
        this.fechaPostulacion = fechaPostulacion;
        this.oferta = oferta;
        this.postulante = postulante;
    }

    @Override
    public String toString() {
        return "postulaciones [fechaPostulacion=" + fechaPostulacion + ", oferta=" + oferta
                + ", postulante=" + postulante + "]";
    }

       
   @Embeddable
   public static class PostulacionId implements Serializable{

    private String usuarioPostulante;  
    private Integer idOferta;

    public String getUsuarioPostulante() {
        return usuarioPostulante;
    }
    public void setUsuarioPostulante(String usuarioPostulante) {
        this.usuarioPostulante = usuarioPostulante;
    }
    public Integer getIdOferta() {
        return idOferta;
    }
    public void setIdOferta(Integer idOferta) {
        this.idOferta = idOferta;
    }
    
    
    public PostulacionId(String usuarioPostulante, Integer idOferta) {
        this.usuarioPostulante = usuarioPostulante;
        this.idOferta = idOferta;
    }

       
    

   } 
    
 
    

}


