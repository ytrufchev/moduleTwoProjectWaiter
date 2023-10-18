package utilities;

import menus.RegisterMenu;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class ValidatePassword {
    public boolean validatePassword(String passOne, String passTwo) throws FileNotFoundException, NoSuchAlgorithmException {
      if(!passOne.equals(passTwo)){
          System.out.println("Password mismatch");
          RegisterMenu registerMenu = new RegisterMenu();
          registerMenu.register();
          return false;
      }
      return true;
    }
}
