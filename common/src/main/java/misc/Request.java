package misc;

import collectionobject.Organization;
import commands.Command;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Setter
@Getter
public class Request implements Serializable {
    private Command command;
    private Object[] objects;

    @Serial
    private static final long serialVersionUID = 777L;

    public Request(Command command) {
        this.command = command;
    }

    public Request(Command command, Object[] objects) {
        this.command = command;
        this.objects = objects;
    }
}
