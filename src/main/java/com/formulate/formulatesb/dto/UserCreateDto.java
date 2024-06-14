package com.formulate.formulatesb.dto;

import com.formulate.formulatesb.model.User;
import com.formulate.formulatesb.util.PasswordService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class UserCreateDto {
    @Length(min = 2)
    private String firstname;
    @Length(min = 2)
    private String lastname;
    @NotBlank
    @Indexed(unique = true)
    @Email
    private String email;
    @Length(min = 8)
    private String password;

    public User mapToUser() {
        User user = new User();
        user.setFirstname(this.firstname);
        user.setLastname(this.lastname);
        user.setEmail(this.email);
        user.setPassword(PasswordService.hashPassword(this.password));
        return user;
    }
}
