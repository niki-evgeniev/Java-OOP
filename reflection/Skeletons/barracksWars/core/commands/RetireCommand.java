package reflection.Skeletons.barracksWars.core.commands;

import barracksWars.interfaces.Repository;
import jdk.jshell.spi.ExecutionControl;
import reflection.Skeletons.barracksWars.annotations.Inject;

public class RetireCommand extends Command{
    @Inject
    private Repository repository;

    public RetireCommand(String[] data) {
        super(data);
    }

    @Override
    public String execute() {
        try{
            String unitType = getData()[1];
            repository.removeUnit(unitType);
            return String.format("%s retired!", unitType);
        } catch (IllegalArgumentException e){
            return e.getMessage();
        } catch (ExecutionControl.NotImplementedException e) {
            throw new RuntimeException(e);
        }
    }
}
