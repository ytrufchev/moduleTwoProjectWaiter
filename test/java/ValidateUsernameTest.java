import org.junit.jupiter.api.Test;
import utilities.ValidateUsername;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateUsernameTest {

    @Test
    void testValidateUsername_UsernameExists() throws FileNotFoundException {
        ValidateUsername validator = new ValidateUsername();
        String existingUsername = "existingUsername";

        boolean result = validator.ValidateUsername(existingUsername);

        assertTrue(result, "The username already exists, so validation should fail");
    }

    @Test
    void testValidateUsername_UsernameDoesNotExist() throws FileNotFoundException {
        ValidateUsername validator = new ValidateUsername();
        String newUsername = "newUsername";

        boolean result = validator.ValidateUsername(newUsername);

        assertTrue(result, "The username does not exist, so validation should pass");
    }
}
