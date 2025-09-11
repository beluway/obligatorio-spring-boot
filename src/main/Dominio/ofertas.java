import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.*;

public class ofertas {
    
    private int id;

    @NonNull
    @PastOrPresent
    private Date fechaPublicacion, fechaCierre;

    @NonNull
    private String area;

    @NonNull
    private String descripcion;

    @NonNull
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
