package menus;

import utilities.Formatter;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MainMenu {
    public static void mainMenu() throws FileNotFoundException, NoSuchAlgorithmException {
        //
        DisplayMenu displayMenu = new DisplayMenu();
        displayMenu.displayMenu();
        //
        RegisterMenu registerMenu = new RegisterMenu();
        LoginMenu loginMenu = new LoginMenu();
        System.out.flush();
        String title = "Main Menu";
        String mainMenu = "1. Login as waiter\n2. Login as Cook\n3. Register\n4. Quit";
        Formatter formatter = new Formatter();
        System.out.print(formatter.formatter(mainMenu, title));
        Scanner scanner = new Scanner(System.in);
        int selection = scanner.nextInt();
        switch (selection){
            case 1 : loginMenu.loginMenu("waiter"); break;
            case 2 : loginMenu.loginMenu("cook"); break;
            case 3 : registerMenu.register(); break;
            case 4 : System.exit(0);
            default : System.out.println("Invalid option"); break;
        }
        scanner.close();
    }
}
