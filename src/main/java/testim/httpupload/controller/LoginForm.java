package testim.httpupload.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginForm {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;

}
