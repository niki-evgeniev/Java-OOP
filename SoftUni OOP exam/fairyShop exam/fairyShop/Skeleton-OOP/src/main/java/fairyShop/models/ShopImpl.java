package fairyShop.models;

import java.util.Collection;

public class ShopImpl implements Shop{


    @Override
    public void craft(Present present, Helper helper) {
//        Collection<Instrument> ins = helper.getInstruments();
//
//        if (helper.canWork()){
//            for (Instrument in : ins) {
//                while (!in.isBroken()){
//                    present.getCrafted();
//                    helper.work();
//                    in.use();
//                }
//                if (present.isDone()){
//                    return;
//                }
//                if (!helper.canWork()){
//                    return;
//                }
//            }
//        }
        if (helper.canWork()) {
            for (Instrument instrument : helper.getInstruments()) {
                while (!instrument.isBroken()) {
                    if (!instrument.isBroken() && helper.canWork()) {
                        present.getCrafted();
                        helper.work();
                        instrument.use();
                    }
                    if (present.isDone()) {
                        return;
                    }
                    if (!helper.canWork()) {
                        return;
                    }
                }
            }
        }

    }
}
