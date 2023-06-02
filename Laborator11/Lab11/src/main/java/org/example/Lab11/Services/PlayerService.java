package org.example.Lab11.Services;

import org.example.Lab11.Repositories.BaseRepository;
import org.example.Lab11.Repositories.PlayerRepository;
import org.example.Lab11.Models.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PlayerService {
    private final BaseRepository playerRepository;

    @Autowired
    public PlayerService(@Qualifier("PlayerRepository") PlayerRepository play) {
        this.playerRepository = play;
    }

    public void createPlayer(@RequestBody String name) {
        playerRepository.createPlayer(name);
    }

    public List<Player> selectAll() {
        return playerRepository.selectAll();
    }

    public Player modifyPlayer(@RequestBody String request) {
        return playerRepository.modifyPlayer(request);
    }

    public void deletePlayer(@RequestBody String request) {
        playerRepository.deletePlayer(request);
    }
}
