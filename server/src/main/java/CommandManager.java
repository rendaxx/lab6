import commands.Command;
import misc.CollectionServer;
import misc.Request;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

public class CommandManager {
    private CollectionServer collectionServer;

    public CommandManager(CollectionServer collectionServer) {
        this.collectionServer = collectionServer;
    }

    public void execute(Request request, OutputStream out) throws IOException {
        Command command = request.getCommand();
        if (command.getName().equals("help")) {
            command.run(out);
            return;
        }
        command.setCollectionServer(collectionServer);
        collectionServer.changeOutputStream(out);
        command.run(request.getObjects());
        collectionServer.save();
    }
}
