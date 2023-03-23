package bakery.repositories.interfaces;

import bakery.entities.tables.interfaces.Table;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class TableRepositoryImpl implements TableRepository<Table> {

    private Map<Integer, Table> models;

    public TableRepositoryImpl() {
        models = new LinkedHashMap<>();
    }

    @Override
    public Collection<Table> getAll() {
        return models.values();
    }

    @Override
    public void add(Table table) {
        models.put(table.getTableNumber(), table);
    }

    @Override
    public Table getByNumber(int number) {
        return models.get(number);
    }
}
