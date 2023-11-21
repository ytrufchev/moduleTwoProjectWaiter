package utilities;

import menus.RegisterMenu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class ValidateUsername {
    public boolean ValidateUsername(String username) throws FileNotFoundException {
        String fileName = "src/persistent/accounts.csv";

        try (
                BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while (true) {
                try {
                    if (!((line = reader.readLine()) != null)) break;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                String[] values = line.split(",");
                if (values.length >= 1) {
                    String uname = values[0].trim();
                    if(uname.equals(username.toLowerCase())){
                        System.out.println("This username is taken");
                        RegisterMenu registerMenu = new RegisterMenu();
                        registerMenu.register();
                        return false;
                    }
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public String toString() {
        return "";
    }
}
