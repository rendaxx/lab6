package fieldvalidators;
/**
 * Validator for fullName field.
 */
public class fullNameValidator {
    public static boolean notValid(String fullName) {
        return fullName.length() > 1311;
    }
}
