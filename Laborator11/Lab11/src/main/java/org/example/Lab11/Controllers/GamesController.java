package org.example.Lab11.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.example.Lab11.Helpers.Saver;
import org.example.Lab11.Helpers.ToSave;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/games")
@RestController
public class GamesController {
    @GetMapping
    @Operation(tags = {"Game"})
    public ToSave createP() {
        return Saver.load();
    }


}
