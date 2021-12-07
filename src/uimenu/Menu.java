package uimenu;

import java.util.Scanner;
import principal.GhostGame;

/**
 * @author Joe Corrales
 */
public class Menu {
    //Instancias de objetos
    Scanner input = new Scanner(System.in);
    GhostGame func = new GhostGame();

    //Variables
    byte opcion;
    String username;
    String password;
    boolean repetir = true;

    
    // Funcion que muestra el MENU DE INICIO
    public void menuInicio() {
        System.out.println("\n\t     ╠╬══╣BIENVENIDO A GHOSTGAME╠══╬╣");
        
        do {
            System.out.print("\n\t╠╬══╣MENU DE INICIO╠══╬╣"
                + "\n  1) Log in"
                + "\n  2) Registrar Jugador"
                + "\n  0) Salir"
                + "\nOPCION #");
            opcion = input.nextByte(); 
            
            switch (opcion) {
                case 1:
                    System.out.println("\n\t╠╬══╣INICIAR SESIÓN╠══╬╣");
                    
                    username = func.nextStringString("\n╠╬══╣Ingrese su usuario\n: ");
                    // Si se encuentra el usuario se da acceso
                    if (func.iniciarSesion(username)) {
                        menuPrincipal();
                    }
                    break;
                    
                case 2:
                    System.out.println("\n\t╠╬══╣REGISTRAR USUARIO╠══╬╣");
                    
                    username = func.nextStringString("\n╠╬══╣Ingrese su usuario\n: ");
                    
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
            System.out.print("\n\t╠╬══╣MENU PRINCIPAL╠══╬╣"
                + "\n  1) Jugar Ghosts"
                + "\n  2) Configuracion"
                + "\n  3) Reportes"
                + "\n  4) Mi Perfil"                    
                + "\n  0) Cerrar Sesion"
                + "\nOPCION #");
                opcion = input.nextByte(); 
        
            switch (opcion) {
                case 1://JUGAR GHOSTS
                    username = func.nextStringString("\n╠╬══╣Ingrese el usuario del Judagor 2\n: ");
                    if (func.verificarUsuario(username)) {
                        System.out.println();
                        func.jugarGhost();
                    }
                    break;
                case 2://CONFIGURACION
                    do {
                       System.out.print("\n\t╠╬══╣CONFIGURACIONES╠══╬╣"
                                + "\n 1) Dificultad"
                                + "\n 2) Modo de Juego"
                                + "\n 3) Regresar al Menu Principal"
                                + "\nOPCION #");
                        opcion = input.nextByte();

                        switch (opcion) {
                            case 1://DIFICULTAD
                                break;
                            case 2:// MODO DE JUEGO                                
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
                        System.out.print("\n\t╠╬══╣REPORTES╠══╬╣"
                            + "\n\t1) Descripcion de mis ultimos 10 juegos"
                            + "\n\t2) Ranking de Jugadores"
                            + "\n\t3) Regresar al Menu Principal"
                            + "\nOPCION #");
                        opcion = input.nextByte();
                    
                        switch (opcion) {
                            case 1://ULTIMOS JUEGOS
                                break;
                            case 2://RANKING
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
                       System.out.print("\n\t╠╬══╣MI PERFIL╠══╬╣"
                                + "\n 1) Ver mis Datos"
                                + "\n 2) Cambiar Contraseña"
                                + "\n 3) Eliminar mi cuenta"
                                + "\n 4) Regresar al Menu Principal"
                                + "\nOPCION #");
                        opcion = input.nextByte();

                        switch (opcion) {
                            case 1://VER DATOS
                                func.buscarUsuario(username).mostrarDatos();
                                break;
                                
                            case 2://CAMBIAR CONTRASEÑA
                                password = func.nextStringString("\nIntroduzca su contraseña actual\n: ");
                                func.cambiarContra(password);                             
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
