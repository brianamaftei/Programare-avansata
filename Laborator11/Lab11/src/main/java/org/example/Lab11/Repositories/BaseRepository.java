package org.example.Lab11.Repositories;

import org.example.Lab11.Models.Player;

import java.util.List;

public interface BaseRepository {

    void createPlayer(String name);

    List<Player> selectAll();

    Player modifyPlayer(String request);

    void deletePlayer(String request);
}
