package misc;

import collectionobject.Address;
import collectionobject.Coordinates;
import collectionobject.OrganizationType;

import java.io.IOException;

public interface Interrogator {
    String askName() throws IOException;
    Coordinates askCoordinates() throws IOException;
    Long askAnnualTurnover() throws IOException;
    String askFullName() throws IOException;
    Long askEmployeesCount() throws IOException;
    OrganizationType askType() throws IOException;
    Address askPostalAddress() throws IOException;
}
