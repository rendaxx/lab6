package fieldvalidators;
/**
 * Validator for employeesCount field.
 */
public class employeesCountValidator {
    public static boolean notValid(Long employeesCount) {
        return employeesCount == null || employeesCount < 0;
    }
}
