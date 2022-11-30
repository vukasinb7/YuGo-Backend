package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM USERS u WHERE u.user_type = 2",
            nativeQuery = true)
    public List<User> findAllDrivers();

    @Query(value = "SELECT * FROM USERS WHERE user_type = 2", nativeQuery = true)
    public Page<User> findAllDrivers(Pageable page);

    @Query(value = "SELECT * FROM USERS u WHERE u.user_type = 1",
            nativeQuery = true)
    public List<User> findAllPassengers();
}
