package com.showtime.authserver.bean;

import com.showtime.authserver.security.CassandraTokenStore;
import com.showtime.authserver.utils.SecurityHelper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * @author Vengatesan Nagarajan
 */
@Configuration
public class BeanConfiguration {

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder(SecurityHelper.ROUNDS);
    }

    @Bean
    public TokenStore tokenStore() {
        return new CassandraTokenStore();
    }

    @Bean
    public AuthenticationKeyGenerator getAuthenticationKeyGenerator() {
        return new DefaultAuthenticationKeyGenerator();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }


}