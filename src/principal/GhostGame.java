package principal;

/**
 * @author Joe Corrales
 */
import java.util.Scanner;

public class GhostGame {
    Scanner input = new Scanner(System.in);
    //Coleccion de usuarios
    private Player[] usuarios = new Player[100];
    //Colecciones de los fantasmas de cada Jugador
    Ghost[] fantasmasJ1; Ghost[] fantasmasJ2;
    //La matriz del tipo Ghost de 6x6
    Ghost[][] tablero = new Ghost[6][6];
    //Arreglos express que dan las posiciones disponibles que tiene cada Jugador para empezar el juego
    String[] posJ1 = new String[8];
    String[] posJ2 = new String[8];
    
    //Variables/Atributos globales para ejecutar funciones
    int codigo, cantidad;
    int fila, columna;
    String username, password;
    String posVacia = "▄";
    Player Jugador1; //Variable de uso temporal para mantener al Usuario activo
    Player Jugador2; //Variable para conseguir el segundo Jugador
    boolean turno, modoJuego;
    
    //METODOS 
    
    // Funcion para ejecutar el juego
    public void jugarGhost() {
        rellenarPosiciones();
        matrizInicial(tablero);
        mostrarMatriz(tablero);  // este va a estar dentro de un while
        
        int cantFantasmas = Jugador1.getDificultad();
        crearFantasmas(cantFantasmas);
        if (modoJuego) {
            System.out.println("MODO DE JUEGO: MANUAL");
            modoManual();
        } else {
            System.out.println("MODO DE JUEGO: ALEATORIO");
            modoAleatorio();
        }
        
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
        Jugador2 = buscarUsuario(username);
        return buscarUsuario(username) != null;
    }
    
    // Funcion para INICIAR SESION
    public boolean iniciarSesion(String username) { // se ingresa el usuario
        Jugador1 = buscarUsuario(username); // se almacena el OBJETO jugador temporalmente en esta variable
        if (verificarUsuario(username)) { // si el OBJETO jugador no esta vacio
            password = myNextString("╠╬══╣Ingrese su contraseña\n: "); // se le pide la contraseña
            // si la contraseña es igual al que tiene el objeto jugador...
            if (Jugador1.getPassword().equals(password)) {
                return true; // ...se le da acceso
            } else System.out.println("Contraseña Incorrecta!"); // si no, se le da un mensaje
        } else { // si el usuario no coincide se le da un mensaje
            System.out.println("Usuario Incorrecto/No registrado!"); 
        }
        return false; // si no coincide el usuario o la contraseña no se le da acceso
    }
    
    // Funcion para CREAR un nuevo jugador
    public boolean crearUsuario(String username) { // Se ingresa el usuario que se quiere registrar
        if(buscarUsuario(username) == null) { //Se busca que ese usuario no exista
            System.out.println("Usuario disponible!");
            for(int i = 0; i < usuarios.length; i++) { // Empieza a buscar un espacio en la coleccion
                if (usuarios[i] == null) { 
                    password = myNextString("╠╬══╣Ingrese su contraseña\n: "); 
                    usuarios[i] = new Player(username, password); //Registra el nuevo usuario
                    Jugador1 = buscarUsuario(username); 
                    codigo = i; // guarda la posicion en la coleccion... se usa para la funcion de borrar
                    System.out.println("\tUsuario correctamente creado! ®");
                    return true;
                }
            } 
        } else System.out.println("Usuario no disponible!");
        return false;
    }
    
    //Funcion para cambiar la contraseña
    public void cambiarContra(String password) { // se pide la contraseña actual
        if (password.equals(Jugador1.getPassword())) {
            password = myNextString("Ingrese su nueva contraseña\n: "); // Se pide la nueva dos veces
            String validPassword = myNextString("Ingrese denuevo su nueva contraseña\n: ");
            if (password.equals(validPassword)) {
                Jugador1.setPassword(password); // Y si coinciden se establece como nueva contraseña
                System.out.println("\nCAMBIO DE CONTRASEÑA CORRECTAMENTE");
            } else System.out.println("El cambio no se hizo, las contraseñas no coinciden");
        } else System.out.println("Contraseña Incorrecta!");
    }
    
