package principal;

/**
 * @author Joe Corrales
 */
import java.util.Scanner;

public class GhostGame {
    Scanner input = new Scanner(System.in);
    private Player[] usuarios = new Player[100];
    String[][] tablero = new String[6][6];
    
    //Variables/Atributos globales para ejecutar funciones
    int codigo;
    String password;
    String posVacia = "▄";
    Player validUser; //Variable de uso temporal
    boolean turno;
        
    //METODOS 
    
    // Funcion para ejecutar el juego
    public void jugarGhost() {
        matrizInicial(tablero, posVacia);
        
//        while()
        
        
        // Va  a estar switcheando turno
        turno = !turno;
        
        mostrarMatriz(tablero); // este va a estar dentro de un while
    }
    
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
    
    // Funcion que verifica si el usuario existe, retorna true o false dependiendo.
    public boolean verificarUsuario(String username) {
        return buscarUsuario(username) != null;
    }
    
    // Funcion para INICIAR SESION
    public boolean iniciarSesion(String username) { // se ingresa el usuario
        validUser = buscarUsuario(username); // se almacena el OBJETO jugador temporalmente en esta variable
        if (verificarUsuario(username)) { // si el OBJETO jugador no esta vacio
            password = nextStringString("╠╬══╣Ingrese su contraseña\n: "); // se le pide la contraseña
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
                    password = nextStringString("╠╬══╣Ingrese su contraseña\n: ");
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
            password = nextStringString("Ingrese su nueva contraseña\n: ");
            String validPassword = nextStringString("Ingrese denuevo su nueva contraseña\n: ");
            if (password.equals(validPassword)) {
                validUser.setPassword(password);
                System.out.println("\nCAMBIO DE CONTRASEÑA CORRECTAMENTE");
            } else System.out.println("El cambio no se hizo, las contraseñas no coinciden");
        } else System.out.println("Contraseña Incorrecta!");
    }
    
    // Funcion para rellenar la matriz con "▄""
    public void matrizInicial(String[][] matriz, String posVacia) {
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz.length; columna++) {
                matriz[fila][columna] = posVacia; //👻
            }
        }
    }
    
    // Funcion para mostrar el tablero actualizado... cada ciclo itera y actualiza el tablero...
    public void mostrarMatriz(String[][] matriz) {
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz.length; columna++) {
                System.out.print("  "+ matriz[fila][columna] +"  ");
            }
            System.out.println();
            System.out.println();
        }
    } 
    
    // Funcion para pedir una cadena con un mensaje especifico
    public String nextStringString(String mensaje) {
        System.out.print(mensaje);
        String cadena = input.next();
        return cadena;
    }
    
}
