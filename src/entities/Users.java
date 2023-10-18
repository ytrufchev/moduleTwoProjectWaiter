package entities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Users {
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
    public void addUser(){
        String fileName = "src/accounts/accounts.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {

            writer.write(getUsername() + "," + getPassword());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
