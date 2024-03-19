package commands;

import lombok.Setter;
import misc.CollectionServer;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ClearCommand extends Command implements Serializable {
    private static final String NAME = "clear";
    private static final String ARGS = "";
    private static final String DESC = "clears collection";

    @Serial
    private static final long serialVersionUID = 3L;

    @Setter
    private CollectionServer collectionServer;

    public ClearCommand() {
        super(NAME, ARGS, DESC);
    }

    public ClearCommand(CollectionServer collectionServer) {
        this();
        this.collectionServer = collectionServer;
    }

    @Override
    public List<String> requiredArgs() {
        return null;
    }
    @Override
    public void run(Object[] args) {
        collectionServer.clearCollection();
    }
}
