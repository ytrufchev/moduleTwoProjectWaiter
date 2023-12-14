package menus;

import utilities.Formatter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class LoginMenu {
    String username;
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void loginMenu(String role) throws NoSuchAlgorithmException, FileNotFoundException {
        System.out.flush();
        String roleMenu = "";
        String title = "Login as " + role;
        Formatter formatter = new Formatter();
        System.out.print(formatter.formatter(roleMenu, title));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        setUsername(scanner.next());
        System.out.print("Enter Password: ");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(scanner.next().getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }
        setPassword(hexString.toString());

        if (checkCredentials()) {
            if (role.equalsIgnoreCase("waiter")) {
                WaiterMenu waiterMenu = new WaiterMenu();
                waiterMenu.waiterMenu();

            } else if (role.equalsIgnoreCase("cook")) {
                CookMenu cookMenu = new CookMenu();
                cookMenu.cookMenu();
            }
        } else {
            System.out.println("Login failed. Username or password is incorrect.");
        }
    }

    public boolean checkCredentials() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/persistent/accounts.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2) {
                    String storedUsername = values[0].trim();
                    String storedPasswordHash = values[1].trim();

                    if (storedUsername.equals(getUsername()) && storedPasswordHash.equals(getPassword())) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
