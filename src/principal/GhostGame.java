package principal;

/** @author Joe Corrales */
import java.util.Scanner;

public class GhostGame {
    Scanner input = new Scanner(System.in);
    //Coleccion de usuarios
    private Player[] usuarios = new Player[100];
    Player mi = new Player();
    //Colecciones de los fantasmas de cada Jugador
    Ghost[] fantasmasJ1; Ghost[] fantasmasJ2;
    //La matriz del tipo Ghost de 6x6
    Ghost[][] tablero = new Ghost[6][6];
    //Arreglos express que dan las posiciones disponibles que tiene cada Jugador para empezar el juego
    String[] posJ1 = new String[8]; String[] posJ2 = new String[8];
    
    //Variables/Atributos globales para ejecutar funciones
    //f: fila Y c: columna
    int codigo, cantidad, f, f2, c, c2, cantFantasmas, opcion,
            fBuenosJdr1, fBuenosJdr2, fMalosJdr1, fMalosJdr2;
    String username, password, nomJdr1, nomJdr2, posVacia = "💠";
    Player Jugador1; //Variable de uso temporal para mantener al Usuario activo
    Player Jugador2; //Variable para conseguir el segundo Jugador
    boolean turno, modoJuego;
    
    //METODOS 
    // Funcion para ejecutar el juego
    public void jugarGhost() {
        turno = true;
        inicioPartida();
        crearTDFantasmas();
        modoJuego();
        
        while(hayGanador()) { // si hay ganador entonces se detiene
            mostrarTurno(turno);
            if (turno) coorSeleccion(true, Jugador1); //Empieza el jugador 1
            else coorSeleccion(false, Jugador2); //Luego el jugador 2
            turno = !turno; // cambio de turno
        }
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
        if (verificarUsuario(username)) { // si el OBJETO jugador no esta vacio
            Jugador1 = buscarUsuario(username);
            nomJdr1 = Jugador1.getUsername();
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
                    Jugador1 = buscarUsuario(username); nomJdr1 = Jugador1.getUsername();
                    codigo = i; // guarda la posicion en la coleccion... se usa para la funcion de borrar
                    System.out.println("     Usuario correctamente creado! ®");
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
    public boolean eliminarCuenta() {
        System.out.print("\nADVERTENCIA: Borrara su cuenta, esto es irreversible. \nEsta seguro? [S/N]: ");
        char resp = input.next().toLowerCase().charAt(0);
        if (resp == 's') {
            usuarios[codigo] = null; // si da como respuesta SI, se borra la cuenta 
            System.out.println("HA BORRADO SU CUENTA");
            return true;
        }
        return false;
    }
    
    //Funcion para definir la dificultad
    public void configDificultad(int opcion) {
        switch(opcion) {
            case 1: //NORMAL
                Jugador1.setDificultad(8);
                System.out.println("\n\tMODO NORMAL: ON");
                break;
            case 2: //EXPERT
                Jugador1.setDificultad(4);
                System.out.println("\n\tMODO EXPERT: ON");
                break;
            case 3: //GENIUS
                Jugador1.setDificultad(2);
                System.out.println("\n\tMODO GENIUS: ON");
                break;
            default: 
                System.out.println("Dificultad no valida");
        } 
    }
    
    //Funcion para definir el modo de juego
    public void configModoDeJuego() {
        this.opcion = myNextInt("\n\t╠╬══╣MODO DE JUEGO╠══╬╣"
                                + "\n 1) Aleatorio"
                                + "\n 2) Manual"
                                + "\nOPCION #");
        modoJuego = (opcion == 2); //Operador ternario para selecionar el modo de juego
    }
    
    //Funcion para dar acceso al Juego
    public void accesoAlJuego() {
        username = myNextString("\n╠╬══╣Ingrese el usuario del Jugador 2\n: ");
        Jugador2 = buscarUsuario(username);
        if (verificarUsuario(username) && !(Jugador1.getUsername().equals(username))) {
            nomJdr2 = Jugador2.getUsername();
            System.out.println();
            jugarGhost();
        } else if (!verificarUsuario(username)) {
            System.out.println("Usuario no registrado");
        } else System.out.println("No puede jugar contra usted mismo.");
    }
    
    //Funcion con varios procedimientos para iniciar una partida
    public void inicioPartida() {
        rellenarPosiciones(); //Rellena las posiciones de todos los jugadores
        matrizInicial(tablero); //Rellena la matriz
        mostrarMatriz(tablero); //Muestra la matriz 
    }
    
    //Funcion para mandar a crear todos los fanasmas
    public void crearTDFantasmas() {
        cantFantasmas = Jugador1.getDificultad();
        fabricaFantasmas(cantFantasmas);
    }
    
    //Funcion para establecer el modo de juego
    public void modoJuego() {
        if (modoJuego) modoManual("\n     MODO DE JUEGO: MANUAL");
        else modoAleatorio("\n     MODO DE JUEGO: ALEATORIO");
    }
    
    //Funcion para seleccionar un fantasma
    public void coorSeleccion(boolean turno, Player Jugador) {
        f = myNextInt("    ╠╬══╣SELECCIONE UN FANTASMA╠══╬╣\nFila: ");
        c = myNextInt("Columna: ");
        // Si la coordenada esta en el rango y el fantasma es del mismo jugador
        if (!((f == -1)||(c == -1))) {
            if (coordenada(f, c) && validUserToken(tablero, f, c, Jugador)) {
            coorMovimiento(f, c, turno, Jugador);
            } else {
                System.out.println("Ingresa otra coordenada");
                coorSeleccion(turno, Jugador);
            }
        } 
    }
    
    //Funcion para validar si la coordenada ingresada esta en el rango
    public boolean coordenada(int f, int c) {
        return (f <= 6 && f >= 1) && (c <= 6 && c >= 1);
    }
    
    //Funcion para saber si la posicion en la matriz tiene un fantasma del mismo jugador
    public boolean validUserToken(Ghost[][] matriz, int f, int c, Player Jugador) {
        return matriz[(f-1)][(c-1)].getJugador().equals(Jugador.getUsername());
    }
    
    //Funcion para mover un fantasma
    public void coorMovimiento(int f, int c, boolean turno, Player Jdr1) {
        pMovimientos(f, c, Jdr1); // se le muestran los movimientos disponibles // BETA
        f2 = myNextInt("    ╠╬══╣MUEVA UN FANTASMA╠══╬╣\nFila: ");
        c2 = myNextInt("Columna: ");
        // Si la coordenada de movimiento es valida 
        if (validMove(f, c, f2, c2)) {
            // Y la posicion a mover no tiene un fantasma del mismo jugador
            if (!validUserToken(tablero, f2, c2, Jdr1)) {
                alerta(tablero, f2, c2);    
                this.turno = turno; //Si se hace bien el movimiento se cambia de turno
                tablero[(f2-1)][(c2-1)] = tablero[(f-1)][(c-1)]; //se pone el fantasma en la posicion pedida
                tablero[(f-1)][(c-1)] = new Ghost(posVacia, "vacio", "ninguno"); //se remplaza por un espacio vario
                mostrarMatriz(tablero);
            } else errOpcion("\n[!] Tienes una ficha ahi\n", Jdr1);// si no se muestra 
        } else errOpcion("\n[!] No te puedes mover ahi\n", Jdr1);
    }
    
    //Funcion para mostrar las opciones si se ingresa mal una coordenada
    public void errOpcion(String mensaje, Player Jdr) {
        boolean repetir = true;
        while(repetir) {
            opcion = myNextInt(mensaje+"\nOPCIONES:"
                    + "\n1) Ingresar otra coordenada de movimiento"
                    + "\n2) Seleccionar otro fantasma\nOpcion #");
            switch (opcion) {
                case 1:
                    repetir = false;
                    mostrarMatriz(tablero);
                    coorMovimiento(f, c, turno, Jdr);
                    break;
                case 2:
                    repetir = false;
                    mostrarMatriz(tablero);
                    coorSeleccion(turno, Jdr);
                    break;
                default:
                    System.out.println("No disponible");
            }
        }
    }
    
    //Funcion para dar una alerta de que un jugador se comio una ficha del otro
    public void alerta(Ghost[][] matriz, int f2, int c2) {
        String estado = matriz[(f2-1)][(c2-1)].getEstado();
        String Jdr = matriz[(f2-1)][(c2-1)].getJugador();
        if (!matriz[(f2-1)][(c2-1)].getJugador().equals("ninguno")) {
            System.out.println("\n[!][!][!][!][!][!][!][!][!]][!][!][!][!][!]"
                   +"\n »»Te comiste una ficha ["+estado+"] de {"+Jdr+"}««");
        }
    }
    
    //Funcion para saber si un Jugador se retira
    public boolean retiro(int f, int c) {
        if(((f == -1)||(c == -1))) {
            System.out.print("\nTe retiraras de la partida \nEstas seguro? [S/N]: ");
            char resp = input.next().toLowerCase().charAt(0);
            if (resp == 's') {
                if (turno) {
                    System.out.println("\nGanador: "+nomJdr1+"!!!\nPor que "+nomJdr2+" se retiro");
                    mi.reportes.add("\nGanador: "+nomJdr1+", Por que "+nomJdr2+" se retiro");
                    System.out.println("+3 Pts");
                    Jugador1.set3Pts();
                    return true;
                } else {
                    System.out.println("\nGanador: "+nomJdr2+"!!!\nPor que "+nomJdr1+" se retiro");
                    mi.reportes.add("\nGanador: "+nomJdr2+", Por que "+nomJdr1+" se retiro");
                    System.out.println("+3 Pts");
                    Jugador2.set3Pts();
                    return true;
                }
            } 
        }
        return false;
    }
    
    //Funcion para indicar si un jugador se comieron TODOS los fantasma BUENOS del otro
    public boolean winSituacionUno() {
        if ((fBuenosJdr1 != 0)&&(fBuenosJdr2 == 0)) { // ganador Jugador 1
            System.out.println("\nGanador : "+nomJdr1+"!!!\nPor que se comio todas las Punks Buenos de "+nomJdr2);
            mi.reportes.add("Ganador : "+nomJdr1+", Por que se comio todas las Punks Buenos de "+nomJdr2);            
            System.out.println("+3 Pts");
            Jugador1.set3Pts();
            return true;
        } else if ((fBuenosJdr2 != 0)&&(fBuenosJdr1 == 0)) { // ganador Jugador 2
            System.out.println("\nGanador : "+nomJdr2+"!!!\nPor que se comio todos los Aliens Buenos de "+nomJdr1);
            mi.reportes.add("Ganador : "+nomJdr2+", Por que se comio todos los Aliens Buenos de "+nomJdr1);
            System.out.println("+3 Pts");
            Jugador2.set3Pts();
            return true;
        }
        return false;
    }
    
    //Funcion para indicar si un jugador le comieron TODOS los fantasma MALOS
    public boolean winSituacionDos() {
        if ((fMalosJdr1 == 0)&&(fMalosJdr2 != 0)) {
            System.out.println("\nGanador: "+nomJdr1+"!!!\nPor que "+nomJdr2 +" le comio todos los Aliens Malos");
            mi.reportes.add("Ganador: "+nomJdr1+", Por que "+nomJdr2+" le comio todos los Aliens Malos");
            System.out.println("+3 Pts");
            Jugador1.set3Pts();
            return true; 
        } else if ((fMalosJdr2 == 0)&&(fMalosJdr2 != 0)) {
            System.out.println("\nGanador: "+nomJdr2+"!!!\nPor que "+nomJdr1+" le comio todos los Punks Malos");
            mi.reportes.add("Ganador: "+nomJdr2+", Por que "+nomJdr1+" le comio todos los Aliens Malos");
            System.out.println("+3 Pts");
            Jugador2.set3Pts();
            return true;
        }
        return false;
    }
    
    //Funcion para indicar si un fantasma bueno cruzo por el lado contrario del castillo
    public boolean winSituacionTres() {
        if (winStJdr(0, 0, 0, 5, nomJdr1)) {
            System.out.println("\nGanador: "+ nomJdr1+"!!!\nlogro sacar un Alien Bueno por el lado contrario");
            mi.reportes.add("Ganador: "+nomJdr2+", Por que "+nomJdr1+" le comio todos los Aliens Malos");
            System.out.println("+3 Pts");
            Jugador1.set3Pts();
            return true;
        } else if (winStJdr(5, 0, 5, 5, nomJdr2)) {
            System.out.println("\nGanador: "+ nomJdr2+"!!!\nlogro sacar un Punk Bueno por el lado contrario");
            mi.reportes.add("Ganador: "+ nomJdr2+"!!!\nlogro sacar un Punk Bueno por el lado contrario");
            System.out.println("+3 Pts");
            Jugador2.set3Pts();
            return true;
        }
        return false;
    }
    
    //Funcion para saber si un fantasma bueno cruza por el lado contrario
    public boolean winStJdr(int f1, int c1, int f2, int c2, String Jdr) {
        if (tablero[f1][c1].getEstado().equals("Buena")&&tablero[f1][c1].getJugador().equals(Jdr)) return true;
        else if (tablero[f2][c2].getEstado().equals("Buena")&&tablero[f2][c2].getJugador().equals(Jdr)) return true;
        return false;
    }
    
    //Funcion que se ejecuta cada turno para saber si hay un ganador
    public boolean hayGanador() { 
        if (retiro(f, c)) return false; //si se retira un jugador
        else if (winSituacionUno()) return false; //Si hay un ganador entonces se para el ciclo de turnos
        else if (winSituacionDos()) return false;
        else if (winSituacionTres()) return false;
        return true; // si no, sigue normal el flujo
    }
    
    //Funcion para mostrar los movimientos posibles que tiene su ficha
    //HACER QUE MUESTRE SOLO DONDE PUEDA MOVER <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    public void pMovimientos(int f, int c, Player Jugador) {
        //Posibles Filas//Posibles Columnas
        int pF1 = f-1; int pC1 = c-1;
        int pF2 = f+1; int pC2 = c+1;        
        System.out.println("     ["+pF1+","+c+"]\n"+"       🢁\n"
                + "["+f+","+pC1+"]🢀  🢂["+f+","+pC2+"]\n"
                + "       🢃\n     ["+pF2+","+c+"]\n");
    }
    
    //Funcion para que la coordenada de movimiento sea solo una casilla y no en diagonal
    public boolean validMove(int f, int c, int f2, int c2) {
        if (((f2 == f-1)||(f2 == f+1))&&(c == c2)) return true; // se mueve entre columnas    
        else if ((f == f2)&&((c2 == c-1)||(c2 == c+1))) return true;  // se mueve entre filas
        else if (f == f2 && c == c2) return false; // no permite moverse al mismo lugar  
        else return false; // no permite movimientos en diagonal o mas de una casilla
    }
    
    //Funcion para mostrar el turno actual
    public void mostrarTurno(boolean turno){
        if (turno) System.out.println("\n\t    TURNO DE "+ nomJdr1.toUpperCase());
        else System.out.println("\n\t    TURNO DE "+ nomJdr2.toUpperCase());
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
        for (int fi = 0; fi < matriz.length; fi++) {
            for (int co = 0; co < matriz.length; co++) 
                matriz[fi][co] = new Ghost(posVacia, "vacio", "ninguno"); //👻
        }
    }
    
    // Funcion para mostrar el tablero actualizado... cada ciclo itera y actualiza el tablero...
    public void mostrarMatriz(Ghost[][] matriz) {
        System.out.println();
        for (int fi = 0; fi < matriz.length; fi++) {
            for (int co = 0; co < matriz.length; co++) 
                System.out.print("  "+ matriz[fi][co].getImagen()+"  ");
            System.out.println("\n");
        }
        imprimirActual();
    }
    
    //Funcion para mostrar los fantasmas actuales en la partida
    public void fantasmasActuales(Ghost[][] matriz) {
        for (int fi = 0; fi < matriz.length; fi++) {
            for (int co = 0; co < matriz.length; co++) {
                if (matriz[fi][co].getEstado().equals("Buena") && matriz[fi][co].getJugador().equals(nomJdr1)) fBuenosJdr1++;
                else if (matriz[fi][co].getEstado().equals("Buena") && matriz[fi][co].getJugador().equals(nomJdr2)) fBuenosJdr2++;
                else if(matriz[fi][co].getEstado().equals("Mala") && matriz[fi][co].getJugador().equals(nomJdr1)) fMalosJdr1++;
                else if (matriz[fi][co].getEstado().equals("Mala") && matriz[fi][co].getJugador().equals(nomJdr2)) fMalosJdr2++;
            }
        }
    }
    
    //Funcion para saber como va la partida
    public void imprimirActual() {
        fBuenosJdr1 = 0; fBuenosJdr2 = 0; fMalosJdr1 = 0; fMalosJdr2 = 0;
        fantasmasActuales(tablero);
        System.out.println("Jugador 1: "+ nomJdr1 +"\t\tJugador 2: "+ nomJdr2 +"\n"
                +"Aliens Buenos: "+ fBuenosJdr1 +"\tPunks Buenos: "+ fBuenosJdr2
                +"\nAliens Malos: "+ fMalosJdr1 +"\t\tPunks Malos: "+ fMalosJdr2);
    }
    
    //Funcion para crear los fantasmas de cada Jugador
    public void fabricaFantasmas(int cantFantasmas) {
        fantasmasJ1 = new Ghost[cantFantasmas]; //Asignacion de arreglos
        fantasmasJ2 = new Ghost[cantFantasmas];
        
        for (int i = 0; i < cantFantasmas; i++) {
            if (i % 2 == 0) { // Si la i no da residuo se crean fantasmas buenos
                fantasmasJ1[i] = new Ghost("👽", "Buena", Jugador1.getUsername());
                fantasmasJ2[i] = new Ghost("🎃", "Buena", Jugador2.getUsername());
            } else { //si no, fantasmas malos
                fantasmasJ1[i] = new Ghost("👽", "Mala", Jugador1.getUsername());
                fantasmasJ2[i] = new Ghost("🎃", "Mala", Jugador2.getUsername());
            }
        }
    }
    
    //PARTE DEL MODO MANUAL
    //Funcion de el modo de juego MANUAL
    public void modoManual(String mensaje) {
        System.out.println(mensaje);
        ponerFantasmas(tablero);
    }
    
    //Funcion para poner los fantasmas
    public void ponerFantasmas(Ghost[][] matriz){
        System.out.println("\nUSUARIO: ["+nomJdr1+"] pondra sus fantasmas");
        insertarEn(matriz, fantasmasJ1, posJ1);
        System.out.println("\nUSUARIO: ["+nomJdr2+"] pondra sus fantasmas");
        insertarEn(matriz, fantasmasJ2, posJ2);
    }
    
    //Funcion para insertar los fantamas en alguna casilla disponibles
    public void insertarEn(Ghost[][] matriz, Ghost[] fantasmasJdr, String[] posicionesJdr) {
        for (int i = 0; i < fantasmasJdr.length; i++) { // Para la cantidad de fantasmas que tenga el arreglo
            listarPosiciones(posicionesJdr); // Se estara listando las posiciones disponibles
            if (i % 2 == 0) System.out.println("\n     ╠╬══╣COLOCAR FICHA BUENO╠══╬╣"); // se intercala para poner un fantasma bueno
            else System.out.println("\n     ╠╬══╣COLOCAR FICHA MALA╠══╬╣"); // y luego malo
            this.f = myNextInt("Seleccionar Fila: ");
            this.c = myNextInt("Seleccionar Columna: ");
            System.out.println();
            union(f, c);
            if (buscarPosicion(union(f, c), posicionesJdr) != -1) { // si esta la posicion disponible
                if (matriz[(f-1)][(c-1)].getImagen().equals(posVacia)) { // verifica que este vacia la coordenada
                    matriz[(f-1)][(c-1)] = fantasmasJdr[i]; // pone el fantasma
                    posicionesJdr[buscarPosicion(union(f, c), posicionesJdr)] = null; // borra la coordenada usada
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
    public void modoAleatorio(String mensaje) {
        System.out.println(mensaje);
        ponerRandom(tablero);
        mostrarMatriz(tablero);
    }
    
    public void ponerRandom(Ghost[][] matriz) {
        //POSICIONES RANDOM JUGADOR 1
        insertarRandom(matriz, fantasmasJ1, posJ1, 5, 6, 1, 5);
        //POSICIONES RANDOM JUGADOR 2
        insertarRandom(matriz, fantasmasJ2, posJ2, 1, 2, 1, 5);
    }

    //Funcion para que la computadora ponga las fichas de manera random
    public void insertarRandom(Ghost[][] matriz, Ghost[] fantasmasJdr, String[] posicionesJdr, int minFi, int maxFi, int minCo, int maxCo) {
        for (int i = 0; i < fantasmasJdr.length; i++) {    
            f = (int) (Math.random() * (maxFi+1));
            while(f != minFi && f != maxFi) 
                f = (int) (Math.random() * (maxFi+1));

            c = (int) (Math.random() * (maxCo+1));
            while(!(c != minCo && c <= maxCo)) 
                c = (int) (Math.random() * (maxCo+1));
            
            union(f, c);
            if (buscarPosicion(union(f, c), posicionesJdr) != -1) {
                if (matriz[(f-1)][(c-1)].getImagen().equals(posVacia)) {
                    matriz[(f-1)][(c-1)] = fantasmasJdr[i];
                    posicionesJdr[buscarPosicion(union(f, c), posicionesJdr)] = null;
                } 
            } else i--;
        }
    }
    
    //Funcion para parsear numeros a cadenas, se usa para las posiciones disponibles
    public String union(int f, int c) {
        String Fila = String.valueOf(f); // se parsean las fs y las cs 
        String Columna = String.valueOf(c); // para verificar si la union de esos dos digitos
        String union = "["+Fila+"]{"+Columna+"}"; // se encuentra dispoonible
        return union;
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
    
    //Funcion para listar las posiciones disponibles de cada Jugador
    public void listarPosiciones(String[] posicionesJdr) {
        System.out.println("\nPOSICIONES DISPONIBLES: \nEjemplo para poner un fantasma: ");
        System.out.println("'[Filas]: [5]{2} :{Columnas}'\n");
        for(String pos: posicionesJdr) {
            if (pos != null) {
                System.out.println("\t» "+pos);
            }
        }
    }
    
    //Funcion para imprimir los reportes
    public void imprimirReportes() {
        System.out.println("\n     MIS ULTIMOS DIEZ JUEGOS");
        mi.ultimosJuegos(nomJdr1);
    }
    
    //Funcion para el ranking
    public void rankingJdrs() {
        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null) {
                System.out.println((i+1)+". "+ usuarios[i].getScore() +" pts "+ usuarios[i].getUsername());
            }
        }
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
        int numero = input.nextInt();
        return numero;
    }
}
