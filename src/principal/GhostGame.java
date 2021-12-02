package principal;

import java.util.Scanner;

public class GhostGame {
    Scanner input = new Scanner(System.in);
    private Player usuarios[];

    // Funcion verificar si el usuario existe
    public Player buscarUsuario(String username) {
        for(Player jugador: usuarios) {
            if (jugador != null && jugador.getUsername().equals(username)) {
                System.out.println("Ese usuario ya esta utilizado!");
                return jugador;
            }
        }
        return null;
    }
    
    // Crear un nuevo jugador
    public boolean createPlayer(String username, String password) {
        if(buscarUsuario(username).equals(null)) {
           for(int i = 0; i < usuarios.length; i++) {
                if (usuarios[i] == null) {
                    usuarios[i] = new Player(username, password); 
                    System.out.println("Usuario correctamente creado!");
                    return true;
                }
            } 
        }
        System.out.println("Ese usuario ya existe!");
        return false;
    }
}
