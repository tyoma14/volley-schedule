package ru.artzhelt.volleyschedule.gym.stomp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.artzhelt.volleyschedule.gym.GymAdded;

/**
 * Created by Artyom Zheltyshev on 19.02.2024
 */
@Component
@RequiredArgsConstructor
public class GymStompPublisher {

    private final SimpMessagingTemplate messagingTemplate;

    @EventListener
    public void onGymAdded(GymAdded event) {
        messagingTemplate.convertAndSend("/topic/gym", event);
    }

}
