package commands;

import lombok.Getter;
import lombok.Setter;
import misc.CollectionServer;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class Command implements Serializable {
    private final String name;
    private final String possibleArgs;
    private final String description;

    @Serial
    private static final long serialVersionUID = 5L;

    @Setter
    private CollectionServer collectionServer;

    public Command(String name, String possibleArgs, String description) {
        this.name = name;
        this.possibleArgs = possibleArgs;
        this.description = description;
    }

    @Override
    public String toString() {
        return getName() + (getPossibleArgs().isEmpty() ? "" : " ") + getPossibleArgs() + ": " + getDescription();
    }

    public abstract List<String> requiredArgs();
    public abstract void run(Object... args) throws IOException;
}
