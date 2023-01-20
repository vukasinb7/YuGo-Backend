package org.yugo.backend.YuGo.service;

public interface WebSocketService {
    void sendRideRequestToDriver(Integer driverID, Integer rideID);

    void notifyPassengerAboutRide(Integer rideID, Integer passengerID);

    void notifyPassengerAboutRideEnd(Integer passengerID);

    void notifyPassengerAboutRideStart(Integer passengerID);

    void notifyPassengerAboutVehicleLocation(Integer passengerID, double longitude, double latitude);
}
