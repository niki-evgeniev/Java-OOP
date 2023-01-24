package EncapsulationFirstAndReserveTeam;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Team {
    private String name;
    private List<Person> fistTeam;
    private List<Person> reserveTeam;

    public Team(String name) {
        setName(name);
        this.fistTeam = new ArrayList<>();
        this.reserveTeam = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public List<Person> getFirstTeam() {
        return Collections.unmodifiableList(fistTeam);
    }

    public List<Person> getReserveTeam() {
        return Collections.unmodifiableList(reserveTeam);
    }

    public void addPlayer(Person person) {
        if (person.getAge() < 40) {
            fistTeam.add(person);
        } else {
            reserveTeam.add(person);
        }
    }


}
