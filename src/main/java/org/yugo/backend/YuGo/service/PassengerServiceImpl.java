package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.repository.PassengerRepository;
import org.yugo.backend.YuGo.repository.RideRepository;
import org.yugo.backend.YuGo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository){
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Passenger insert(Passenger passenger){
        return passengerRepository.save(passenger);
    }

    @Override
    public Optional<Passenger> get(Integer id) {
        return passengerRepository.findById(id);
    }

    @Override
    public Passenger update(Integer passengerId, Passenger updatedPassenger){
        Optional<Passenger> passengerOpt = get(passengerId);
        if (passengerOpt.isPresent()){
            Passenger passenger = passengerOpt.get();
            passenger.setName(updatedPassenger.getName());
            passenger.setSurname(updatedPassenger.getSurname());
            passenger.setProfilePicture(updatedPassenger.getProfilePicture());
            passenger.setTelephoneNumber(updatedPassenger.getTelephoneNumber());
            passenger.setEmail(updatedPassenger.getEmail());
            passenger.setAddress(updatedPassenger.getAddress());
            passenger.setPassword(updatedPassenger.getPassword());
            return passengerRepository.save(passenger);
        }
        return null;
    }

    @Override
    public List<Passenger> getAll() {
        return passengerRepository.findAllPassengers();
    }

    @Override
    public Page<Passenger> getPassengersPage(Pageable page){
        return passengerRepository.findAllPassengers(page);
    }
}
