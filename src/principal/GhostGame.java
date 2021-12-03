package principal;

import java.util.Scanner;

public class GhostGame {
    Scanner input = new Scanner(System.in);
    private Player[] usuarios = new Player[100];
    
    // Funciones booleanas para ACCESO de menu
    public boolean noMenu() {
        return false;
    }
    
    public boolean siMenu() {
        return true;
    }
    
    // Funcion VERIFICAR/BUSCAR si el usuario existe
    public Player buscarUsuario(String username) {
        for(Player jugador: usuarios) {
            if (jugador != null && jugador.getUsername().equals(username)) {
                noMenu();
                return jugador;
            }
        }
        return null;
    }
    
    // Funcion para INICIAR SESION
    public boolean iniciarSesion(String user, String pass) {
        Player vUser = buscarUsuario(user);
        if (vUser.getUsername().equalsIgnoreCase(user)) {
            if (vUser.getPassword().equals(pass)) {
                return siMenu();
            }
        } else if (vUser.getUsername() == null) {
            System.out.println("Usuario/Contraseña Incorrecta!");
        }
        return noMenu();
    }
    
    // Funcion para CREAR un nuevo jugador
    public boolean crearUsuario(String username, String password) {
//        System.out.println(buscarUsuario(username));
        if(buscarUsuario(username) == null) {
           for(int i = 0; i < usuarios.length; i++) {
                if (usuarios[i] == null) {
                    usuarios[i] = new Player(username, password); 
                    System.out.println("\tUsuario correctamente creado! ®");
                    return true;
                }
            } 
        }
        return false;
    }
}
