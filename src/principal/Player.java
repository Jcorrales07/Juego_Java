package principal;

/**
 *
 * @author Lenovo
 */
public class Player {
   private String username;
   private String password;
   private double score;
   
   Player(String username, String password) {
       this.username = username;
       this.password = password;
       score = 0;
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
    
    // Otras Funciones
    public void mostrarDatos() {
        System.out.println("╠╩═╩╬═════════════════════════╬╩═╩╣");
        System.out.println("╠═◘═╬╣ DATOS DEL JUGADOR ╠╬═◘═╣");
        System.out.println("╠╦═╦╬═════════════════════════╬╦═╦╣\n");
        System.out.println("Usuario: "+ username
                        +"\nContraseña: "+ password
                                +"\nPuntaje: "+ score);
        
    }
}
