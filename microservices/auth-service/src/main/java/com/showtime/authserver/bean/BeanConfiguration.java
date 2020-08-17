package com.showtime.authserver.bean;

import com.showtime.authserver.security.CassandraTokenStore;
import com.showtime.authserver.utils.SecurityHelper;
import com.showtime.corelib.constants.KafkaConstants;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

import java.util.Properties;

/**
 * @author Vengatesan Nagarajan
 */
@Configuration
public class BeanConfiguration {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.topic-name}")
    private String topicName;

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

    @Bean("kafka-properties")
    public Properties kafkaProperties() {
        Properties kafkaProperties = new Properties();
        kafkaProperties.setProperty(KafkaConstants.BOOTSTRAP_SERVERS.toString(), bootstrapServers);
        kafkaProperties.setProperty(KafkaConstants.TOPIC_NAME.toString(), topicName);
        return kafkaProperties;
    }

}