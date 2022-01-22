package testim.service;

import testim.httpupload.entity.User;

public interface UserService {

    User findOne(String email);

    //Collection<User> findByRole(String role);

    User update(User user);
}
