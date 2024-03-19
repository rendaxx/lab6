import collectionobject.Organization;
import lombok.Setter;
import misc.CollectionServer;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Objects;

public class OrganizationManager implements CollectionServer {
    private static OrganizationManager singleton;
    @Setter
    private LinkedHashSet<Organization> organizations;
    private final LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;

    PrintWriter out;

    private CollectionStreamer<LinkedHashSet<Organization>> collectionStreamer;

    public static OrganizationManager getInstance() {
        if (singleton == null)
            singleton = new OrganizationManager();
        return singleton;
    }

    private OrganizationManager() {
        organizations = new LinkedHashSet<>();
        lastInitTime = LocalDateTime.now();
    }
    
    public void deleteInvalidElements() {
        organizations.stream().filter(o -> !o.validate()).forEach(organizations::remove);
    }

/*    public void setCollectionStreamer(CollectionStreamer<LinkedHashSet<Organization>> collectionStreamer) {
        this.collectionStreamer = collectionStreamer;
    }*/
    
    @Override
    public void addElement(Organization organization) {
        organizations.add(organization);
    }
    
    @Override
    public void collectionInfo() {
        out.println("type: " + organizations.getClass().getSimpleName());
        out.println("init time: " + lastInitTime);
        out.println("last save time: " + lastSaveTime);
        out.println("number of elements: " + organizations.size());
        out.flush();
    }
    @Override
    public void collectionShow() {
        organizations.forEach(out::println);
        if (organizations.isEmpty()) {
            out.println("Collection is empty");
        }
        out.flush();
    }

    @Override
    public void updateElement(Long id, Organization organization)  {
        var answer = organizations.stream().filter(org ->
                Objects.equals(org.getId(), id)).findFirst();
        if (answer.isEmpty()) {
            out.println("Can't update: no element with such id");
            out.flush();
            return;
        }
        Organization element = answer.get();
        Long tempId = element.getId();
        Date tempDate = element.getCreationDate();
        element = organization;
        element.setId(tempId);
        element.setCreationDate(tempDate);
        out.println("Successfully updated");
        out.flush();
    }

    @Override
    public void removeElementById(Long id) {
        var answer = organizations.stream().filter(organization ->
                Objects.equals(organization.getId(), id)).findFirst();
        if (answer.isEmpty()) {
            out.println("Can't delete: no element with such id");
            out.flush();
            return;
        }
        organizations.remove(answer.get());
        out.println("Successfully deleted");
        out.flush();
    }
    @Override
    public void clearCollection() {
        organizations.clear();
    }

    @Override
    public void addIfMin(Organization organization) {
        var answer = organizations.stream().min(Organization::compareTo);
        if (answer.isEmpty() || organization.compareTo(answer.get()) < 0) {
            organizations.add(organization);
            out.println("Successfully added");
        } else {
            out.println("Addition failed: this element is not minimum");
        }
        out.flush();
    }

    @Override
    public void removeGreater(Organization organization) {
        organizations.stream().filter(o -> o.compareTo(organization) > 0).forEach(organizations::remove);
    }

    @Override
    public void removeLower(Organization organization) {
        organizations.stream().filter(o -> o.compareTo(organization) < 0).forEach(organizations::remove);
    }
    @Override
    public void sumOfAnnual() {
        Long result = organizations.stream().map(Organization::getAnnualTurnover).reduce(0L, Long::sum);
        out.println("Sum of annualTurnover is: " + result);
        out.flush();
    }

    @Override
    public void filterStartsWithName(String name) {
        var temp = organizations.stream().filter(o -> o.getName().startsWith(name)).toList();
        if (temp.isEmpty()) {
            out.println("There is no elements that start with " + name);
        }
        temp.forEach(out::println);
        out.flush();
    }
    @Override
    public void printsAscending() {
        organizations.stream().map(Organization::getFullName).sorted().forEach(out::println);
        if (organizations.isEmpty()) {
            out.println("Collection is empty");
        }
        out.flush();
    }

    @Override
    public void changeOutputStream(OutputStream out) {
        this.out = new PrintWriter(out);
    }

    @Override
    public void save() {
        if (collectionStreamer == null) {
            throw new RuntimeException("No collection streamer == no saving >:(");
        }
        collectionStreamer.saveToFile(organizations);
    }

    @Override
    public void load() {
        if (collectionStreamer == null) {
            throw new RuntimeException("No collection streamer == no loading >:(");
        }
        organizations = collectionStreamer.readFromFile();
        deleteInvalidElements();
    }

}
