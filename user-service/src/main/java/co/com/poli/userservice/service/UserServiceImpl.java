package co.com.poli.userservice.service;

import co.com.poli.userservice.clientFeing.BookingClient;
import co.com.poli.userservice.model.Booking;
import co.com.poli.userservice.model.entity.User;
import co.com.poli.userservice.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BookingClient bookingClient;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public String delete(Long id) {
        if (userRepository.existsById(id) && !bookingExist(id)){
            userRepository.deleteById(id);
            return "USUARIO ELIMINADO";
        } else if (userRepository.existsById(id)) {
            return "USUARIO EN USO";
        }
        return "USUARIO NO ENCONTRADO";
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    private Boolean bookingExist(Long id) {
        Integer states = bookingClient.findBookingByUserId(id).getCode();
        return states != 404;
    }
}
