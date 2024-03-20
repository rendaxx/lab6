package fieldvalidators;

/**
 * Validator for name field.
 */
public class nameValidator {
    public static boolean notValid(String name) {
        return name == null || name.isEmpty();
    }
}
