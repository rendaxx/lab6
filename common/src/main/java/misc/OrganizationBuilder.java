package misc;

import collectionobject.Organization;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Random;

/**
 * Class for building organization object.
 */
public class OrganizationBuilder implements OrganizationBuilderInterface {
    private final Organization org;

    public OrganizationBuilder() {
        org = new Organization();
    }
    public OrganizationBuilder(Organization org) {
        this.org = org;
    }
    Interrogator interrogator;

    public OrganizationBuilder(Interrogator i) {
        this.interrogator = i;
        this.org = new Organization();
    }

    @Override
    public OrganizationBuilderInterface setId() {
        org.setId(new Random().nextLong(Long.MAX_VALUE));
        return this;
    }

    @Override
    public OrganizationBuilderInterface setName() throws IOException {
        var x = interrogator.askName();
        org.setName(x);
        return this;
    }

    @Override
    public OrganizationBuilderInterface setCoordinates() throws IOException {
        var x = interrogator.askCoordinates();
        org.setCoordinates(x);
        return this;
    }

    @Override
    public OrganizationBuilderInterface setCreationDate() {
        org.setCreationDate(LocalDate.now());
        return this;
    }

    @Override
    public OrganizationBuilderInterface setAnnualTurnover() throws IOException {
        var x = interrogator.askAnnualTurnover();
        org.setAnnualTurnover(x);
        return this;
    }

    @Override
    public OrganizationBuilderInterface setFullName() throws IOException {
        var x = interrogator.askFullName();
        org.setFullName(x);
        return this;
    }

    @Override
    public OrganizationBuilderInterface setEmployeesCount() throws IOException {
        var x = interrogator.askEmployeesCount();
        org.setEmployeesCount(x);
        return this;
    }

    @Override
    public OrganizationBuilderInterface setType() throws IOException {
        var x = interrogator.askType();
        org.setType(x);
        return this;
    }

    @Override
    public OrganizationBuilderInterface setPostalAddress() throws IOException {
        var x = interrogator.askPostalAddress();
        org.setPostalAddress(x);
        return this;
    }

    @Override
    public Organization build() {
        return org;
    }
}
