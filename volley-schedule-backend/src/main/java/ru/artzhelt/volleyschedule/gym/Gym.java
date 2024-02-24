package ru.artzhelt.volleyschedule.gym;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.artzhelt.volleyschedule.core.BaseEntity;

/**
 * Created by Artyom Zheltyshev on 10.01.2024
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Gym extends BaseEntity {

    @NotEmpty
    private String name;

    @NotEmpty
    private String address;

}
