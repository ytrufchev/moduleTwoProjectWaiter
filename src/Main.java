import menus.MainMenu;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenu();
    }
}