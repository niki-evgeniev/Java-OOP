package aquarium.core;

import aquarium.entities.aquariums.Aquarium;
import aquarium.entities.aquariums.FreshwaterAquarium;
import aquarium.entities.aquariums.SaltwaterAquarium;
import aquarium.entities.decorations.Decoration;
import aquarium.entities.decorations.Ornament;
import aquarium.entities.decorations.Plant;
import aquarium.entities.fish.Fish;
import aquarium.entities.fish.FreshwaterFish;
import aquarium.entities.fish.SaltwaterFish;
import aquarium.repositories.DecorationRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import static aquarium.common.ConstantMessages.*;
import static aquarium.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private DecorationRepository decorationRepository;
    private Collection<Aquarium> aquariums;
    private int countFish;


    public ControllerImpl() {
        decorationRepository = new DecorationRepository();
        aquariums = new ArrayList<>();
    }

    @Override
    public String addAquarium(String aquariumType, String aquariumName) {

        Aquarium aquarium;
        switch (aquariumType) {
            case "FreshwaterAquarium":
                aquarium = new FreshwaterAquarium(aquariumName);
                break;
            case "SaltwaterAquarium":
                aquarium = new SaltwaterAquarium(aquariumName);
                break;
            default:
                throw new NullPointerException(INVALID_AQUARIUM_TYPE);
        }
        aquariums.add(aquarium);
        return String.format(SUCCESSFULLY_ADDED_AQUARIUM_TYPE, aquariumType);
    }

    @Override
    public String addDecoration(String type) {
        Decoration decoration;
        switch (type) {
            case "Ornament":
                decoration = new Ornament();
                break;
            case "Plant":
                decoration = new Plant();
                break;
            default:
                throw new IllegalArgumentException(INVALID_DECORATION_TYPE);
        }
        decorationRepository.add(decoration);
        return String.format(SUCCESSFULLY_ADDED_DECORATION_TYPE, type);
    }

    @Override
    public String insertDecoration(String aquariumName, String decorationType) {
        Decoration decoration = decorationRepository.findByType(decorationType);

        if (decoration == null) {
            String errMsg = String.format(NO_DECORATION_FOUND, decorationType);
            throw new IllegalArgumentException(errMsg);
        }
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                aquarium.addDecoration(decoration);
                decorationRepository.remove(decoration);
            }
        }

        return String.format(SUCCESSFULLY_ADDED_DECORATION_IN_AQUARIUM, decorationType, aquariumName);
    }

    @Override
    public String addFish(String aquariumName, String fishType, String fishName, String fishSpecies, double price) {
        Fish fish;
        switch (fishType) {
            case "FreshwaterFish":
                fish = new FreshwaterFish(fishName, fishSpecies, price);
                break;
            case "SaltwaterFish":
                fish = new SaltwaterFish(fishName, fishSpecies, price);
                break;
            default:
                throw new IllegalArgumentException(INVALID_FISH_TYPE);
        }

        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                if (aquarium.getClass().getSimpleName().equals("FreshwaterAquarium") &&
                        fish.getClass().getSimpleName().equals("FreshwaterFish")) {
                    aquarium.addFish(fish);
                } else if (aquarium.getClass().getSimpleName().equals("SaltwaterAquarium") &&
                        fish.getClass().getSimpleName().equals("SaltwaterFish")) {
                    aquarium.addFish(fish);
                } else {
                    return String.format(WATER_NOT_SUITABLE);
                }
            }
        }
        return String.format(SUCCESSFULLY_ADDED_FISH_IN_AQUARIUM, fishType, aquariumName);
    }

    @Override
    public String feedFish(String aquariumName) {
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                aquarium.feed();
                countFish = aquarium.getFish().size();
            }
        }
        return String.format(FISH_FED, countFish);
    }

    @Override
    public String calculateValue(String aquariumName) {
        double sum = 0;
        for (Aquarium aquarium : aquariums) {
            if (aquarium.getName().equals(aquariumName)) {
                Collection<Fish> fish = aquarium.getFish();
                for (Fish fish1 : fish) {
                    sum += fish1.getPrice();
                }
                Collection<Decoration> decorations = aquarium.getDecorations();
                for (Decoration decoration : decorations) {
                    sum += decoration.getPrice();
                }
            }
        }
        //int sum = field.getPlayers().stream().mapToInt(Player::getStrength).sum();
        return String.format(VALUE_AQUARIUM, aquariumName, sum);
    }

    @Override
    public String report() {
        return aquariums.stream().map(aquarium -> aquarium.getInfo()).collect(Collectors.joining(System.lineSeparator()));
    }
}
