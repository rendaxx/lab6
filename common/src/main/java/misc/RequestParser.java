package misc;

import commands.Command;
import lombok.extern.java.Log;
import misc.CommandInput;
import misc.CommandMatcher;
import misc.Request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Log
public class RequestParser {

    public static Request parse(String str, CommandMatcher matcher) {
        log.info("Parsing message...");
        CommandInput input = new CommandInput(str);

        Command command = matcher.match(input);

        if (command == null) {
            System.out.println("Command not found");
            return null;
        }

        if (command.requiredArgs() == null) {
            return new Request(command);
        }

        List<String> noOrg = new ArrayList<>(command.requiredArgs());
        noOrg.remove("Organization");

        if ((input.getArgs() == null) != noOrg.isEmpty()) {
            System.out.println("Next arguments required to run this command: " + command.requiredArgs());
            return null;
        }

        if ((input.getArgs() != null) && (input.getArgs().length != noOrg.size())) {
            System.out.println("Next arguments required to run this command: " + command.requiredArgs());
            return null;
        }

        Object[] objects = new Object[command.requiredArgs().size()];
        for (int i = 0; i < command.requiredArgs().size(); ++i) {
            switch (command.requiredArgs().get(i)) {
                case "Long":
                    try {
                        objects[i] = Long.parseLong(input.getArgs()[i]);
                    } catch (NumberFormatException e) {
                        System.out.println("Can't parse provided id");
                        return null;
                    }
                    break;
                case "Organization":
                    try {
                        objects[i] = new OrganizationBuilder(new InterrogatorCLI()).setName()
                                .setCoordinates()
                                .setAnnualTurnover()
                                .setFullName()
                                .setEmployeesCount()
                                .setType()
                                .setPostalAddress().build();
                    } catch (IOException e) {
                        log.warning("Error while creating Organization");
                    }
                    break;
                case "String":
                    objects[i] = input.getArgs()[i];
            }
        }
        return new Request(command, objects);
    }
}
