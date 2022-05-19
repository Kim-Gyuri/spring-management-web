package testim.httpupload.controller;

import lombok.Data;

@Data
public class UserForm {

    private String loginId;
    private String password;

    private String name;

    private String city;
    private String street;
    private String zipcode;



}
