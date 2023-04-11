package restaurant.repositories;

import restaurant.entities.tables.interfaces.Table;
import restaurant.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;

public class TableRepositoryImpl implements TableRepository<Table> {

    private Collection<Table> tables;

    public TableRepositoryImpl() {
        tables = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAllEntities() {
        return tables;
    }

    @Override
    public void add(Table entity) {
        tables.add(entity);
    }

    @Override
    public Table byNumber(int number) {
        return tables.stream()
                .filter(e -> e.getTableNumber() == number)
                .findFirst().orElse(null);
    }
}
//    @Override
//    public Booth getByNumber(int number) {
//        return this.booths.stream()
//                .filter(booth -> booth.getBoothNumber() == number)
//                .findFirst().orElse(null);
//    }
