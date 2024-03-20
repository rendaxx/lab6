package fieldvalidators;
/**
 * Validator for annualTurnover field.
 * @see annualTurnoverValidator
 */
public class annualTurnoverValidator {
    public static boolean notValid(Long annualTurnover) {
        return annualTurnover == null || annualTurnover < 0;
    }
}
