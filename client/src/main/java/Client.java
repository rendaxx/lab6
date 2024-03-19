import lombok.extern.java.Log;
import misc.Request;
import org.apache.commons.lang3.SerializationUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
@Log
public class Client implements ISend, IGetResponse {

    private SocketChannel client;
    private ByteBuffer buffer;
    private String response;

    public Client() {

    }

    public void connectTo(String hostname, int port) {
        try {
            client = SocketChannel.open(new InetSocketAddress(hostname, port));
        } catch (IOException e) {
            log.info("Server is not ready");
        }
    }

    @Override
    public String getResponse() {
        return response;
    }

    private void setResponse() throws IOException {
        log.info("Setting response...");
        buffer = ByteBuffer.allocate(4096);
        int r = client.read(buffer);
        if (r == -1) {
            response = null;
            return;
        }
        response = new String(buffer.array()).trim();
        log.info("Got the response: " + response);
    }

    private void wrapRequest(Request request) throws IOException {
        log.info("Wrapping the request...");
        buffer = ByteBuffer.wrap(SerializationUtils.serialize(request));
    }

    @Override
    public void send(String msg) {
        Request request = RequestParser.parse(msg);
        if (request == null) return;
        try {
            wrapRequest(request);
        } catch (IOException e) {
            log.severe("Well, I hope this won't happen");
            return;
        }

        try {
            log.info("Sending to server...");
            client.write(buffer);
            setResponse();
        } catch (IOException e) {
            log.warning("For some reason connection broke");
        }
    }
}
