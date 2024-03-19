package commands;

import lombok.Setter;
import misc.CollectionServer;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class InfoCommand extends Command implements Serializable {
    private static final String NAME = "info";
    private static final String ARGS = "";
    private static final String DESC = "prints collection's info";

    @Serial
    private static final long serialVersionUID = 8L;

    @Setter
    private CollectionServer collectionServer;

    public InfoCommand() {
        super(NAME, ARGS, DESC);
    }
    public InfoCommand(CollectionServer collectionServer) {
        this();
        this.collectionServer = collectionServer;
    }

    @Override
    public List<String> requiredArgs() {
        return null;
    }

    @Override
    public void run(Object[] args) {
        collectionServer.collectionInfo();
    }
}
