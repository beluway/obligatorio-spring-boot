import java.util.ArrayList;
import java.util.List;
import com.bios.edu.uy.obligatorio2025.*;

import io.micrometer.common.lang.NonNull;


public class clientes extends usuarios {
    


    @NonNull
    private long rut;

    @NonNull
    private String nombre;

    private String url;

    @NonNull
    private ArrayList<ofertas> listaOfertas;


    public long getRut() {
        return rut;
    }
    public void setRut(long rut) {
        this.rut = rut;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

     public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

   public ArrayList<ofertas> getListaOfertas() {
        return listaOfertas;
    }

    public void setListaOfertas(ArrayList<ofertas> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }
      

    public clientes(long rut, String nombre, String url, ArrayList<ofertas> listaOfertas) {
        this.rut = rut;
        this.nombre = nombre;
        this.url = url;
        this.listaOfertas = listaOfertas;
    }


     public clientes() {
       
    }

    @Override
    public String toString() {
        return "clientes [rut=" + rut + ", nombre=" + nombre +", url= " +url +"]";
    }



 

}
