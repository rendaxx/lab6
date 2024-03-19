package misc;

import collectionobject.Organization;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public interface CollectionServer {
    void addElement(Organization organization);
    void collectionInfo();
    void collectionShow();
    void updateElement(Long id, Organization organization);
    void removeElementById(Long id);
    void clearCollection();
    void addIfMin(Organization organization);
    void removeGreater(Organization organization);
    void removeLower(Organization organization);
    void sumOfAnnual();
    void filterStartsWithName(String name);
    void printsAscending();

    void changeOutputStream(OutputStream out);

    void save();

    void load();
}
