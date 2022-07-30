package co.com.poli.userservice;

import co.com.poli.userservice.model.entity.User;
import co.com.poli.userservice.persistence.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class UserRepositoryMockTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void when_findAllUser_return_ListUser(){
        User user = User.builder()
                .name("NameUser")
                .lastName("LastNameUser").build();
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.size()).isEqualTo(1);
    }
}
