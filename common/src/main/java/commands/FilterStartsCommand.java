package commands;

import collectionobject.Organization;
import lombok.Setter;
import lombok.extern.java.Log;
import misc.CollectionServer;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
@Log
public class FilterStartsCommand extends Command implements Serializable {
    private static final String NAME = "filter_starts_with_name";
    private static final String ARGS = "name";
    private static final String DESC = "prints all elements whose name field starts with {name}";
    @Serial
    private static final long serialVersionUID = 6L;

    @Setter
    private CollectionServer collectionServer;

    public FilterStartsCommand() {
        super(NAME, ARGS, DESC);
    }
    public FilterStartsCommand(CollectionServer collectionServer) {
        this();
        this.collectionServer = collectionServer;
    }

    @Override
    public List<String> requiredArgs() {
        return List.of("String");
    }

    @Override
    public void run(Object[] args) {
        if (args == null) {
            log.severe("Args lost???");
            return;
        }
        String name = null;
        try {
            name = (String) args[0];
        } catch (ClassCastException e) {
            log.severe("This shouldn't have happen, but " + e.getMessage());
            return;
        }
        collectionServer.filterStartsWithName(name);
    }
}
