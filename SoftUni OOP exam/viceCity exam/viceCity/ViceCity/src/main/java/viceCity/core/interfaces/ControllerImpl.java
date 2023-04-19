package viceCity.core.interfaces;

import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.GunRepository;
import viceCity.repositories.interfaces.Repository;

import java.util.*;
import java.util.stream.Collectors;


import static viceCity.common.ConstantMessages.*;

public class ControllerImpl implements Controller{

    private Player player;
    private Deque<Gun>guns;
    private Map<String, Player> players;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        player = new MainPlayer();
        guns = new ArrayDeque<>();
        players = new LinkedHashMap<>();
        neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
    Player player1 = new CivilPlayer(name);
    players.put(name, player1);

        return String.format(PLAYER_ADDED,name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun;
        switch (type){
            case"Pistol":
                gun = new Pistol(name);
                break;
            case"Rifle":
                gun = new Rifle(name);
                break;
            default:
                return String.format(GUN_TYPE_INVALID);
        }
        guns.offer(gun);
        return String.format(GUN_ADDED,name,type);
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = guns.poll();
        if (gun == null){
            return String.format(GUN_QUEUE_IS_EMPTY);
        }
        if (name.equals("Vercetti")){
            player.getGunRepository().add(gun);
            String nameMainPlayer= "Tommy Vercetti";
            return String.format(GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), nameMainPlayer);
        }
        if (!players.containsKey(name)){
            return String.format(CIVIL_PLAYER_DOES_NOT_EXIST);
        }
        players.get(name).getGunRepository().add(gun);
        return String.format(GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(),name);
    }

    @Override
    public String fight() {
        neighbourhood.action(player, players.values());
        if (player.getLifePoints() == 100 && players.values()
                .stream().allMatch(e -> e.getLifePoints() == 50)){
            return String.format(FIGHT_HOT_HAPPENED);
        }

        List<Player> deadPl = players.values().stream().filter(p ->!p.isAlive()).collect(Collectors.toList());


        StringBuilder sb = new StringBuilder();
        sb.append(FIGHT_HAPPENED).append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE,player.getLifePoints()))
                .append(System.lineSeparator());
        sb.append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE,deadPl.size()))
                .append(System.lineSeparator());
        sb.append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, players.size() - deadPl.size()));

        for (Player pl : deadPl) {
            players.remove(pl.getName());
        }

        return sb.toString().trim();
    }
}
