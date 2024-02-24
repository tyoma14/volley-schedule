package ru.artzhelt.volleyschedule.gym;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Artyom Zheltyshev on 12.02.2024
 */
@Service
@RequiredArgsConstructor
public class GymService {

    private final GymRepository gymRepository;
    private final ApplicationEventPublisher eventPublisher;

    public Gym add(Gym gym) {
        Gym createdGym = gymRepository.save(gym);
        eventPublisher.publishEvent(new GymAdded(createdGym.getId()));
        return createdGym;
    }

    public List<Gym> findAll() {
        Sort sort = Sort.by("name");
        return gymRepository.findAll(Specification.anyOf(), sort);
    }

}
