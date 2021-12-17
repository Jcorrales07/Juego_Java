package uimenu;

/** @author Joe Corrales */
import java.util.Scanner;
import principal.GhostGame;

public class Menu {
    //Instancias de objetos
    Scanner input = new Scanner(System.in);
    GhostGame func = new GhostGame();

    //Variables
    int opcion;
    String username;
    String password;
    boolean repetir = true;

    
    // Funcion que muestra el MENU DE INICIO
    public void menuInicio() {
        System.out.println("\n\t     ╠╬══╣BIENVENIDO A GHOSTGAME╠══╬╣");
        do {
            opcion = func.myNextInt("\n\t╠╬══╣MENU DE INICIO╠══╬╣"
                + "\n  1) Log in"
                + "\n  2) Registrar Jugador"
                + "\n  0) Salir"
                + "\nOPCION #"); 
            
            switch (opcion) {
                case 1:
                    System.out.println("\n\t╠╬══╣INICIAR SESIÓN╠══╬╣");
                    username = func.myNextString("\n╠╬══╣Ingrese su usuario\n: ");
                    // Si se encuentra el usuario se da acceso
                    if (func.iniciarSesion(username)) 
                        menuPrincipal();
                    break;
                    
                case 2:
                    System.out.println("\n\t╠╬══╣REGISTRAR USUARIO╠══╬╣");
                    username = func.myNextString("\n╠╬══╣Ingrese su usuario\n: ");
                    
                    // Si el usuario es unico (hecho correctamente)... se da acceso
                    if (func.crearUsuario(username))
                        menuPrincipal();
                    // de otro modo le da un mensaje de error en la funcion crearUsuario
                    break;
                    
                case 0:
                    System.out.println("\nGracias por jugar!!"
                            + "\nHA TERMINADO EL PROGRAMA...");
                    break;
                    
                default:
                    System.out.println("\nOPCION INVALIDA!");
            } 
        } while (opcion != 0);
    }
    
    // Funcion que muestra el MENU PRINCIPAL
    public void menuPrincipal() {
        System.out.println("\n\t»» HA INICIADO SESION ««");
        do {
            opcion = func.myNextInt("\n\t╠╬══╣MENU PRINCIPAL╠══╬╣"
                + "\n  1) Jugar Ghosts"
                + "\n  2) Configuracion"
                + "\n  3) Reportes"
                + "\n  4) Mi Perfil"                    
                + "\n  0) Cerrar Sesion"
                + "\nOPCION #"); 
        
            switch (opcion) {
                case 1://JUGAR GHOSTS
                    func.accesoAlJuego();
                    break;
                case 2://CONFIGURACION
                    do {
                        opcion = func.myNextInt("\n\t╠╬══╣CONFIGURACIONES╠══╬╣"
                            + "\n 1) Dificultad"
                            + "\n 2) Modo de Juego"
                            + "\n 3) Regresar al Menu Principal"
                            + "\nOPCION #");

                        switch (opcion) {
                            case 1://DIFICULTAD
                                opcion = func.myNextInt("\n\t╠╬══╣DIFICULTAD╠══╬╣"
                                    + "\n 1) Normal (8 fantasmas)"
                                    + "\n 2) Expert (4 fantasmas)"
                                    + "\n 3) Genius (2 fantasmas)"
                                    + "\nOPCION #");
                                func.configDificultad(opcion);
                                
                                break;
                            case 2:// MODO DE JUEGO 
                                func.configModoDeJuego();
                                break;
                            case 3:
                                repetir = false;
                                break;
                            default:
                                System.out.println("\nOPCION INVALIDA!");
                        } 
                    } while (repetir);
                    break;
                case 3://REPORTES
                    do {
                        opcion = func.myNextInt("\n\t╠╬══╣REPORTES╠══╬╣"
                            + "\n 1) Descripcion de mis ultimos 10 juegos"
                            + "\n 2) Ranking de Jugadores"
                            + "\n 3) Regresar al Menu Principal"
                            + "\nOPCION #");
                    
                        switch (opcion) {
                            case 1://ULTIMOS JUEGOS
                                func.imprimirReportes();
                                break;
                            case 2://RANKING
                                func.rankingJdrs();
                                break;
                            case 3:
                                repetir = false;
                                break;
                            default:
                                System.out.println("\nOPCION INVALIDA!");
                        }
                    } while (repetir);
                    break;
                case 4://MI PERFIL
                    do {
                        opcion = func.myNextInt("\n\t╠╬══╣MI PERFIL╠══╬╣"
                                + "\n 1) Ver mis Datos"
                                + "\n 2) Cambiar Contraseña"
                                + "\n 3) Eliminar mi cuenta"
                                + "\n 4) Regresar al Menu Principal"
                                + "\nOPCION #");

                        switch (opcion) {
                            case 1://VER DATOS
                                func.buscarUsuario(username).mostrarDatos();
                                break;
                                
                            case 2://CAMBIAR CONTRASEÑA
                                password = func.myNextString("\nIntroduzca su contraseña actual\n: ");
                                func.cambiarContra(password);                             
                                break;
                                
                            case 3://ELIMINAR CUENTA
                                func.eliminarCuenta();
                                menuInicio();
                                repetir = false;
                                break;
                                
                            case 4:
                                repetir = false;
                                break;

                            default:
                                System.out.println("\nOPCION INVALIDA!");
                        } 
                    } while (repetir);
                    break;
                case 0:
                    System.out.println("\n\t»» HA CERRADO SESION ««");
                    menuInicio();
                    break;
                default:
                    System.out.println("\nOPCION INVALIDA!");       
            }
        } while (opcion != 0);
    }
}
