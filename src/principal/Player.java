package principal;

/** @author Joe Corrales */
import java.util.ArrayList;

public class Player {
    private String username;
    private String password;
    private double score;
    private int dificultad;
    //Array dinamico para almacenar los resultados de los juegos
    ArrayList<String> logrosFinales = new ArrayList<>();
   
    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        score = 0;
        dificultad = 8;
    }
   
    // SET y GET de username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // SET y GET de password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    // SET y GET de score
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
    
    public void setDificultad(int dificultad) {
        this.dificultad = dificultad;
    }
    
    public int getDificultad() {
        return dificultad;
    }
    
    // Otras Funciones
    public void mostrarDatos() {
        System.out.println("\n╠═◘═╬╣DATOS DEL JUGADOR╠╬═◘═╣"
                + "\nUsuario: "+ username
                +"\nContraseña: "+ password
                +"\nPuntaje: "+ score);
    }
    
    public void set3Pts() {
        this.score += 3;
    }

}
