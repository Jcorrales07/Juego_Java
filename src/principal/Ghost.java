package principal;

/** @author Joe Corrales */

public class Ghost {
    //Atributo
    private String jugador;
    private String estado;
    private String imagen;
    
    Ghost(String imagen, String estado, String jugador) {
        this.imagen = imagen;
        this.estado = estado;
        this.jugador = jugador;
    }
    
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    public String getImagen() {
        return imagen;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
        
    public String getEstado() {
        return estado;
    }
    
    public void setJugador(String jugador) {
       this.jugador = jugador;
    }
    
    public String getJugador() {
        return jugador;
    }
}
