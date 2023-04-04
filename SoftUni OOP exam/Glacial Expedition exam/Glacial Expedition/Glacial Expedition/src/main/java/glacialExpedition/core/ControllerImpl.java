package glacialExpedition.core;

import glacialExpedition.models.explorers.AnimalExplorer;
import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.explorers.GlacierExplorer;
import glacialExpedition.models.explorers.NaturalExplorer;
import glacialExpedition.models.mission.Mission;
import glacialExpedition.models.mission.MissionImpl;
import glacialExpedition.models.states.State;
import glacialExpedition.models.states.StateImpl;
import glacialExpedition.models.suitcases.Suitcase;
import glacialExpedition.repositories.ExplorerRepository;
import glacialExpedition.repositories.StateRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static glacialExpedition.common.ConstantMessages.*;
import static glacialExpedition.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {

    private ExplorerRepository explorerRepository;
    private StateRepository stateRepository;

    private int count;

    public ControllerImpl() {
        explorerRepository = new ExplorerRepository();
        stateRepository = new StateRepository();
    }

    @Override
    public String addExplorer(String type, String explorerName) {
        Explorer explorer;
        switch (type) {
            case "AnimalExplorer":
                explorer = new AnimalExplorer(explorerName);
                break;
            case "GlacierExplorer":
                explorer = new GlacierExplorer(explorerName);
                break;
            case "NaturalExplorer":
                explorer = new NaturalExplorer(explorerName);
                break;
            default:
                throw new IllegalArgumentException(EXPLORER_INVALID_TYPE);
        }
        explorerRepository.add(explorer);
        return String.format(EXPLORER_ADDED, type, explorerName);
    }

    @Override
    public String addState(String stateName, String... exhibits) {
        State state = new StateImpl(stateName);

        for (String exhibit : exhibits) {
            state.getExhibits().add(exhibit);
        }
        stateRepository.add(state);
        return String.format(STATE_ADDED, stateName);
    }

    @Override
    public String retireExplorer(String explorerName) {
        Explorer explorer = explorerRepository.byName(explorerName);
        if (explorer == null) {
            String errMsg = String.format(EXPLORER_DOES_NOT_EXIST, explorerName);
            throw new IllegalArgumentException(errMsg);
        }

        explorerRepository.remove(explorer);
        return String.format(EXPLORER_RETIRED, explorerName);
    }

    @Override
    public String exploreState(String stateName) {

        List<Explorer> collect = explorerRepository.getCollection()
                .stream()
                .filter(e -> e.getEnergy() > 50)
                .collect(Collectors.toList());
        if (collect.size() == 0) {
            String errMsg = String.format(STATE_EXPLORERS_DOES_NOT_EXISTS);
            throw new IllegalArgumentException(errMsg);
        }
        State state = stateRepository.byName(stateName);
        Mission mission = new MissionImpl();
        mission.explore(state, collect);
        count++;
        long count1 = collect.stream().filter(e -> e.getEnergy() == 0).count();


        return String.format(STATE_EXPLORER, stateName,count1);
    }

    @Override
    public String finalResult() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(FINAL_STATE_EXPLORED, count)).append(System.lineSeparator());
        sb.append(String.format(FINAL_EXPLORER_INFO)).append(System.lineSeparator());
        for (Explorer explorer : explorerRepository.getCollection()) {
            sb.append(String.format(FINAL_EXPLORER_NAME, explorer.getName())).append(System.lineSeparator());
            sb.append(String.format(FINAL_EXPLORER_ENERGY, explorer.getEnergy())).append(System.lineSeparator());
            Collection<String> exhibits = explorer.getSuitcase().getExhibits();
            String info;
            if (exhibits.size() == 0) {
                info = "None";
            } else {
                info = exhibits.stream().collect(Collectors.joining(FINAL_EXPLORER_SUITCASE_EXHIBITS_DELIMITER));
            }
            sb.append(String.format(FINAL_EXPLORER_SUITCASE_EXHIBITS, info)).append(System.lineSeparator());
        }


        return sb.toString().trim();
    }
}
