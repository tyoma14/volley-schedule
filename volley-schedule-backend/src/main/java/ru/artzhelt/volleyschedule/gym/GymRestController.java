package ru.artzhelt.volleyschedule.gym;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Artyom Zheltyshev on 17.02.2024
 */
@RestController
@RequestMapping("/api/gym")
@RequiredArgsConstructor
public class GymRestController {

    private final GymService gymService;

    @PostMapping
    public Gym add(@Valid @RequestBody Gym gym) {
        return gymService.add(gym);
    }

    @GetMapping
    public List<Gym> getAll() {
        return gymService.findAll();
    }

}
