package testim.httpupload.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testim.httpupload.domain.User;
import testim.httpupload.repository.UserRepository;
import testim.httpupload.service.LoginService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Override
    public User login(String loginId, String password) {
        Optional<User> byLoginId = userRepository.findByLoginId(loginId);
        return byLoginId.filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
