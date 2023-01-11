package org.yugo.backend.YuGo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yugo.backend.YuGo.exception.BadRequestException;
import org.yugo.backend.YuGo.exception.NotFoundException;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.Role;
import org.yugo.backend.YuGo.model.User;
import org.yugo.backend.YuGo.repository.PassengerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final RoleService roleService;
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailService mailService;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, RoleService roleService,
                                BCryptPasswordEncoder passwordEncoder, MailService mailService,
                                UserService userService){
        this.passengerRepository = passengerRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
        this.userService = userService;
    }

    @Override
    public Passenger insert(Passenger passenger) {
        try{
            ArrayList<Role> roles = new ArrayList<>();
            roles.add(roleService.findRoleByName("ROLE_PASSENGER"));
            passenger.setRoles(roles);
            passenger.setPassword(passwordEncoder.encode(passenger.getPassword()));
            passenger = passengerRepository.save(passenger);
            mailService.sendActivationMail(passenger);
            return passenger;
        }catch (DataIntegrityViolationException e){
            throw new BadRequestException("Email is already being used by another user!");
        }
    }

    @Override
    public Passenger get(Integer id) {
        Optional<Passenger> passengerOpt = passengerRepository.findById(id);
        if (passengerOpt.isPresent()){
            return passengerOpt.get();
        }
        throw new NotFoundException("Passenger does not exist!");
    }

    @Override
    public Passenger update(Passenger passengerUpdate){
        Passenger passenger = get(passengerUpdate.getId());
        try{
            User foundUser = userService.getUserByEmail(passengerUpdate.getEmail());
            if (!foundUser.getEmail().equals(passenger.getEmail())){
                throw new BadRequestException("User with that email already exists");
            }
        }
        catch (NotFoundException ignored){}
        passenger.setName(passengerUpdate.getName());
        passenger.setSurname(passengerUpdate.getSurname());
        passenger.setProfilePicture(passengerUpdate.getProfilePicture());
        passenger.setTelephoneNumber(passengerUpdate.getTelephoneNumber());
        passenger.setEmail(passengerUpdate.getEmail());
        passenger.setAddress(passengerUpdate.getAddress());
        return passengerRepository.save(passenger);
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
