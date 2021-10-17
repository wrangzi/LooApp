package com.java.looapp;

import com.java.looapp.Validator.EmailValidator;
import com.java.looapp.Validator.PasswordValidator;
import com.java.looapp.Users.User;
import com.java.looapp.Users.UserRepository;
import com.java.looapp.Validator.PhoneValidator;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Java6Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repo;

    @Test
    public void testAddNew(){
        User user = new User();
        PasswordValidator passVal = new PasswordValidator();
        PhoneValidator phoneVal = new PhoneValidator();
        EmailValidator EmailValidator = new EmailValidator();
        String email = "abc@gmail.com";
        String password="nda21A!@";
        String phone_num ="939-9399-2122";
        user.setId("Gcs190039");
        if(EmailValidator.isValid(email))
        {
            user.setEmail(email);
        }
        user.setAddress("17-10");
        user.setUsername("Duy Nguyen");
        if(passVal.isValid(password)) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPassword = encoder.encode(password);
            user.setPassword(encodedPassword);
        }
        if(phoneVal.isValid(phone_num)) {
            user.setPhone_num(phone_num);
        }
        user.setEmoji("fun.jpg");
        user.setActive(true);

        User saveUser = repo.save(user);
        User existUser = entityManager.find(User.class, saveUser.getId());
        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }
}
