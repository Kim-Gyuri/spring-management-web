package testim.httpupload.service;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import testim.httpupload.domain.User;
import testim.httpupload.repository.UserRepository;

class UserServiceTest {

    UserRepository userRepository = new UserRepository();

    @Test
    void save() {
        //given
        User user = new User("test","test!", "tester");

        //when
        User savedUser = userRepository.save(user);

        //then
        User findUser = userRepository.findById(user.getId());
        Assertions.assertThat(findUser).isEqualTo(savedUser);


    }


}