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

    void showPS() {
        out.println("lab6>");
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
        if (response != null) {
            out.println(response);
        }
    }

    void repl() {
        while (true) {
            showPS();

            String line = read();
            if (line == null || line.equalsIgnoreCase(EXIT)) return;

            send(line);

            String response = getResponse();

            if (response == null) {
                log.warning("For some reason response is null");
            }

            print(response);
        }
    }

}
