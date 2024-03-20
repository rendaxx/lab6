import collectionobject.Organization;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OrganizationManager organizationManager = OrganizationManager.getInstance();
        CommandManager commandManager = new CommandManager(organizationManager);
        Server server = new Server(commandManager);
        server.init();
        server.run();
    }
}
