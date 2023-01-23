package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.dto.ReportOut;
import org.yugo.backend.YuGo.model.Ride;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    private final PassengerService passengerService;
    private final RideService rideService;
    private final RoutingService routingService;

    @Autowired
    public ReportServiceImpl(PassengerService passengerService,RideService rideService, RoutingService routingService){
        this.passengerService=passengerService;
        this.rideService=rideService;
        this.routingService=routingService;
    }
    @Override
    public ReportOut getNumberOfRidesByUser(Integer userId, LocalDateTime from, LocalDateTime to){
        List<Ride> rides;
        try{
            rides=rideService.getPassengerRidesNonPagable(userId,from,to);
        }catch (Exception e){
            rides=rideService.getRidesByDriverNonPageable(userId,from,to);
        }
        HashMap<LocalDate,Double> result= new HashMap<LocalDate, Double>();
        for (Ride ride:rides) {
            if (result.containsKey(ride.getStartTime().toLocalDate()))
                result.put(ride.getStartTime().toLocalDate(), result.get(ride.getStartTime().toLocalDate())+1);
            else
                result.put(ride.getStartTime().toLocalDate(), 1.0);
        }
        ArrayList<LocalDate> sortedKeys
                = new ArrayList<LocalDate>(result.keySet());

        Collections.sort(sortedKeys);
        ArrayList<Double> sortedValues=new ArrayList<>();
        for (LocalDate key:sortedKeys) {
            sortedValues.add(result.get(key));
        }

        return new ReportOut(sortedKeys,sortedValues);


    }

    @Override
    public ReportOut getTotalNumberOfRides(LocalDateTime from, LocalDateTime to){
        List<Ride> rides=rideService.getAllByDate(from,to);
        HashMap<LocalDate,Double> result= new HashMap<LocalDate, Double>();
        for (Ride ride:rides) {
            if (result.containsKey(ride.getStartTime().toLocalDate()))
                result.put(ride.getStartTime().toLocalDate(), result.get(ride.getStartTime().toLocalDate())+1);
            else
                result.put(ride.getStartTime().toLocalDate(), 1.0);
        }
        ArrayList<LocalDate> sortedKeys
                = new ArrayList<LocalDate>(result.keySet());

        Collections.sort(sortedKeys);
        ArrayList<Double> sortedValues=new ArrayList<>();
        for (LocalDate key:sortedKeys) {
            sortedValues.add(result.get(key));
        }

        return new ReportOut(sortedKeys,sortedValues);


    }

    @Override
    public ReportOut getTotalCostOfRidesByUser(Integer userId, LocalDateTime from, LocalDateTime to){
        List<Ride> rides;
        try{
            rides=rideService.getPassengerRidesNonPagable(userId,from,to);
        }catch (Exception e){
            rides=rideService.getRidesByDriverNonPageable(userId,from,to);
        }
        HashMap<LocalDate,Double> result= new HashMap<LocalDate, Double>();
        for (Ride ride:rides) {
            if (result.containsKey(ride.getStartTime().toLocalDate()))
                result.put(ride.getStartTime().toLocalDate(), result.get(ride.getStartTime().toLocalDate())+ride.getTotalCost());
            else
                result.put(ride.getStartTime().toLocalDate(), ride.getTotalCost());
        }
        ArrayList<LocalDate> sortedKeys
                = new ArrayList<LocalDate>(result.keySet());

        Collections.sort(sortedKeys);
        ArrayList<Double> sortedValues=new ArrayList<>();
        for (LocalDate key:sortedKeys) {
            sortedValues.add(result.get(key));
        }

        return new ReportOut(sortedKeys,sortedValues);


    }

    @Override
    public ReportOut getTotalCostOfRides(LocalDateTime from, LocalDateTime to){
        List<Ride> rides=rideService.getAllByDate(from,to);
        HashMap<LocalDate,Double> result= new HashMap<LocalDate, Double>();
        for (Ride ride:rides) {
            if (result.containsKey(ride.getStartTime().toLocalDate()))
                result.put(ride.getStartTime().toLocalDate(), result.get(ride.getStartTime().toLocalDate())+ride.getTotalCost());
            else
                result.put(ride.getStartTime().toLocalDate(), ride.getTotalCost());
        }
        ArrayList<LocalDate> sortedKeys
                = new ArrayList<LocalDate>(result.keySet());

        Collections.sort(sortedKeys);
        ArrayList<Double> sortedValues=new ArrayList<>();
        for (LocalDate key:sortedKeys) {
            sortedValues.add(result.get(key));
        }

        return new ReportOut(sortedKeys,sortedValues);


    }

    @Override
    public ReportOut getDistanceByUser(Integer userId, LocalDateTime from, LocalDateTime to){
        List<Ride> rides;
        try{
            rides=rideService.getPassengerRidesNonPagable(userId,from,to);
        }catch (Exception e){
            rides=rideService.getRidesByDriverNonPageable(userId,from,to);
        }
        HashMap<LocalDate,Double> result= new HashMap<LocalDate, Double>();
        for (Ride ride:rides) {
            Double distance=routingService.getRouteProperties(ride.getLocations().get(0).getDeparture().getLatitude(),ride.getLocations().get(0).getDeparture().getLongitude(),ride.getLocations().get(0).getDestination().getLatitude(),ride.getLocations().get(0).getDestination().getLongitude()).getDistance()/1000;
            if (result.containsKey(ride.getStartTime().toLocalDate()))
                result.put(ride.getStartTime().toLocalDate(), result.get(ride.getStartTime().toLocalDate())+distance);
            else
                result.put(ride.getStartTime().toLocalDate(), distance);
        }
        ArrayList<LocalDate> sortedKeys
                = new ArrayList<LocalDate>(result.keySet());

        Collections.sort(sortedKeys);
        ArrayList<Double> sortedValues=new ArrayList<>();
        for (LocalDate key:sortedKeys) {
            sortedValues.add(result.get(key));
        }

        return new ReportOut(sortedKeys,sortedValues);


    }

    @Override
    public ReportOut getTotalDistance( LocalDateTime from, LocalDateTime to){
        List<Ride> rides=rideService.getAllByDate(from,to);
        HashMap<LocalDate,Double> result= new HashMap<LocalDate, Double>();
        for (Ride ride:rides) {
            Double distance=routingService.getRouteProperties(ride.getLocations().get(0).getDeparture().getLatitude(),ride.getLocations().get(0).getDeparture().getLongitude(),ride.getLocations().get(0).getDestination().getLatitude(),ride.getLocations().get(0).getDestination().getLongitude()).getDistance()/1000;
            if (result.containsKey(ride.getStartTime().toLocalDate()))
                result.put(ride.getStartTime().toLocalDate(), result.get(ride.getStartTime().toLocalDate())+distance);
            else
                result.put(ride.getStartTime().toLocalDate(), distance);
        }
        ArrayList<LocalDate> sortedKeys
                = new ArrayList<LocalDate>(result.keySet());

        Collections.sort(sortedKeys);
        ArrayList<Double> sortedValues=new ArrayList<>();
        for (LocalDate key:sortedKeys) {
            sortedValues.add(result.get(key));
        }

        return new ReportOut(sortedKeys,sortedValues);


    }


}
