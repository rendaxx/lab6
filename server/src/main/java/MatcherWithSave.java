import misc.CommandMatcher;

public class MatcherWithSave extends CommandMatcher {
    public MatcherWithSave() {
        super();
        commandByName.put("save", new SaveCommand());
    }
}
