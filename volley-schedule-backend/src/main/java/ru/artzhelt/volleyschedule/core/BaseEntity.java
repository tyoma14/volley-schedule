package ru.artzhelt.volleyschedule.core;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Artyom Zheltyshev on 11.02.2024
 */
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
public class BaseEntity implements Identifiable {

    @Id
    @GeneratedValue
    private Long id;

}
