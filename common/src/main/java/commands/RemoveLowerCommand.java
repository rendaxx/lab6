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
public class RemoveLowerCommand extends Command implements Serializable {
    private static final String NAME = "remove_greater";
    private static final String ARGS = "{element}";
    private static final String DESC = "removes all elements greater that {element}";
    @Serial
    private static final long serialVersionUID = 12L;
    @Setter
    private CollectionServer collectionServer;

    public RemoveLowerCommand() {
        super(NAME, ARGS, DESC);
    }
    public RemoveLowerCommand(CollectionServer collectionServer) {
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
        collectionServer.removeLower(organization);
    }
}
