package commands;

import lombok.Setter;
import lombok.extern.java.Log;
import misc.CollectionServer;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
@Log
public class RemoveByIDCommand extends Command implements Serializable {
    private static final String NAME = "remove_by_id";
    private static final String ARGS = "id";
    private static final String DESC = "removes element by his id";
    @Serial
    private static final long serialVersionUID = 10L;

    @Setter
    private CollectionServer collectionServer;

    public RemoveByIDCommand() {
        super(NAME, ARGS, DESC);
    }

    public RemoveByIDCommand(CollectionServer collectionServer) {
        this();
        this.collectionServer = collectionServer;
    }

    @Override
    public List<String> requiredArgs() {
        return List.of("Long");
    }

    @Override
    public void run(Object[] args) {
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
        collectionServer.removeElementById(id);
    }
}
