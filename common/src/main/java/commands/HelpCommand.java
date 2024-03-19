package commands;

import misc.CollectionServer;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;

public class HelpCommand extends Command implements Serializable {
    private static final String NAME = "help";
    private static final String ARGS = "";
    private static final String DESC = "prints this list";

    @Serial
    private static final long serialVersionUID = 8L;

    private LinkedHashMap<String, Command> commandByName;

    private HelpCommand() {
        super(NAME, ARGS, DESC);
    }

    public HelpCommand(LinkedHashMap<String, Command> linkedHashMap) {
        this();
        this.commandByName = linkedHashMap;
    }

    @Override
    public List<String> requiredArgs() {
        return null;
    }

    @Override
    public void run(Object... args) {
        PrintWriter out = new PrintWriter((ByteArrayOutputStream) args[0]);
        commandByName.values().forEach(out::println);
        out.flush();
    }
}
