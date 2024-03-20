import collectionobject.Organization;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OrganizationManager organizationManager = OrganizationManager.getInstance();
        CommandManager commandManager = new CommandManager(organizationManager);
        Server server = new Server(commandManager);
        server.init();
        Thread t1 = new Thread(server);
        t1.start();
        new ServerConsole(commandManager).repl();
    }
}
