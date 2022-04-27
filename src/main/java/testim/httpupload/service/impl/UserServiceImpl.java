package testim.httpupload.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import testim.httpupload.domain.User;
import testim.httpupload.repository.UserRepository;
import testim.httpupload.service.UserService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Long join(User user) {
        validateDuplicateMember(user);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public void validateDuplicateMember(User user) {
        List<User> findUsers = userRepository.findByName(user.getName());
        if (!findUsers.isEmpty()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }

    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Long userId) {
        return userRepository.findOne(userId);
    }
}
