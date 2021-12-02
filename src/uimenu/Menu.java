package uimenu;

import java.util.Scanner;
import principal.GhostGame;

/**
 * @author Lenovo
 */
public class Menu {
    Scanner input = new Scanner(System.in);
    GhostGame funcion = new GhostGame();
    
    String username;
    String password;
    byte opcion;
    // Funcion que muestra el MENU DE INICIO
    public void menuInicio() {
        System.out.println("\n\t     [== BIENVENIDO A GHOSTGAME ==]");
        
        do {
            System.out.print("\n\t[=== MENU DE INICIO ===]"
                + "\n  1) Log in"
                + "\n  2) Registrar Jugador"
                + "\n  0) Salir"
                + "\nOPCION #");
            opcion = input.nextByte(); 
            
            switch (opcion) {
                case 1:
                    
                    // funcion de login
                    /* Hacer la verificacion del usuario, si existe le das acceso
                    al menu principal*/
//                    System.out.println("Ha iniciado sesion correctamente!");
                    menuPrincipal();
                    // si algo esta mal entonces se le manda al menu principal
                    // pero vamo a hacer que pregunte denuevo por que somos asi de pros
                    break;
                case 2:
                    /* Primero se tiene que registrar, si se hace bien entonces se
                    le da el menu principal. NO se pueden REPETIR los users 
                    si algo esta mal entonces se le manda al menu principal
                    pero vamo a hacer que pregunte denuevo por que somos asi de pros*/
//                    System.out.println("Ha creado su cuenta correctamente!");
                    System.out.println("\n\t╠╬══╣REGISTRAR USUARIO╠══╬╣");
                    System.out.print("Ingrese su usuario: ");
                    username = input.next();
                    System.out.print("Ingrese su contraseña: ");
                    password = input.next();
                    funcion.createPlayer(username, password);
                    menuPrincipal();
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
            System.out.print("\n\t[=== MENU PRINCIPAL ===]"
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
