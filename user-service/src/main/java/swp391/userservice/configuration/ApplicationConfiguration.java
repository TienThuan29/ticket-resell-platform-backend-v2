package swp391.userservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import swp391.userservice.exception.def.NotFoundException;
import swp391.userservice.repository.UserRepository;

/**
 * Author: Nguyen Tien Thuan
 */
@Configuration
public class ApplicationConfiguration {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageConfiguration messageConfig;

    @Bean
    public UserDetailsService userDetailsService(){
        return username -> userRepository.findEnableAccount(username)
                .orElseThrow(() -> new NotFoundException(messageConfig.ERROR_USERNAME_NOTFOUND));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthProvider = new DaoAuthenticationProvider();
        daoAuthProvider.setUserDetailsService(userDetailsService());
        daoAuthProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthProvider;
    }

}
