package principal;

import java.util.Scanner;

public class GhostGame {
    Scanner input = new Scanner(System.in);
    private Player[] usuarios = new Player[100];
    
    //Variables/Atributos globales para ejecutar funciones
    int codigo;
    String password;
    Player validUser; //Variable de uso temporal
        
    //METODOS 
    
    // Funcion VERIFICAR/BUSCAR si el usuario existe
    public Player buscarUsuario(String username) { // Se ingresa el usuario
        for(Player jugador: usuarios) { // se recorre el arreglo de jugadores
            // si el jugador esta lleno y el USUARIO es el que se busca...
            if (jugador != null && jugador.getUsername().equals(username)) {
                return jugador; // se retorna el jugador deseado
            }
        }
        return null; // de otra forma si no se encuentra se retorna nulo
    }
    
    //NOTA IMPORTANTE LA VARIABLE validUser CREO QUE NO ES NECESARIA, REVISALA BIEN EN LA FUNCION 
    //iniciarSesion Y crearUsuario, SE PUEDE OPTIMIZAR. PERO POR AHORA A MIMIR ZZzZZZzzZZzzz Jejeje
    // Funcion para INICIAR SESION
    public boolean iniciarSesion(String username) { // se ingresa el usuario
        validUser = buscarUsuario(username); // se almacena el OBJETO jugador temporalmente en esta variable
        if (validUser != null) { // si el OBJETO jugador no esta vacio
            System.out.print("╠╬══╣Ingrese su contraseña\n: "); // se le pide la contraseña
            password = input.next();
            // si la contraseña es igual al que tiene el objeto jugador...
            if (validUser.getPassword().equals(password)) {
                return true; // ...se le da acceso
            } else System.out.println("Contraseña Incorrecta!"); // si no, se le da un mensaje
        } else { // si el usuario no coincide se le da un mensaje
            System.out.println("Usuario Incorrecto/No registrado!"); 
        }
        return false; // si no coincide el usuario o la contraseña no se le da acceso
    }
    
    // Funcion para CREAR un nuevo jugador
    public boolean crearUsuario(String username) { // Se ingresa el usuario que se quiere registrar
        if(buscarUsuario(username) == null) { 
            System.out.println("Usuario disponible!");
             for(int i = 0; i < usuarios.length; i++) {
                if (usuarios[i] == null) {
                    System.out.print("╠╬══╣Ingrese su contraseña\n: ");
                    password = input.next();
                    usuarios[i] = new Player(username, password);
                    validUser = buscarUsuario(username);
                    System.out.println("\tUsuario correctamente creado! ®");
                    return true;
                }
            } 
        } else System.out.println("Usuario no disponible!");
        return false;
    }
    
    public void cambiarContra(String password) {
        if (password.equals(validUser.getPassword())) {
            System.out.print("Ingrese su nueva contraseña\n: ");
            password = input.next();
            System.out.println("Ingrese denuevo su nueva contraseña");
            String validPassword = input.next();
            if (password.equals(validPassword)) {
                validUser.setPassword(password);
                System.out.println("Cambio de contraseña correctamente");
            } else {
                System.out.println("El cambio no se hizo, las contraseñas no coinciden");
            }
        } else System.out.println("Contraseña Incorrecta!");
    }
}
