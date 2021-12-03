package uimenu;

import java.util.Scanner;
import principal.GhostGame;

/**
 * @author Lenovo
 */
public class Menu {
    //Instancias de objetos
    Scanner input = new Scanner(System.in);
    GhostGame funcion = new GhostGame();
    
    //Variables
    byte opcion;
    String username;
    String password;
    
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
                    
                    System.out.print("\n╠╬══╣Ingrese su usuario\n: ");
                    username = input.next();
                    
                    System.out.print("╠╬══╣Ingrese su contraseña\n: ");
                    password = input.next();
                    // Si se encuentra el usuario se da acceso
                    if (funcion.iniciarSesion(username, password)) {
                        menuPrincipal();
                    }
                    break;
                    
                case 2:
                    System.out.println("\n\t╠╬══╣REGISTRAR USUARIO╠══╬╣");
                    
                    System.out.print("\n╠╬══╣Ingrese su usuario\n: ");
                    username = input.next();
                    
                    System.out.print("╠╬══╣Ingrese su contraseña\n: ");
                    password = input.next();
                    // Si el usuario es unico (hecho correctamente)... se da acceso
                    if (funcion.crearUsuario(username, password))
                        menuPrincipal();
                    // de otro modo le da un mensaje
                    else System.out.println("\nUsuario no disponible!");
                    break;
                    
                case 0:
                    System.out.println("\nGracias por jugar!!"
                            + "\nHA TERMINADO EL PROGRAMA...");
                    break;
                    
                default:
                    System.out.println("\nINGRESE UNA OPCION VALIDA!");
            } 
        } while (opcion != 0);
    }
    
    // Funcion que muestra el MENU PRINCIPAL
    public void menuPrincipal() {
        System.out.println("\n\t\t»» HA INICIADO SESION! ««");
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
                case 1:
                    //JUGAR GHOSTS
                    break;
                case 2:
                    //CONFIGURACION
                    System.out.print("\n\t╠╬══╣CONFIGURACIONES╠══╬╣"
                            + "\n 1) Dificultad"
                            + "\n 2) Cambiar contraseña"
                            + "\n 3) Eliminar mi cuenta"
                            + "\n 0) Regresar al Menu Principal"
                            + "\nOPCION #");
                    opcion = input.nextByte();
                    
                    switch (opcion) {
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 0:
                            break;
                            
                        default:
                            System.out.println("\nINGRESE UNA OPCION VALIDA!");
                    }
                    
                    break;
                case 3:
                    //REPORTES
                    break;
                case 4:
                    //MI PERFIL
                    break;
                case 0:
                    System.out.println("\n\t\t»» HA CERRADO SESION! ««");
                    menuInicio();
                    break;
                default:
                    System.out.println("\nINGRESE UNA OPCION VALIDA!");       
            }
        } while (opcion != 0);
        
    }
}
