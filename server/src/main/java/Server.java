import lombok.SneakyThrows;
import lombok.extern.java.Log;
import misc.Request;
import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;
@Log
public class Server implements Runnable {
    Selector selector;
    ServerSocketChannel serverSocket;
    CommandManager commandManager;

    public Server(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void init() throws IOException {
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(1488));
        serverSocket.configureBlocking(false);
        selector = Selector.open();
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
    }
    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            ByteBuffer buffer = ByteBuffer.allocate(4096);
            selector.select();
            Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                iter.remove();

                if (key.isAcceptable()) {
                    register(selector, key);
                } else if (key.isReadable()) {
                    handleRequest(buffer, key);
                }
            }
        }
    }

    private void handleRequest(ByteBuffer buffer, SelectionKey key) throws IOException {
        SocketChannel client = (SocketChannel) key.channel();
        Request request = getRequest(buffer, client);
        if (request == null) return;

        ByteArrayOutputStream out = new ByteArrayOutputStream(4096);
        commandManager.execute(request, out);
        buffer = ByteBuffer.wrap(out.toByteArray()).duplicate();
        client.write(buffer);
    }

    private Request getRequest(ByteBuffer buffer, SocketChannel client) throws IOException {
        int r = -1;
        try {
            r = client.read(buffer);
        } catch (SocketException | NotYetConnectedException | ClosedChannelException e) {
            log.info("Channel closed");
            client.close();
            return null;
        }
        if (r == -1) {
            client.close();
            log.info("Not accepting client messages anymore");
            return null;
        }
        Request request = SerializationUtils.deserialize(buffer.array());
        return request;
    }

    private void register(Selector selector, SelectionKey key) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel client = serverChannel.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        log.info("New client connected!");
    }
}
