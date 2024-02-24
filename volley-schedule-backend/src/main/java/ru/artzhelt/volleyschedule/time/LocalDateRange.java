package ru.artzhelt.volleyschedule.time;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Created by Artyom Zheltyshev on 18.02.2024
 */
@Getter
@Setter
@NoArgsConstructor
public class LocalDateRange {

    private LocalDate start;
    private LocalDate end;

}
