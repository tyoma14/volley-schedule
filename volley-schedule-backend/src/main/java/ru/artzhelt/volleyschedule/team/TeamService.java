package ru.artzhelt.volleyschedule.team;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Artyom Zheltyshev on 11.02.2024
 */
@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Team add(Team team) {
        Team saved = teamRepository.save(team);
        eventPublisher.publishEvent(new TeamAdded(saved.getId()));
        return saved;
    }

    public List<Team> findAll() {
        Sort sort = Sort.by("name");
        return teamRepository.findAll(Specification.anyOf(), sort);
    }

}
