package goldDigger.core;


import goldDigger.models.discoverer.Anthropologist;
import goldDigger.models.discoverer.Archaeologist;
import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.discoverer.Geologist;
import goldDigger.models.operation.Operation;
import goldDigger.models.operation.OperationImpl;
import goldDigger.models.spot.Spot;
import goldDigger.models.spot.SpotImpl;
import goldDigger.repositories.DiscovererRepository;
import goldDigger.repositories.SpotRepository;

import java.util.List;
import java.util.stream.Collectors;

import static goldDigger.common.ConstantMessages.*;
import static goldDigger.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private DiscovererRepository discovererRepository;
    private SpotRepository spotRepository;
    private int countSpot;

    public ControllerImpl() {
        discovererRepository = new DiscovererRepository();
        spotRepository = new SpotRepository();
    }

    @Override
    public String addDiscoverer(String kind, String discovererName) {
        Discoverer discoverer;

        switch (kind) {
            case "Anthropologist":
                discoverer = new Anthropologist(discovererName);
                break;
            case "Archaeologist":
                discoverer = new Archaeologist(discovererName);
                break;
            case "Geologist":
                discoverer = new Geologist(discovererName);
                break;
            default:
                throw new IllegalArgumentException(DISCOVERER_INVALID_KIND);
        }
        discovererRepository.add(discoverer);
        return String.format(DISCOVERER_ADDED, kind, discovererName);
    }

    @Override
    public String addSpot(String spotName, String... exhibits) {
        Spot spot = new SpotImpl(spotName);

        for (String exhibit : exhibits) {
            spot.getExhibits().add(exhibit);
        }
        spotRepository.add(spot);
        return String.format(SPOT_ADDED,spotName);
    }

    @Override
    public String excludeDiscoverer(String discovererName) {
        Discoverer discoverer = discovererRepository.byName(discovererName);
        if (discoverer == null){
            String errMsg= String.format(DISCOVERER_DOES_NOT_EXIST,discovererName);
            throw new IllegalArgumentException(errMsg);
        }
        discovererRepository.remove(discoverer);
        return String.format(DISCOVERER_EXCLUDE,discovererName);
    }

    @Override
    public String inspectSpot(String spotName) {
        Spot spot = spotRepository.byName(spotName);
        List<Discoverer> collect = discovererRepository.getCollection().stream().filter(discoverer -> discoverer.getEnergy() > 45)
                .collect(Collectors.toList());
        if (collect.size() == 0){
            throw new IllegalArgumentException(SPOT_DISCOVERERS_DOES_NOT_EXISTS);
        }
        Operation operation = new OperationImpl();
        operation.startOperation(spot, collect);
        countSpot++;
        long count = collect.stream().filter( e -> e.getEnergy() ==0).count();
        return String.format(INSPECT_SPOT, spotName,count);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_SPOT_INSPECT,countSpot)).append(System.lineSeparator());
        sb.append(FINAL_DISCOVERER_INFO).append(System.lineSeparator());
        for (Discoverer discoverer : discovererRepository.getCollection()) {
            sb.append(String.format(FINAL_DISCOVERER_NAME, discoverer.getName()))
                    .append(System.lineSeparator());
            sb.append(String.format(FINAL_DISCOVERER_ENERGY,discoverer.getEnergy()))
                    .append(System.lineSeparator());
            String info ="";
            if (discoverer.getMuseum().getExhibits().size() == 0) {
                info = "None";
            }else {
                info = discoverer.getMuseum().getExhibits().stream().collect(Collectors.joining(FINAL_DISCOVERER_MUSEUM_EXHIBITS_DELIMITER));
            }
            sb.append(String.format(FINAL_DISCOVERER_MUSEUM_EXHIBITS,info))
                    .append(System.lineSeparator());
        }
        return sb.toString().trim();
    }
}
