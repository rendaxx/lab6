public class Main {
    public static void main(String[] args) {
        ServerConnector connector = new ServerConnector();
        connector.connect();
        connector.send("улыбоктебедедмакар");
        System.out.println(connector.getResponse());
    }
}
