package co.com.poli.userservice;

import co.com.poli.userservice.clientFeing.BookingClient;
import co.com.poli.userservice.model.entity.User;
import co.com.poli.userservice.persistence.repository.UserRepository;
import co.com.poli.userservice.service.UserService;
import co.com.poli.userservice.service.UserServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserServiceMockTest {

    @Mock
    private UserRepository userRepository;
    private BookingClient bookingClient;
    private UserService userService;

    @BeforeEach
    public void begin(){
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository,bookingClient);

        User user = User.builder()
                .id(5L)
                .name("NameUser")
                .lastName("LastNameUser").build();
        Mockito.when(userRepository.findById(5L))
                .thenReturn(Optional.of(user));
    }

    @Test
    public void when_findById_return_User(){
        Optional<User> user = userService.findById(5L);
        Assertions.assertThat(user.get().getName()).isEqualTo("NameUser");
        Assertions.assertThat(user.get().getLastName()).isEqualTo("LastNameUser");
    }
}
