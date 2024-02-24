package ru.artzhelt.volleyschedule.game;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.artzhelt.volleyschedule.presentation.Option;

import java.util.List;

/**
 * Created by Artyom Zheltyshev on 18.02.2024
 */
@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameRestController {

    private final GameService gameService;

    @PostMapping
    public Game scheduleGame(@Valid @RequestBody Game game) {
        return gameService.scheduleGame(game);
    }

    @GetMapping("/gym-options")
    public List<Option<Long>> gymOptions() {
        return gameService.gymOptions();
    }

    @GetMapping("/owner-options")
    public List<Option<Long>> ownerOptions() {
        return gameService.ownerOptions();
    }

    @GetMapping("/guest-options")
    public List<Option<Long>> guestOptions() {
        return gameService.guestOptions();
    }
}
