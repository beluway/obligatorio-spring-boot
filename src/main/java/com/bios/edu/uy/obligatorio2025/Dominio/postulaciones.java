package com.bios.edu.uy.obligatorio2025.Dominio;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;


@Entity
@Table(name = "postulaciones")
public class postulaciones {
    
    @NotNull
    @PastOrPresent
    @Column(name = "fechaPostulacion")
    private Date fechaPostulacion; 

    @NotNull
    @Column(name = "oferta", nullable = false)
    @OneToOne //UNA POSTULACIÓN SOLO TIENE UNA OFERTA
    private ofertas oferta;


    @NotNull
    @Column(name="postulante", nullable = false)
    @OneToOne //UNA POSTULACIÓN SOLO TIENE UN POSTULANTE
    private postulantes postulante;

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

   
    public postulaciones(@NotNull Date fechaPostulacion, @NotNull Integer cantidadPostulaciones,
            @NotNull ofertas oferta, @NotNull postulantes postulante) {
        this.fechaPostulacion = fechaPostulacion;
     
        this.oferta = oferta;
        this.postulante = postulante;
    }
    

    @Override
    public String toString() {
        return "postulaciones [fechaPostulacion=" + fechaPostulacion + ", oferta=" + oferta + ", postulante="
                + postulante + "]";
    }

 

   



    

}
