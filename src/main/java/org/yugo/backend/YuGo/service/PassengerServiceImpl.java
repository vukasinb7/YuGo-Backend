package org.yugo.backend.YuGo.service;

import jakarta.persistence.PersistenceException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exceptions.BadRequestException;
import org.yugo.backend.YuGo.exceptions.EmailDuplicateException;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.repository.PassengerRepository;
import org.yugo.backend.YuGo.repository.RideRepository;
import org.yugo.backend.YuGo.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository){
        this.passengerRepository = passengerRepository;
    }

    @Override
    public Passenger insert(Passenger passenger) throws EmailDuplicateException {
        try{
            return passengerRepository.save(passenger);
        }catch (DataIntegrityViolationException e){
            throw new EmailDuplicateException("Email is already being used by another user");
        }
    }

    @Override
    public Optional<Passenger> get(Integer id) {
        return passengerRepository.findById(id);
    }

    @Override
    public Passenger update(Passenger passengerUpdate){
        Optional<Passenger> passengerOpt = get(passengerUpdate.getId());
        if (passengerOpt.isPresent()){
            Passenger passenger = passengerOpt.get();
            passenger.setName(passengerUpdate.getName());
            passenger.setSurname(passengerUpdate.getSurname());
            passenger.setProfilePicture(passengerUpdate.getProfilePicture());
            passenger.setTelephoneNumber(passengerUpdate.getTelephoneNumber());
            passenger.setEmail(passengerUpdate.getEmail());
            passenger.setAddress(passengerUpdate.getAddress());
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
