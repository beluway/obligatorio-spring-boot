package com.bios.edu.uy.obligatorio2025.Dominio;

import java.util.Date;

public class postulaciones {
    
    private Date fechaPostulacion;
    private ofertas oferta;
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

    public postulaciones(Date fechaPostulacion, ofertas oferta, postulantes postulante) {
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
