package commands;

import collectionobject.Organization;
import lombok.Setter;
import lombok.extern.java.Log;
import misc.CollectionServer;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Log
public class AddCommand extends Command implements Serializable {
    private static final String NAME = "add";
    private static final String ARGS = "{element}";
    private static final String DESC = "adds element in collection";

    @Serial
    private static final long serialVersionUID = 1L;

    @Setter
    private CollectionServer collectionServer;

    public AddCommand() {
        super(NAME, ARGS, DESC);
    }
    public AddCommand(CollectionServer collectionServer) {
        this();
        this.collectionServer = collectionServer;
    }

    @Override
    public List<String> requiredArgs() {
        return List.of("Organization");
    }

    @Override
    public void run(Object[] args) throws IOException {
        if (args == null) {
            log.severe("Args lost???");
            return;
        }
        Organization organization = null;
        try {
            organization = (Organization) args[0];
        } catch (ClassCastException e) {
            log.severe("This shouldn't have happen, but " + e.getMessage());
            return;
        }
        collectionServer.addElement(organization);
    }
}
