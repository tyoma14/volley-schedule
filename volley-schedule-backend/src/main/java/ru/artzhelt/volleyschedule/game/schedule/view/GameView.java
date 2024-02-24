package ru.artzhelt.volleyschedule.game.schedule.view;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import ru.artzhelt.volleyschedule.core.Identifiable;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Artyom Zheltyshev on 18.02.2024
 */
@Entity
@Immutable
@Subselect("select * from game_view")
@Getter
@Setter
@NoArgsConstructor
public class GameView implements Identifiable {

    @Id
    private Long id;
    private String gym;
    private LocalDate date;
    private LocalTime entryTime;
    private LocalTime startTime;
    private String owner;
    private String guest;

}
