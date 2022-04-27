package testim.httpupload.service;

import testim.httpupload.domain.User;

import java.util.List;

public interface UserService {

    Long join(User user);

    void validateDuplicateMember(User user);

    List<User> findUsers();

    User findOne(Long userId);
}
