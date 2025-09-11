import org.springframework.beans.factory.annotation.Value;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.*;

public class ofertas {
    
    private int id;

    @NotNull
    @PastOrPresent
    private Date fechaPublicacion, fechaCierre;

    @NotNull(message = "seleccione el area")
    private String area;

    @NotNull(message = "Ingrese una descripción")
    private String descripcion;

    @NotNull(message = "Ingrese la cantidad de puestos vacantes")
    private int cantidadVacantes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadVacantes() {
        return cantidadVacantes;
    }

    public void setCantidadVacantes(int cantidadVacantes) {
        this.cantidadVacantes = cantidadVacantes;
    }

    public ofertas(int id, Date fechaPublicacion, Date fechaCierre, String area, String descripcion,
            int cantidadVacantes) {
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.fechaCierre = fechaCierre;
        this.area = area;
        this.descripcion = descripcion;
        this.cantidadVacantes = cantidadVacantes;
    }
    


}
