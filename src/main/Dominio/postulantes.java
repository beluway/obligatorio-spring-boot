import io.micrometer.common.lang.NonNull;
import javax.imageio;
import javax.imageio.ImageIO;
import java.io.File;
import com.bios.edu.uy.obligatorio2025.*;


//VER SI ESTE PAQUETE SIRVE PARA SUBIR ARCHIVOS
import org.apache.commons.File;


public class postulantes extends usuarios{
    
    @NonNull
    private long cedula;

    @NonNull
    private String Primernombre,primerApellido,segundoApellido;

    private String segundoNombre;

    @NonNull
    private Date fechanacimiento;

    @NonNull
    private String departamento;


    //ESTA ES UNA OPCION, OTRA OPCION ES TENER ESTA LISTA EN UNA ENTIDAD "POSTULACIONES"
    @NonNull
    private ArrayList<ofertas> listaOfertas;

    //VER SI ESTO FUNCIONA COMO IMAGEN
    private ImageIO imagen;


    //VER SI ESTO SIRVE PARA PDF
    @NonNull
    private File pdf;


    public long getCedula() {
        return cedula;
    }


    public void setCedula(long cedula) {
        this.cedula = cedula;
    }


    public String getPrimernombre() {
        return Primernombre;
    }


    public void setPrimernombre(String primernombre) {
        Primernombre = primernombre;
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


    public String getSegundoNombre() {
        return segundoNombre;
    }


    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
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

    
    public ArrayList<ofertas> getListaOfertas() {
        return listaOfertas;
    }


    public void setListaOfertas(ArrayList<ofertas> listaOfertas) {
        this.listaOfertas = listaOfertas;
    }
    

    public ImageIO getImagen() {
        return imagen;
    }


    public void setImagen(ImageIO imagen) {
        this.imagen = imagen;
    }


    public File getPdf() {
        return pdf;
    }


    public void setPdf(File pdf) {
        this.pdf = pdf;
    }




    public postulantes(long cedula, String primernombre, String primerApellido, String segundoApellido,
            String segundoNombre, Date fechanacimiento, String departamento, ImageIO imagen, java.io.File pdf) {
        this.cedula = cedula;
        Primernombre = primernombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.segundoNombre = segundoNombre;
        this.fechanacimiento = fechanacimiento;
        this.departamento = departamento;
        this.imagen = imagen;
        this.pdf = pdf;
    }



    //VER COMO MOSTRAR LA IMAGEN Y EL PDF EN EL toString()
    @Override
    public String toString() {
        return "postulantes [cedula=" + cedula + ", Primernombre=" + Primernombre + ", primerApellido=" + primerApellido
                + ", segundoApellido=" + segundoApellido + ", segundoNombre=" + segundoNombre + ", fechanacimiento="
                + fechanacimiento + ", departamento=" + departamento + ", imagen=" + imagen + ", pdf=" + pdf + "]";
    }



    
}
