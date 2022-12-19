package org.yugo.backend.YuGo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yugo.backend.YuGo.model.Driver;
import org.yugo.backend.YuGo.model.Passenger;
import org.yugo.backend.YuGo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM USERS u WHERE u.user_type = 'DRIVER'",
            nativeQuery = true)
    public List<User> findAllDrivers();

    @Query(value = "SELECT * FROM USERS WHERE user_type = 'DRIVER'", nativeQuery = true)
    public Page<User> findAllDrivers(Pageable page);

    @Query(value = "SELECT * FROM USERS WHERE user_type = 'DRIVER' AND id=?1", nativeQuery = true)
    public Optional<Driver> findDriverById(Integer id);

    @Query(value = "SELECT u FROM User u")
    public Page<User> findAllUsers(Pageable page);

    @Query(value = "SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.isBlocked = false AND u.isActive = true")
    public User authenticateUser(@Param("email") String email,@Param("password") String password);
}
