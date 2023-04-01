package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;


import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private HelperRepository helperRepository;
    private PresentRepository presentRepository;

    private int countDonePresent = 0;


    public ControllerImpl() {
        helperRepository = new HelperRepository();
        presentRepository = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = helperRepository.findByName(helperName);

        if (helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        //helperRepository.remove(helper);
        Instrument instrument = new InstrumentImpl(power);
        //helper.addInstrument(instrument);
        //helperRepository.add(helper);
        helperRepository.findByName(helperName).addInstrument(instrument);

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> collect = helperRepository.getModels().stream().filter(e -> e.getEnergy() > 50).collect(Collectors.toList());
        if (collect.size() == 0){
            throw new IllegalArgumentException(NO_HELPER_READY);
        }
        Present present = presentRepository.findByName(presentName);
        Shop shop = new ShopImpl();
        long brokenWeapon = 0;
        for (Helper helper : collect) {
            shop.craft(present, helper);
            brokenWeapon += helper.getInstruments().stream().filter(Instrument::isBroken).count();
            if (present.isDone()){
                break;
            }
        }
        String whatHappen = "not done";
        if (present.isDone()){
            whatHappen = "done";
            countDonePresent ++;
        }
        int n = 1;
//        return String.format(PRESENT_DONE, presentName, whatHappen) +
//                String.format(COUNT_BROKEN_INSTRUMENTS, brokenWeapon);
        return "1";
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!",countDonePresent)).append(System.lineSeparator());
        sb.append("Helpers info:").append(System.lineSeparator());
        for (Helper model : helperRepository.getModels()) {
            sb.append(String.format("Name: %s",model.getName())).append(System.lineSeparator());
            sb.append(String.format("Energy: %d",model.getEnergy())).append(System.lineSeparator());
            long count = model.getInstruments().stream().filter(e -> e.getPower() > 0).count();
            sb.append(String.format("Instruments: %d not broken left",count)).append(System.lineSeparator());
        }
//         int size = (int) presentRepository.getModels().stream().filter(Present::isDone).count();
//                List<String> collect = helperRepository.getModels().stream().map(helper -> String.format("Name: %s%n" +
//                                "Energy: %d%n" +
//                                "Instruments: %d not broken left%n", helper.getName(), helper.getEnergy(),
//                        (int) helper.getInstruments().stream().filter(instrument -> !instrument.isBroken()).count())).collect(Collectors.toList());
//                return String.format("%d presents are done!%n", size) + String.format("Helpers info:%n") + String.join("", collect).trim();

        return sb.toString().trim();
    }
}
