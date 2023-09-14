package com.example.reactchallengestudybackend.common.security.service;

import com.example.reactchallengestudybackend.common.security.dto.PrincipalDetails;
import com.example.reactchallengestudybackend.domain.user.entity.User;
import com.example.reactchallengestudybackend.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        User user = userRepository.findByNameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email:" + usernameOrEmail));

        log.info("user: {}", user);

        return new PrincipalDetails(user);
    }
}
