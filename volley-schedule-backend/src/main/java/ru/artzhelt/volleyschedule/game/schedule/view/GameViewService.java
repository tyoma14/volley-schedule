package ru.artzhelt.volleyschedule.game.schedule.view;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.artzhelt.volleyschedule.time.LocalDateRange;

import java.util.List;

/**
 * Created by Artyom Zheltyshev on 18.02.2024
 */
@Service
@RequiredArgsConstructor
public class GameViewService {

    private final GameViewRepository gameViewRepository;

    public List<GameView> gamesForPeriod(LocalDateRange period) {
        return gameViewRepository.findAll(forPeriod(period));
    }

    private static Specification<GameView> forPeriod(LocalDateRange period) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("date"), period.getStart(), period.getEnd());
    }

}
