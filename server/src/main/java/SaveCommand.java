import commands.Command;
import lombok.Setter;
import misc.CollectionServer;

import java.io.IOException;
import java.util.List;

public class SaveCommand extends Command {

    private static final String NAME = "save";
    private static final String ARGS = "";
    private static final String DESC = "saves collection in csv format";

    @Setter
    private CollectionServer collectionServer;
    public SaveCommand() {
        super(NAME, ARGS, DESC);
    }

    @Override
    public List<String> requiredArgs() {
        return null;
    }

    @Override
    public void run(Object... args) throws IOException {
        collectionServer.save();
    }
}
