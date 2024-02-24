package ru.artzhelt.volleyschedule.presentation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Artyom Zheltyshev on 12.02.2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Option<T> {

    private T value;
    private String label;

}
