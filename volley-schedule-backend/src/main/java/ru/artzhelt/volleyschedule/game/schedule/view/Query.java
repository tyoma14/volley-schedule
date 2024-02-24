package ru.artzhelt.volleyschedule.game.schedule.view;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.artzhelt.volleyschedule.time.LocalDateRange;

/**
 * Created by Artyom Zheltyshev on 19.02.2024
 */
@Getter
@Setter
@NoArgsConstructor
public class Query {

    private LocalDateRange period;

}
