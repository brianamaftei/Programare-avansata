package org.example.Lab11.Repositories;

import org.example.Lab11.Models.Player;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Repository("PlayerRepository")
public class PlayerRepository implements BaseRepository {

    private static final List<Player> dataBase = new ArrayList<>();

    @Override
    public void createPlayer(@RequestBody String name) {
        dataBase.add(new Player(name));
    }

    @Override
    public List<Player> selectAll() {
        return dataBase;
    }

    @Override
    public Player modifyPlayer(@RequestBody String request) {
        JSONObject json = new JSONObject(request);
        for (Player p : dataBase) {
            if (p.getName().equals(json.getString("oldName"))) {
                p.setName(json.getString("newName"));
                return p;
            }
        }
        dataBase.add(new Player(json.getString("newName")));
        return dataBase.get(dataBase.size() - 1);
    }

    @Override
    public void deletePlayer(@RequestBody String request) {
        JSONObject json = new JSONObject(request);
        for (Player p : dataBase) {
            if (p.getName().equals(json.getString("name"))) {
                dataBase.remove(p);
                break;
            }
        }

    }


}
