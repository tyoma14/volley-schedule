package ru.artzhelt.volleyschedule.team;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Artyom Zheltyshev on 18.02.2024
 */
@RestController
@RequestMapping("/api/team")
@RequiredArgsConstructor
public class TeamRestController {

    private final TeamService teamService;

    @PostMapping
    public Team add(@Valid @RequestBody Team team) {
        return teamService.add(team);
    }

    @GetMapping
    public List<Team> getAll() {
        return teamService.findAll();
    }

}
