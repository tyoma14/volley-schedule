package ru.artzhelt.volleyschedule.game;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ru.artzhelt.volleyschedule.gym.Gym;
import ru.artzhelt.volleyschedule.gym.GymRepository;
import ru.artzhelt.volleyschedule.presentation.Option;
import ru.artzhelt.volleyschedule.team.Team;
import ru.artzhelt.volleyschedule.team.TeamRepository;

import java.util.Comparator;
import java.util.List;

/**
 * Created by Artyom Zheltyshev on 10.01.2024
 */
@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final TeamRepository teamRepository;
    private final GymRepository gymRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Game scheduleGame(Game game) {
        Game saved = gameRepository.save(game);
        eventPublisher.publishEvent(new GameScheduled(saved.getId()));
        return saved;
    }

    public List<Option<Long>> gymOptions() {
        return gymRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Gym::getName))
                .map(gym -> new Option<>(gym.getId(), gym.getName()))
                .toList();
    }

    public List<Option<Long>> ownerOptions() {
        return teamOptions();
    }

    public List<Option<Long>> guestOptions() {
        return teamOptions();
    }

    public List<Option<Long>> teamOptions() {
        return teamRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Team::getName))
                .map(team -> new Option<>(team.getId(), team.getName()))
                .toList();
    }

}
