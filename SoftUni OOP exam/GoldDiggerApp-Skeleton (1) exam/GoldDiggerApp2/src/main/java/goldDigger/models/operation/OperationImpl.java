package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation {
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        for (Discoverer discoverer : discoverers) {
            while (discoverer.canDig() && spot.getExhibits().size() != 0) {
                for (String exhibit : spot.getExhibits()) {
                    discoverer.dig();
                    String asd = exhibit;
                    discoverer.getMuseum().getExhibits().add(asd);
                    spot.getExhibits().remove(exhibit);
                    if (!discoverer.canDig()){
                        break;
                    }
                }
            }
        }
    }
}
