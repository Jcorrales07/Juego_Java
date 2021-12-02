package principal;

import java.util.Scanner;

public class GhostGame {
    Scanner input = new Scanner(System.in);
    private Player usuarios[];

    // Funcion verificar si el usuario existe
    public String buscarUsuario(String username) {
        for(Player jugador: usuarios) {
            if (jugador.getUsername().equalsIgnoreCase(username)) {
                System.out.println("Ese usuario ya esta utilizado!");
            }
        }
        return "Usuario disponible!";
    }
    
    // Crear un nuevo jugador
    public Player createPlayer() {
        System.out.println("╠╩═╩╬══════════════╬╩═╩╣");
        System.out.println("╠╬══╣REGISTRAR USUARIO╠══╬╣");
        System.out.println("╠╦═╦╬══════════════╬╦═╦╣");
        
        System.out.print("Ingrese su usuario: ");
        String username = input.next();
        buscarUsuario(username);
        System.out.print("Ingrese su contraseña: ");
        String password = input.next();
        
        for(int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] == null) {
                usuarios[i] = new Player(username, password); 
            }
        }
        
        // return temporal
        return null;
    }
}
