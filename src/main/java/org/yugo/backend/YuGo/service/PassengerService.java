package org.yugo.backend.YuGo.service;

import org.yugo.backend.YuGo.model.User;

import java.util.List;
import java.util.Optional;

public interface PassengerService {
    User add(User user);

    List<User> getAll();

    Optional<User> get(Integer id);
}
