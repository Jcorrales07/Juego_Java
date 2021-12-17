package principal;

/** @author Joe Corrales */
import java.util.ArrayList;

public class Player {
    private String username;
    private String password;
    private int score;
    private int dificultad;
    //Array dinamico para almacenar los resultados de los juegos
    ArrayList<String> reportes = new ArrayList<>();
   
    public Player() {}
    
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
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
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

    //Funcion para listar los ultimos 10 juegos
    public void ultimosJuegos(String username) {
        if (reportes.size() > 0) {
            for(int i = 0; i < reportes.size(); i++) {
                if (reportes.get(i).contains(username)) {
                    System.out.println((i+1)+". "+reportes.get(i));
                }
            }
        } else System.out.println("\n     NO HAY REPORTES!");
    }
    
}
