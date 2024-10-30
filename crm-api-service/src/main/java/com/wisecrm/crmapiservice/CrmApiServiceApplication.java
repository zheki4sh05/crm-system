package com.wisecrm.crmapiservice;

import com.wisecrm.crmapiservice.entity.*;
import com.wisecrm.crmapiservice.exceptions.*;
import com.wisecrm.crmapiservice.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.authentication.dao.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;

@SpringBootApplication
public class CrmApiServiceApplication {

    @Autowired
    private ApiKeyRepository apiKeyRepository;

    public static void main(String[] args) {
        SpringApplication.run(CrmApiServiceApplication.class, args);
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService());

        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService(){

        return (key) -> apiKeyRepository.findByCompanyId(Long.parseLong(key)).orElseThrow(()-> new WrongApiKeyException("not valid api key"));
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
