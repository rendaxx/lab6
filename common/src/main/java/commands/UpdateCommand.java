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
public class UpdateCommand extends Command implements Serializable {
    private static final String NAME = "update";
    private static final String ARGS = "id {element}";
    private static final String DESC = "updates element by his id";
    @Serial
    private static final long serialVersionUID = 15L;

    @Setter
    private CollectionServer collectionServer;

    public UpdateCommand() {
        super(NAME, ARGS, DESC);
    }

    public UpdateCommand(CollectionServer collectionServer) {
        this();
        this.collectionServer = collectionServer;
    }

    @Override
    public List<String> requiredArgs() {
        return List.of("Long", "Organization");
    }

    @Override
    public void run(Object[] args) throws IOException {
        if (args == null) {
            log.severe("Args lost???");
            return;
        }
        Long id = null;
        try {
            id = (Long) args[0];
        } catch (ClassCastException e) {
            log.severe("This shouldn't have happen, but " + e.getMessage());
            return;
        }

        Organization organization = null;
        try {
            organization = (Organization) args[0];
        } catch (ClassCastException e) {
            log.severe("This shouldn't have happen, but " + e.getMessage());
            return;
        }

        collectionServer.updateElement(id, organization);
    }
}
