package commands;

import collectionobject.Organization;
import lombok.Setter;
import lombok.extern.java.Log;
import misc.CollectionServer;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;
@Log
public class AddIfMinCommand extends Command implements Serializable {
    private static final String NAME = "add_if_min";
    private static final String ARGS = "{element}";
    private static final String DESC = "adds {element} in collection if its value is lower than collection's minimum";

    @Serial
    private static final long serialVersionUID = 2L;

    @Setter
    private CollectionServer collectionServer;

    public AddIfMinCommand() {
        super(NAME, ARGS, DESC);
    }

    public AddIfMinCommand(CollectionServer collectionServer) {
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
        collectionServer.addIfMin(organization);
    }
}
