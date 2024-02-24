package ru.artzhelt.volleyschedule.team.stomp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.artzhelt.volleyschedule.team.TeamAdded;

/**
 * Created by Artyom Zheltyshev on 23.02.2024
 */
@Component
@RequiredArgsConstructor
public class TeamStompPublisher {

    private final SimpMessagingTemplate simpMessagingTemplate;

    @EventListener
    public void onTeamAdded(TeamAdded teamAdded) {
        simpMessagingTemplate.convertAndSend("/topic/team", teamAdded);
    }

}
