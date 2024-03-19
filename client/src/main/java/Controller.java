import lombok.AllArgsConstructor;
import lombok.extern.java.Log;

import java.io.*;

@AllArgsConstructor
@Log
public class Controller {

    private static final String EXIT = "exit";

    ISend sender;
    IGetResponse receiver;
    BufferedReader in;
    PrintWriter out;

    public Controller(ISend sender, IGetResponse receiver, InputStream inputStream, OutputStream outputStream) {
        this.sender = sender;
        this.receiver = receiver;
        in = new BufferedReader(new InputStreamReader(inputStream));
        out = new PrintWriter(outputStream);
    }

    void showPS() {
        out.print("lab6>");
        out.flush();
    }

    String read() {
        String line = null;
        try {
            line = in.readLine();
        } catch (IOException e) {
            log.severe("Something happened to InputStream!");
        }
        return line;
    }

    void send(String line) {
        sender.send(line);
    }

    String getResponse() {
        return receiver.getResponse();
    }

    void print(String response) {
        log.info("Printing message...");
        if (response != null) {
            out.println(response);
            out.flush();
        }
    }

    void repl() {
        while (true) {
            showPS();

            String line = read();
            if (line == null || line.equalsIgnoreCase(EXIT)) return;

            send(line);

            String response = getResponse();

            print(response);
        }
    }

}
