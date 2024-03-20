package misc;

import collectionobject.Address;
import collectionobject.Coordinates;
import collectionobject.OrganizationType;
import fieldvalidators.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class for asking user input from command line.
 */
public class InterrogatorCLI implements Interrogator {

    private final BufferedReader in;
    public InterrogatorCLI() {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Reads line from BufferedReader.
     * @return line from BufferedReader.
     */
    private String readLine() throws IOException {
        String line = in.readLine();
        return line;
    }

    /**
     * Asks user for string input.
     * @return string input.
     */
    private String askString() throws IOException {
        String line;
        while ((line = readLine()) != null) {
            if (nameValidator.notValid(line)) {
                System.err.println("This field can't be empty. Try again.");
            } else {
                break;
            }
        }
        return line;
    }
    /**
     * Asks user for organization name.
     * @return organization name.
     */
    @Override
    public String askName() throws IOException {
        System.out.println("Enter organization name:");
        String name = askString();
        if (name == null) throw new IOException();
        return name;
    }
    /**
     * Asks user for X coordinate.
     * @return X coordinate.
     */
    private Double askX() throws IOException {
        String line;
        Double x = null;
        while ((line = readLine()) != null) {
            try {
                x = Double.parseDouble(line);
                if (coordinatesValidator.notValidX(x)) {
                    System.err.println("This number is wrong. Try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("This number is wrong. Try again.");
            }
        }
        return x;
    }
    /**
     * Asks user for Y coordinate.
     * @return Y coordinate.
     */
    private Double askY() throws IOException {
        String line;
        Double y = null;
        while ((line = readLine()) != null) {
            try {
                y = Double.parseDouble(line);
                if (coordinatesValidator.notValidY(y)) {
                    System.err.println("This number is wrong. Try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("This number is wrong. Try again.");
            }
        }
        return y;
    }

    /**
     * Asks user for coordinates.
     * @return coordinates.
     */
    @Override
    public Coordinates askCoordinates() throws IOException {
        System.out.println("Enter the X coordinate: ");
        Double x = askX();
        System.out.println("Enter the Y coordinate: ");
        Double y = askY();
        return new Coordinates(x, y);
    }
    /**
     * Asks user for annual turnover.
     *
     * @return annual turnover.
     */
    @Override
    public Long askAnnualTurnover() throws IOException {
        System.out.println("Enter annual turnover: ");
        String line;
        Long x = null;
        while ((line = readLine()) != null) {
            try {
                x = Long.parseLong(line);
                if (annualTurnoverValidator.notValid(x)) {
                    System.err.println("This field can't be lower than 0 or null. Try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("This number is wrong. Try again.");
            }
        }
        return x;
    }
    /**
     * Asks user for full name.
     * @return full name.
     */
    @Override
    public String askFullName() throws IOException {
        System.out.println("Enter the full name: ");
        String line;
        while ((line = readLine()) != null) {
            if (fullNameValidator.notValid(line)) {
                System.err.println("Name can't be longer than 1311 symbols. Try again.");
            } else {
                break;
            }
        }
        return line;
    }
    /**
     * Asks user for employees count.
     *
     * @return employees count.
     */
    @Override
    public Long askEmployeesCount() throws IOException {
        System.out.println("Enter employees count:");
        String line;
        Long x = null;
        while ((line = readLine()) != null) {
            try {
                x = Long.parseLong(line);
                if (employeesCountValidator.notValid(x)) {
                    System.err.println("This field can't be lower than 0 or null. Try again.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.err.println("This number is wrong. Try again.");
            }
        }
        return x;
    }
    /**
     * Asks user for organization type.
     * @return organization type.
     */
    @Override
    public OrganizationType askType() throws IOException {
        System.out.println("Choose organization type:");
        System.out.println("1: PUBLIC");
        System.out.println("2: GOVERNMENT");
        System.out.println("3: OPEN_JOINT_STOCK_COMPANY");
        String line;
        while ((line = readLine()) != null) {
            switch (line) {
                case ("1") -> {
                    return OrganizationType.PUBLIC;
                }
                case ("2") -> {
                    return OrganizationType.GOVERNMENT;
                }
                case ("3") -> {
                    return OrganizationType.OPEN_JOINT_STOCK_COMPANY;
                }
                default -> System.err.println("Choose number from 1 to 3.");
            }
        }
        return null;
    }
    /**
     * Asks user for postal address.
     * @return postal address.
     */
    @Override
    public Address askPostalAddress() throws IOException {
        System.out.println("Enter address: ");
        String street = askString();
        System.out.println("Enter zip code: ");
        String zipCode = askString();
        return new Address(street, zipCode);
    }
}
