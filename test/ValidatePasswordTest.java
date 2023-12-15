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
        Assertions.assertFalse(result);
    }
    @Test
    public void testValidatePasswordNullInput() throws FileNotFoundException, NoSuchAlgorithmException {
        ValidatePassword validator = new ValidatePassword();
        boolean result = validator.validatePassword(null, "password");
        Assertions.assertFalse(result);
    }
    @Test
    public void testValidatePasswordEmptyInput() throws FileNotFoundException, NoSuchAlgorithmException {
        ValidatePassword validator = new ValidatePassword();
        boolean result = validator.validatePassword("", "password");
        Assertions.assertFalse(result);
    }
}