    //Funcion para eliminar una cuenta
    public void eliminarCuenta() {
        System.out.print("\nADVERTENCIA: Borrara su cuenta, esto es irreversible. \nEsta seguro? [S/N]: ");
        char resp = input.next().toLowerCase().charAt(0);
        if (resp == 's') {
            usuarios[codigo] = null; // si da como respuesta SI, se borra la cuenta 
            System.out.println("HA BORRADO SU CUENTA");
        }
    }
    
    //Funcion para definir la dificultad
    public void configDificultad(int opcion) {
        switch(opcion) {
            case 1: //NORMAL
                Jugador1.setDificultad(8);
                System.out.println("\tMODO NORMAL: ON");
                break;
            case 2: //EXPERT
                Jugador1.setDificultad(4);
                System.out.println("\tMODO EXPERT: ON");
                break;
            case 3: //GENIUS
                Jugador1.setDificultad(2);
                System.out.println("\tMODO GENIUS: ON");
                break;
            default: 
                System.out.println("Dificultad no valida");
        } 
    }
    
    //Funcion para definir el modo de juego
    public void configModoDeJuego() {
        int opcion = myNextInt("\n\t╠╬══╣MODO DE JUEGO╠══╬╣"
                                    + "\n 1) Aleatorio"
                                    + "\n 2) Manual"
                                    + "\nOPCION #");
        if (opcion == 2) 
            modoJuego = true; //Preguntar si cuando se pone el modo manual se puede empezar otra partida asi
        else if (opcion == 1)
            modoJuego = false;// o si siempre empezar en aleatorio
        else
            System.out.println("Modo de Juego no valido");
    }
    
    //Funcion para dar acceso al Juego
    public void accesoAlJuego() {
        username = myNextString("\n╠╬══╣Ingrese el usuario del Jugador 2\n: ");
        if (verificarUsuario(username) && !(Jugador1.getUsername().equals(username))) {
            System.out.println();
            jugarGhost();
        } else if (!verificarUsuario(username)) {
            System.out.println("Usuario no registrado");
        } else System.out.println("No puede jugar contra usted mismo.");
    }
    
