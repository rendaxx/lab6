package commands;

import lombok.Setter;
import misc.CollectionServer;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class ShowCommand extends Command implements Serializable {
    private static final String NAME = "show";
    private static final String ARGS = "";
    private static final String DESC = "prints collection's elements";
    @Serial
    private static final long serialVersionUID = 13L;

    @Setter
    private CollectionServer collectionServer;

    public ShowCommand() {
        super(NAME, ARGS, DESC);
    }
    public ShowCommand(CollectionServer collectionServer) {
        this();
        this.collectionServer = collectionServer;
    }

    @Override
    public List<String> requiredArgs() {
        return null;
    }

    @Override
    public void run(Object[] args) {
        collectionServer.collectionShow();
    }
}
