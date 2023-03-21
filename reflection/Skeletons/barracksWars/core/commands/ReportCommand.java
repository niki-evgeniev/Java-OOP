package reflection.Skeletons.barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import reflection.Skeletons.barracksWars.annotations.Inject;

public class ReportCommand extends Command{
    @Inject
    private Repository repository;


    public ReportCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        return repository.getStatistics();
    }
}
