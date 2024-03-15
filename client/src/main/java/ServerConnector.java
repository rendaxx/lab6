import lombok.Setter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
@Log
public class ServerConnector implements ISend, IGetResponse {
    private SocketChannel client;
    private ByteBuffer buffer;
    String response;
    public void connect() {
        try {
            client = SocketChannel.open(new InetSocketAddress("localhost", 1488));
            buffer = ByteBuffer.allocate(256);
        } catch (IOException e) {
            log.warning("");
        }
    }

    @Override
    public String getResponse() {
        return response;
    }

    @Override
    public void send(String msg) {
        buffer = ByteBuffer.wrap(msg.getBytes());
        try {
            client.write(buffer);
            buffer.clear();
            client.read(buffer);
            response = new String(buffer.array()).trim();
            buffer.clear();
        } catch (IOException e) {
            log.warning("");
        }
    }
}
