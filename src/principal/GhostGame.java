package principal;

/**
 * @author Joe Corrales
 */
import java.util.Scanner;

public class GhostGame {
    Scanner input = new Scanner(System.in);
    private Player[] usuarios = new Player[100];
    Ghost[] fantasmasJ1; Ghost[] fantasmasJ2;
    Ghost[][] tablero = new Ghost[6][6];
    String[] posicionesJ1 = {"[5]{2}","[5]{3}","[5]{4}","[5]{5}","[6]{2}","[6]{3}","[6]{4}","[6]{5}"};
    String[] posicionesJ2 = {"[1]{2}","[1]{3}","[1]{4}","[1]{5}","[2]{2}","[2]{3}","[2]{4}","[2]{5}"};
    
    //Variables/Atributos globales para ejecutar funciones
    int codigo, cantidad;
    int fila;
    int columna;
    String password;
    String posVacia = "▄";
    Player validUser; //Variable de uso temporal
    boolean turno;
    
    //METODOS 
    
    // Funcion para ejecutar el juego
    public void jugarGhost() {
        matrizInicial(tablero);
        mostrarMatriz(tablero);  // este va a estar dentro de un while
        
        int cantFantasmas = validUser.getDificultad();
        crearFantasmasJ1(cantFantasmas);
//        crearFantasmasJ2(cantFantasmas);
        ponerFantasmas(tablero); 
        
        
//        boolean temp = false;
//        while(!temp/*!finDelJuego(String[][] matriz, posVacia)*/) {
//            
//            int fila;
//            int columna;
//            
//            fila = myNextInt("Seleccione la fila a moverse\n: ");
//            columna = myNextInt("Seleccione la columna a moverse\n: ");
//            System.out.println(verificarPosicion(fila, columna));
//        }
        
        
        // Va  a estar switcheando turno
//        turno = !turno;
        
//        mostrarMatriz(tablero); // este va a estar dentro de un while
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
            password = myNextString("╠╬══╣Ingrese su contraseña\n: "); // se le pide la contraseña
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
                    password = myNextString("╠╬══╣Ingrese su contraseña\n: ");
                    usuarios[i] = new Player(username, password);
                    validUser = buscarUsuario(username);
                    codigo = i;
                    System.out.println("\tUsuario correctamente creado! ®");
                    return true;
                }
            } 
        } else System.out.println("Usuario no disponible!");
        return false;
    }
    
    public void cambiarContra(String password) {
        if (password.equals(validUser.getPassword())) {
            password = myNextString("Ingrese su nueva contraseña\n: ");
            String validPassword = myNextString("Ingrese denuevo su nueva contraseña\n: ");
            if (password.equals(validPassword)) {
                validUser.setPassword(password);
                System.out.println("\nCAMBIO DE CONTRASEÑA CORRECTAMENTE");
            } else System.out.println("El cambio no se hizo, las contraseñas no coinciden");
        } else System.out.println("Contraseña Incorrecta!");
    }
    
    public void eliminarCuenta() {
        System.out.print("\nADVERTENCIA: Borrara su cuenta, esto es irreversible. \nEsta seguro? [S/N]: ");
        char resp = input.next().toLowerCase().charAt(0);
        if (resp == 's') {
            usuarios[codigo] = null;
            System.out.println("HA BORRADO SU CUENTA");
        }
    }
    
    // Funcion para rellenar la matriz con "▄"
    public void matrizInicial(Ghost[][] matriz) {
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz.length; columna++) {
                matriz[fila][columna] = new Ghost("▄", "vacio", "ninguno"); //👻
            }
        }
    }
    
    // Funcion para mostrar el tablero actualizado... cada ciclo itera y actualiza el tablero...
    public void mostrarMatriz(Ghost[][] matriz) {
        for (int fila = 0; fila < matriz.length; fila++) {
            for (int columna = 0; columna < matriz.length; columna++) {
                System.out.print("  "+ matriz[fila][columna].getImagen() +"  ");
            }
            System.out.println();
            System.out.println();
        }
    } 
    
    public void ponerFantasmas(Ghost[][] matriz){
        System.out.println("Usuario: "+ validUser.getUsername() +" pondra sus fantasmas");
        insertarEn(matriz, fantasmasJ1, posicionesJ1);
        System.out.println("Usuario: Jugador 2 pondra sus fantasmas");
        insertarEn(matriz, fantasmasJ2, posicionesJ2);
    }
    
    public void insertarEn(Ghost[][] matriz, Ghost[] fantasmasJdr, String[] posicionesJdr) {
        for (int i = 0; i < fantasmasJdr.length; i++) {
            listarPosiciones(posicionesJdr);
            if (i % 2 == 0) System.out.println("\nColocar Fantasma BUENO");
            else System.out.println("Colocar Fantasma MALO");
            fila = myNextInt("Seleccionar Fila: ");
            columna = myNextInt("Seleccionar Columna: ");
            System.out.println();
            if (!matriz[(fila-1)][(columna-1)].getEstado().equals("▄")) {
                String Fila = String.valueOf(fila);
                String Columna = String.valueOf(columna);
                String union = "["+Fila+"]{"+Columna+"}";
                if(buscarPosicion(union, posicionesJdr) != -1) {
                    matriz[(fila-1)][(columna-1)] = fantasmasJdr[i];
                    posicionesJdr[buscarPosicion(union, posicionesJdr)] = null;
                } else {
                    i--;
                    System.out.println("Posicion No disponible");  
                }
                mostrarMatriz(matriz);
            }
        } 
    }
    
    public void crearFantasmasJ1(int cantFantasmas) {
        fantasmasJ1 = new Ghost[cantFantasmas];
        fantasmasJ2 = new Ghost[cantFantasmas];
        
        for (int i = 0; i < cantFantasmas; i++) {
            if (!(i % 2 == 0)) {
                fantasmasJ1[i] = new Ghost("👻", "Malo", validUser.getUsername());
                fantasmasJ2[i] = new Ghost("⛯", "Malo",/*por mientras*/ validUser.getUsername());
            }
            fantasmasJ1[i] = new Ghost("👻", "Bueno", validUser.getUsername());
            fantasmasJ2[i] = new Ghost("⛯", "Bueno",/*por mientras*/ validUser.getUsername());
        }
    }
    
    public void listarPosiciones(String [] posicionesJdr) {
        System.out.println("Posiciones disponibles: \nEjemplo para poner un fantasma: ");
        System.out.println("'[Filas]: [4] {1} :{Columnas}'");
        for(String pos: posicionesJdr) {
            if (pos != null) {
                System.out.println("        » "+pos);
            }
        }
    }
    
    public int buscarPosicion(String union, String[] posicionesJdr) {
        for (int i = 0; i < posicionesJdr.length; i++) {
            if (posicionesJdr[i] != null && posicionesJdr[i].equals(union)) {
                return i;
            }
        }
        return -1;
    }
    
    //Funcion para verificar la posicion //FUNCIONA, YA PROBE TODAS LAS COORDENADAS
    public boolean verificarPosicion(int fila, int columna) {
        if ((fila >= 0 && fila <= 1) || (fila >= 4 && fila <= 5)) {
            if (columna >= 1 && columna <= 4) {
                System.out.println("Coordenada aceptada ["+fila+"]["+columna+"]");
                return true;
            }
        }
        return false;
    }
    
    // Funcion para pedir una cadena con un mensaje especifico
    public String myNextString(String mensaje) {
        System.out.print(mensaje);
        String cadena = input.next();
        return cadena;
    }
    
    // Funcion para pedir un byte con un mensaje especifico
    public int myNextInt(String mensaje) {
        System.out.print(mensaje);
        int numero = input.nextByte();
        return numero;
    }
}