    //Funcion para rellenar las posiciones
    public void rellenarPosiciones() {
        posJ1[0] = "[5]{2}"; posJ1[1] = "[5]{3}"; posJ1[2] = "[5]{4}"; posJ1[3] = "[5]{5}";
        posJ1[4] = "[6]{2}"; posJ1[5] = "[6]{3}"; posJ1[6] = "[6]{4}"; posJ1[7] = "[6]{5}";
        posJ2[0] = "[1]{2}"; posJ2[1] = "[1]{3}"; posJ2[2] = "[1]{4}"; posJ2[3] = "[1]{5}";
        posJ2[4] = "[2]{2}"; posJ2[5] = "[2]{3}"; posJ2[6] = "[2]{4}"; posJ2[7] = "[2]{5}";
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
    
    //Funcion para crear los fantasmas de cada Jugador
    public void crearFantasmas(int cantFantasmas) {
        //Se le asignan los espacios a los arreglos
        fantasmasJ1 = new Ghost[cantFantasmas];
        fantasmasJ2 = new Ghost[cantFantasmas];
        
        for (int i = 0; i < cantFantasmas; i++) {
            if (i % 2 == 0) { // Si la i no da residuo se crean fantasmas buenos
                fantasmasJ1[i] = new Ghost("👻", "Bueno", Jugador1.getUsername());
                fantasmasJ2[i] = new Ghost("⛯", "Bueno", Jugador2.getUsername());
            } else { //si no, fantasmas malos
                fantasmasJ1[i] = new Ghost("👻", "Malo", Jugador1.getUsername());
                fantasmasJ2[i] = new Ghost("⛯", "Malo", Jugador2.getUsername());
            }
        }
    }
    
    //PARTE DEL MODO MANUAL
    //Funcion de el modo de juego MANUAL
    public void modoManual() {
        ponerFantasmas(tablero);
    }
    
    //Funcion para poner los fantasmas
    public void ponerFantasmas(Ghost[][] matriz){
        System.out.println("Usuario: "+ Jugador1.getUsername() +" pondra sus fantasmas");
        insertarEn(matriz, fantasmasJ1, posJ1);
        System.out.println("Usuario: "+ Jugador2.getUsername() +" pondra sus fantasmas");
        insertarEn(matriz, fantasmasJ2, posJ2);
    }
    
    //Funcion para insertar los fantamas en alguna casilla disponibles
    public void insertarEn(Ghost[][] matriz, Ghost[] fantasmasJdr, String[] posicionesJdr) {
        for (int i = 0; i < fantasmasJdr.length; i++) { // Para la cantidad de fantasmas que tenga el arreglo
            listarPosiciones(posicionesJdr); // se estara listando las posiciones disponibles
            if (i % 2 == 0) System.out.println("\nColocar Fantasma BUENO"); // se intercala para poner un fantasma bueno
            else System.out.println("Colocar Fantasma MALO"); // y luego malo
            fila = myNextInt("Seleccionar Fila: ");
            columna = myNextInt("Seleccionar Columna: ");
            System.out.println();
            String Fila = String.valueOf(fila); // se parsean las filas y las columnas 
            String Columna = String.valueOf(columna); // para verificar si la union de esos dos digitos
            String union = "["+Fila+"]{"+Columna+"}"; // se encuentra si disponible
            if (buscarPosicion(union, posicionesJdr) != -1) { // si esta la posicion disponible
                if (matriz[(fila-1)][(columna-1)].getImagen().equals(posVacia)) { // verifica que este vacia la coordenada
                    matriz[(fila-1)][(columna-1)] = fantasmasJdr[i]; // pone el fantasma
                    posicionesJdr[buscarPosicion(union, posicionesJdr)] = null; // borra la coordenada usada
                } 
            } else {
                i--;
                System.out.println("   »»» POSICION NO DISPONIBLE «««");
            }
            mostrarMatriz(matriz);
        } 
    }
    
    //PARTE DEL MODO RANDOM
    //Funcion de el modo de juego ALEATORIO
    public void modoAleatorio() {
        ponerRandom(tablero);
    }
    
    public void ponerRandom(Ghost[][] matriz) {
        //POSICIONES RANDOM JUGADOR 1
        insertarRandom(matriz, fantasmasJ1, posJ1, 5, 6, 1, 5);
        //POSICIONES RANDOM JUGADOR 2
        insertarRandom(matriz, fantasmasJ2, posJ2, 1, 2, 1, 5);
    }
    
    public void insertarRandom(Ghost[][] matriz, Ghost[] fantasmasJdr, String[] posicionesJdr, int minFi, int maxFi, int minCo, int maxCo) {
        for (int i = 0; i < fantasmasJdr.length; i++) {    
            fila = (int) (Math.random() * (maxFi+1));
            while(fila != minFi && fila != maxFi) 
                fila = (int) (Math.random() * (maxFi+1));

            columna = (int) (Math.random() * (maxCo+1));
            while(!(columna != minCo && columna <= maxCo)) 
                columna = (int) (Math.random() * (maxCo+1));
            
            System.out.println();
            
            String Fila = String.valueOf(fila); // se parsean las filas y las columnas 
            String Columna = String.valueOf(columna); // para verificar si la union de esos dos digitos
            String union = "["+Fila+"]{"+Columna+"}"; // se encuentra dispoonible
            if (buscarPosicion(union, posicionesJdr) != -1) {
                if (matriz[(fila-1)][(columna-1)].getImagen().equals(posVacia)) {
                    matriz[(fila-1)][(columna-1)] = fantasmasJdr[i];
                    posicionesJdr[buscarPosicion(union, posicionesJdr)] = null;
                } 
            } else i--;
        }  
        
    }
    
    //Funcion para listar las posiciones disponibles de cada Jugador
    public void listarPosiciones(String[] posicionesJdr) {
        System.out.println("Posiciones disponibles: \nEjemplo para poner un fantasma: ");
        System.out.println("'[Filas]: [5]{2} :{Columnas}'");
        for(String pos: posicionesJdr) {
            if (pos != null) {
                System.out.println("\t» "+pos);
            }
        }
    }
    
    //Funcion temporal
    public void listarFantasmas(Ghost[] fantasmasJdr) {
        System.out.println("FANTASMAS");
        for(Ghost ghost: fantasmasJdr) {
            System.out.println(ghost.getEstado());
            System.out.println(ghost.getImagen());
            System.out.println(ghost.getJugador());
        }
    }
    
    //Funcion para buscar una posicion, que concuerde con la union de la seleccion del usuario
    public int buscarPosicion(String union, String[] posicionesJdr) {
        for (int i = 0; i < posicionesJdr.length; i++) {
            if (posicionesJdr[i] != null && posicionesJdr[i].equals(union)) {
                return i; // se regresa una posicion si se cumple
            }
        }
        return -1;
    }
    
    //Funcion temporal para verificar la posicion 
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
