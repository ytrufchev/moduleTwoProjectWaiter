package menus;

import com.sun.tools.javac.Main;
import utilities.Formatter;
import utilities.ValidatePassword;
import utilities.ValidateUsername;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class RegisterMenu {
    String username = "";
    String passOne = "";
    String passTwo = "";
    boolean passIdentical = false;

    public String getUsername() {
        return username;
    }

    public String getPassOne() {
        return passOne;
    }

    public String getPassTwo() {
        return passTwo;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassOne(String passOne) {
        this.passOne = passOne;
    }

    public void setPassTwo(String passTwo) {
        this.passTwo = passTwo;
    }

    public void setPassIdentical(boolean passIdentical) {
        this.passIdentical = passIdentical;
    }

    public boolean isPassIdentical() {
        return passIdentical;
    }

    public void register() throws FileNotFoundException, NoSuchAlgorithmException {
        System.out.flush();
        String title = "Register";
        String registerMenu = "";
        Formatter formatter = new Formatter();
        System.out.print(formatter.formatter(registerMenu, title));
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Username: ");
        setUsername(scanner.next());
        ValidateUsername validateUsername = new ValidateUsername();
        validateUsername.ValidateUsername(getUsername());
        System.out.println(validateUsername);
        System.out.print("Enter Password: ");
        setPassOne(scanner.next());
        System.out.print("Repeat password: ");
        setPassTwo(scanner.next());
        ValidatePassword validatePassword = new ValidatePassword();
        validatePassword.validatePassword(getPassOne(), getPassTwo());
        if(validateUsername.ValidateUsername(getUsername()) && validatePassword.validatePassword(getPassOne(), getPassTwo())){
            addNewUser();
        }
    }
    public void addNewUser() throws NoSuchAlgorithmException, FileNotFoundException {
        String fileName = "src/persistent/accounts.csv";

        // Hash the password
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(getPassOne().getBytes(StandardCharsets.UTF_8));
        byte[] hash = digest.digest();

        // Convert the byte array to a hexadecimal string
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            hexString.append(String.format("%02x", b));
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(getUsername() + "," + hexString.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully registered");
        MainMenu mainMenu = new MainMenu();
        mainMenu.mainMenu();
    }
}
