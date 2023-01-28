package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.Message;

public interface WebSocketService {
    void notifyUserAboutMessage(Integer userId, Message message);
    void notifyAdminsAboutPanic(Integer panicId);
    void sendRideRequestToDriver(Integer driverID, Integer rideID);
    void notifyPassengerAboutRide(Integer rideID, Integer passengerID);
    void notifyPassengerAboutRideEnd(Integer passengerID);
    void notifyPassengerAboutRideStart(Integer passengerID);
    void notifyPassengerAboutVehicleLocation(Integer passengerID, double longitude, double latitude);

    void notifyAddedPassenger(Integer passengerID, Integer rideID);

    void notifyPassengerThatVehicleHasArrived(Integer passengerID, Integer rideID);
}
