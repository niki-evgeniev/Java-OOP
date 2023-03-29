package magicGame.repositories.interfaces;

import magicGame.models.magics.Magic;

import java.util.ArrayList;
import java.util.Collection;

public class MagicRepositoryImpl implements MagicRepository<Magic>{

    private Collection<Magic> magics;

    public MagicRepositoryImpl() {
        magics = new ArrayList<>();
    }

    @Override
    public Collection<Magic> getData() {
        return null;
    }

    @Override
    public void addMagic(Magic model) {

    }

    @Override
    public boolean removeMagic(Magic model) {
        return false;
    }

    @Override
    public Magic findByName(String name) {
        return null;
    }
}
