import collectionobject.Organization;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.connectTo("localhost", 1488);
        Controller controller = new Controller(client, client, System.in, System.out);
        controller.repl();
    }
}
