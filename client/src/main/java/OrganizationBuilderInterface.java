import collectionobject.Organization;

import java.io.IOException;

/**
 * Interface for building organization object.
 */
public interface OrganizationBuilderInterface {
    OrganizationBuilderInterface setId();
    OrganizationBuilderInterface setName() throws IOException;
    OrganizationBuilderInterface setCoordinates() throws IOException;
    OrganizationBuilderInterface setCreationDate();
    OrganizationBuilderInterface setAnnualTurnover() throws IOException;
    OrganizationBuilderInterface setFullName() throws IOException;
    OrganizationBuilderInterface setEmployeesCount() throws IOException;
    OrganizationBuilderInterface setType() throws IOException;
    OrganizationBuilderInterface setPostalAddress() throws IOException;
    Organization build();
}
