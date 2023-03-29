package magicGame.repositories.interfaces;

import magicGame.models.magicians.Magician;

import java.util.ArrayList;
import java.util.Collection;

public class MagicianRepositoryImpl implements MagicianRepository<Magician>{

    private Collection<Magician> magicians;

    public MagicianRepositoryImpl() {
        magicians = new ArrayList<>();
    }

    @Override
    public Collection<Magician> getData() {
        return null;
    }

    @Override
    public void addMagician(Magician model) {

    }

    @Override
    public boolean removeMagician(Magician model) {
        return false;
    }

    @Override
    public Magician findByUsername(String name) {
        return null;
    }
}
