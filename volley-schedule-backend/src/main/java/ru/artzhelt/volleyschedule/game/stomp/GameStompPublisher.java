package ru.artzhelt.volleyschedule.game.stomp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.artzhelt.volleyschedule.game.GameScheduled;

/**
 * Created by Artyom Zheltyshev on 23.02.2024
 */
@Component
@RequiredArgsConstructor
public class GameStompPublisher {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void onGameScheduled(GameScheduled gameScheduled) {
        simpMessagingTemplate.convertAndSend("/topic/game", gameScheduled);
    }

}
