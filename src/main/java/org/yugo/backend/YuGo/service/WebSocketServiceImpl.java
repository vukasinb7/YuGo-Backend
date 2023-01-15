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

    private class RideDTO{
        public Integer rideID;
    }
    @Override
    public void sendRideRequestToDriver(Integer driverID, Integer rideID){
        RideDTO output = new RideDTO();
        output.rideID = rideID;
        System.out.println("============================");
        System.out.println("Vozac je obavesten");
        System.out.println("============================");
        simpMessagingTemplate.convertAndSend("/ride-topic/driver-request/" + driverID, output);
    }
    @Override
    public void notifyPassengerAboutRide(Integer rideID, Integer passengerID){
        RideDTO output = new RideDTO();
        output.rideID = rideID;
        System.out.println("============================");
        System.out.println("Putnik je obavesten");
        System.out.println("============================");
        simpMessagingTemplate.convertAndSend("/ride-topic/notify-passenger/" + passengerID, output);
    }
    @Override
    public void notifyPassengerAboutRideEnd(Integer passengerID){
        System.out.println("============================");
        System.out.println("Putnik je obavesten");
        System.out.println("============================");
        simpMessagingTemplate.convertAndSend("/ride-topic/notify-passenger-end-ride/" + passengerID,"");
    }

    @Override
    public void notifyPassengerAboutRideStart(Integer passengerID){
        System.out.println("============================");
        System.out.println("Putnik je obavesten");
        System.out.println("============================");
        simpMessagingTemplate.convertAndSend("/ride-topic/notify-passenger-start-ride/" + passengerID,"");
    }

}
