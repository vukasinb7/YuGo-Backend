package org.yugo.backend.YuGo.service;

public interface WebSocketService {
    void notifyAdminsAboutPanic(Integer panicId);
    void sendRideRequestToDriver(Integer driverID, Integer rideID);
    void notifyPassengerAboutRide(Integer rideID, Integer passengerID);
    void notifyPassengerAboutRideEnd(Integer passengerID);
    void notifyPassengerAboutRideStart(Integer passengerID);
    void notifyPassengerAboutVehicleLocation(Integer passengerID, double longitude, double latitude);

    void notifyAddedPassenger(Integer passengerID, Integer rideID);

    void notifyPassengerThatVehicleHasArrived(Integer passengerID, Integer rideID);
}
