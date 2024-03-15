import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ClientConnector {
    Selector selector;
    ServerSocketChannel serverSocket;
    public void init() throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(1488));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void run() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(256);
        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                }

                if (key.isReadable()) {
                    answerWithEcho(buffer, key);
                }
                iter.remove();
            }
        }
    }

    private void answerWithEcho(ByteBuffer buffer, SelectionKey key)
            throws IOException {

        SocketChannel client = (SocketChannel) key.channel();
        int r = client.read(buffer);
        if (r == -1) {
            client.close();
            System.out.println("Not accepting client messages anymore");
        }
        else {
            buffer.flip();
            client.write(buffer);
            buffer.clear();
        }
    }

    private void register(Selector selector, ServerSocketChannel serverSocket)
            throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    @lombok.SneakyThrows
    public static void main(String[] args) {
        ClientConnector clientConnector = new ClientConnector();
        clientConnector.init();
        clientConnector.run();
    }
}
