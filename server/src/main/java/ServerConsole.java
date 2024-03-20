import commands.Command;
import lombok.SneakyThrows;
import misc.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ServerConsole {

    BufferedReader in;
    CommandManager commandManager;
    public ServerConsole(CommandManager commandManager) {
        in = new BufferedReader(new InputStreamReader(System.in));
        this.commandManager = commandManager;
    }

    @SneakyThrows
    void repl() {
        while (true) {
            System.out.print("server>");
            String line = in.readLine();
            Request request = RequestParser.parse(line, new MatcherWithSave());
            if (request == null) continue;
            commandManager.execute(request, System.out);
        }
    }
}
