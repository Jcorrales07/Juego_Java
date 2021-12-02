package principal;

import uimenu.Menu;

/**
 *
 * @author Lenovo
 */
public class Main {
    public static void main(String[] args) {
        // Instancia del menu, queria hacerlo con static pero me daba problemas
        Menu entrar = new Menu();
        // Llamamos al menu
        entrar.menuInicio();
    }
}
