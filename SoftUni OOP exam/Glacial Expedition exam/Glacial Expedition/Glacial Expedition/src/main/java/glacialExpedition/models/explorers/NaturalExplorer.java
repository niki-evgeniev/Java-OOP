package glacialExpedition.models.explorers;

import glacialExpedition.models.suitcases.Suitcase;

public class NaturalExplorer extends BaseExplorer {

    private static final double ENERGY = 60;

    public NaturalExplorer(String name) {
        super(name, ENERGY);
    }

    @Override
    public void search() {
        double energy = getEnergy();
        energy = Math.max(0, energy - 7);
        setEnergy(energy);
    }
}
