import jakarta.validation.constraints.*;

public abstract class usuarios {
    
    @NotNull
    private String usuario;

    @NotNull
    private String clave;

    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    public usuarios(String usuario, String clave) {
        this.usuario = usuario;
        this.clave = clave;
    }

    @Override
    public String toString() {
        return "usuarios [usuario=" + usuario + ", clave=" + clave + "]";
    }


    

}
