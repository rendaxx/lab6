package misc;

import commands.*;

import java.util.LinkedHashMap;
public class CommandMatcher {
    protected LinkedHashMap<String, Command> commandByName;

    public CommandMatcher() {
        commandByName = new LinkedHashMap<>();
        commandByName.put("help", new HelpCommand(commandByName));
        commandByName.put("info", new InfoCommand());
        commandByName.put("show", new ShowCommand());
        commandByName.put("add", new AddCommand());
        commandByName.put("update", new UpdateCommand());
        commandByName.put("remove_by_id", new RemoveByIDCommand());
        commandByName.put("clear", new ClearCommand());
        commandByName.put("add_if_min", new AddIfMinCommand());
        commandByName.put("remove_greater", new RemoveGreaterCommand());
        commandByName.put("remove_lower", new RemoveLowerCommand());
        commandByName.put("sum_of_annual_turnover", new SumOfAnnualCommand());
        commandByName.put("filter_starts_with_name", new FilterStartsCommand());
        commandByName.put("print_field_ascending_full_name", new PrintAscendingFullCommand());
    }



    public Command match(CommandInput input) {
        Command command = commandByName.get(input.getName());
        return command;
    }
}
