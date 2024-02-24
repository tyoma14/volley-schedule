package ru.artzhelt.volleyschedule.game;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.artzhelt.volleyschedule.core.BaseEntity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Artyom Zheltyshev on 10.01.2024
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Game extends BaseEntity {

    @NotNull
    private Long gymId;

    @NotNull
    @FutureOrPresent
    private LocalDate date;

    @NotNull
    private LocalTime entryTime;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private Long ownerId;

    @NotNull
    private Long guestId;

}
