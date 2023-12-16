package utilities;

import menus.LoginMenu;
import menus.MainMenu;
import menus.RegisterMenu;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class ValidatePassword {
    public boolean validatePassword(String passOne, String passTwo) throws FileNotFoundException, NoSuchAlgorithmException {
        if (passOne != null && passOne.equalsIgnoreCase(passTwo)) {
            if (!passOne.equals(passTwo)) {
                System.out.println("Password mismatch");
                return false;
            }
            return true;
        }
        else {
            return false;
        }
    }
}
