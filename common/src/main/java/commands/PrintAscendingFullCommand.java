package commands;

import lombok.Setter;
import misc.CollectionServer;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class PrintAscendingFullCommand extends Command implements Serializable {
    private static final String NAME = "print_field_ascending_full_name";
    private static final String ARGS = "";
    private static final String DESC = "prints all fullNames sorted";

    @Serial
    private static final long serialVersionUID = 9L;

    @Setter
    private CollectionServer collectionServer;

    public PrintAscendingFullCommand() {
        super(NAME, ARGS, DESC);
    }
    public PrintAscendingFullCommand(CollectionServer collectionServer) {
        this();
        this.collectionServer = collectionServer;
    }

    @Override
    public List<String> requiredArgs() {
        return null;
    }
    @Override
    public void run(Object[] args) {
        collectionServer.printsAscending();
    }
}
