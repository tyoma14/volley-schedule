package ru.artzhelt.volleyschedule.game.schedule.view;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Artyom Zheltyshev on 18.02.2024
 */
@RestController
@RequestMapping("/api/game/view")
@RequiredArgsConstructor
public class GameViewRestController {

    private final GameViewService gameViewService;

    @GetMapping
    public List<GameView> gamesForPeriod(Query query) {
        return gameViewService.gamesForPeriod(query.getPeriod());
    }

}
