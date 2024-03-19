import collectionobject.Organization;
import commands.Command;
import lombok.extern.java.Log;
import misc.Request;

@Log
public class RequestParser {
    public static Request parse(String str) {
        log.info("Parsing message...");
        CommandInput input = new CommandInput(str);

        Command command = new CommandMatcher().match(input);
        if (command == null) {
            log.warning("Command not found");
            return null;
        }

        if ((input.getArgs() == null) != (command.requiredArgs() == null)) {
            log.warning("Next arguments required to run this command: " + command.requiredArgs());
            return null;
        }

        if (input.getArgs() == null) {
            return new Request(command);
        }

        if (input.getArgs().length != command.requiredArgs().size()) {
            log.warning("Next arguments required to run this command: " + command.requiredArgs());
            return null;
        }

        Object[] objects = new Object[input.getArgs().length];
        for (int i = 0; i < input.getArgs().length; ++i) {
            switch (command.requiredArgs().get(i)) {
                case "Long":
                    try {
                        objects[i] = Long.parseLong(input.getArgs()[i]);
                    } catch (NumberFormatException e) {
                        log.warning("Can't parse provided id");
                        return null;
                    }
                case "Organization":
                    objects[i] = CsvParser.parse(input.getArgs()[i]);
                    if (objects[i] == null) {
                        log.warning("Can't parse provided Human");
                        return null;
                    }
                case "String":
                    objects[i] = input.getArgs()[i];
            }
        }
        return new Request(command, objects);
    }
}
