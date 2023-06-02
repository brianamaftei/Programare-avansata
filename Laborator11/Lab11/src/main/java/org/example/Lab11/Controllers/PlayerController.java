package org.example.Lab11.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.Lab11.Models.Player;
import org.example.Lab11.Services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/player")
@RestController
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService ps) {
        this.playerService = ps;
    }

    @PostMapping
    @Operation(tags = {"Player"})
    public void createPlayer(@RequestBody String name) {
        playerService.createPlayer(name);
    }

    @GetMapping
    @Operation(tags = {"Player"})
    public List<Player> selectAll() {
        return playerService.selectAll();
    }

    @PutMapping
    @Operation(tags = {"Player"})
    public Player modifyPlayer(@RequestBody String request) {
        return playerService.modifyPlayer(request);
    }

    @DeleteMapping
    @Operation(tags = {"Player"})
    public void deletePlayer(@RequestBody String request) {
        playerService.deletePlayer(request);
    }

}
