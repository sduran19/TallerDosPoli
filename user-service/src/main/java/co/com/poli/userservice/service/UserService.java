package co.com.poli.userservice.service;

import co.com.poli.userservice.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user);
    void delete(User user);
    void delete(Long id);
    List<User> findAll();
    Optional<User> findById(Long id);
}
