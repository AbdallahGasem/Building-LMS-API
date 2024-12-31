package fcaicu.aswe.lms.config;


import fcaicu.aswe.lms.Repos.UserAuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class WebConfig {
    private final UserAuthRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return id -> userRepository
                    .findById(Integer.valueOf(id))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
