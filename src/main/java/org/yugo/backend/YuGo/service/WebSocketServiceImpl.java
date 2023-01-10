package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceImpl implements WebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public WebSocketServiceImpl(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    private class DriverRequest{
        public Integer rideID;
    }
    @Override
    public void sendRideRequestToDriver(Integer driverID, Integer rideID){
        DriverRequest output = new DriverRequest();
        output.rideID = rideID;
        simpMessagingTemplate.convertAndSend("/ride-topic/driver-request/" + driverID, output);
    }

}
