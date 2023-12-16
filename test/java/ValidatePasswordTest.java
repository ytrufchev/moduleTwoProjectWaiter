import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utilities.ValidatePassword;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class ValidatePasswordTest {
    @Test
    public void testValidatePasswordMatching() throws FileNotFoundException, NoSuchAlgorithmException {
        ValidatePassword validator = new ValidatePassword();

        boolean result = validator.validatePassword("password", "password");

        // Assert that the result is true when passwords match
        Assertions.assertTrue(result);
    }
    @Test
    public void testValidatePasswordMismatch() throws FileNotFoundException, NoSuchAlgorithmException {
        ValidatePassword validator = new ValidatePassword();

        boolean result = validator.validatePassword("password1", "password2");

        Assertions.assertFalse(result);
    }
    @Test
    public void testValidatePasswordCaseSensitive() throws FileNotFoundException, NoSuchAlgorithmException {
        ValidatePassword validator = new ValidatePassword();

        boolean result = validator.validatePassword("Password", "password");

        // Assert that the result is false when passwords are case-sensitive and don't match
        Assertions.assertFalse(result);
    }
    @Test
    public void testValidatePasswordNullInput() throws FileNotFoundException, NoSuchAlgorithmException {
        ValidatePassword validator = new ValidatePassword();

        boolean result = validator.validatePassword(null, "password");

        // Assert that the result is false when the first password is null
        Assertions.assertFalse(result);
    }
    @Test
    public void testValidatePasswordEmptyInput() throws FileNotFoundException, NoSuchAlgorithmException {
        ValidatePassword validator = new ValidatePassword();

        boolean result = validator.validatePassword("", "password");

        // Assert that the result is false when the first password is empty
        Assertions.assertFalse(result);
    }

}
