package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.museum.BaseMuseum;
import goldDigger.models.museum.Museum;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation{
    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
//        Collection<String> exb = spot.getExhibits();
//
//
//        for (Discoverer discoverer : discoverers) {
//            while (discoverer.canDig() && exb.size() > 0){
//                for (String s : exb) {
//                    discoverer.dig();
//                    discoverer.getMuseum().getExhibits().add(s);
//                    exb.remove(s);
//                }
//            }
//        }
        Collection<String> exb = spot.getExhibits();
        for (Discoverer discoverer : discoverers) {
            while(discoverer.canDig() && exb.iterator().hasNext()){
                discoverer.dig();
                String current = exb.iterator().next();
                discoverer.getMuseum().getExhibits().add(current);
                exb.remove(current);
            }
        }
    }
}
