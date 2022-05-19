package testim.httpupload.service;


import testim.httpupload.domain.User;

public interface LoginService {

    User login(String loginId, String password);
}
