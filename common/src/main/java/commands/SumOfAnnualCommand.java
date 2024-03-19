package commands;

import lombok.Setter;
import misc.CollectionServer;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class SumOfAnnualCommand extends Command implements Serializable {
    private static final String NAME = "sum_of_annual_turnover";
    private static final String ARGS = "";
    private static final String DESC = "prints sum of all annual_turnovers";
    @Serial
    private static final long serialVersionUID = 14L;

    @Setter
    private CollectionServer collectionServer;

    public SumOfAnnualCommand() {
        super(NAME, ARGS, DESC);
    }
    public SumOfAnnualCommand(CollectionServer collectionServer) {
        this();
        this.collectionServer = collectionServer;
    }

    @Override
    public List<String> requiredArgs() {
        return null;
    }

    @Override
    public void run(Object[] args) {
        collectionServer.sumOfAnnual();
    }
}
