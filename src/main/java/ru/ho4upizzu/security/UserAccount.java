package ru.ho4upizzu.security;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("user.account")
@Setter
public class UserAccount {
    public String name;
    public String password;
}
