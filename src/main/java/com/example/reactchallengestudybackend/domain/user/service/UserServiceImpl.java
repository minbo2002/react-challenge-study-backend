package com.example.reactchallengestudybackend.domain.user.service;

import com.example.reactchallengestudybackend.common.exception.CustomException;
import com.example.reactchallengestudybackend.common.exception.ErrorCode;
import com.example.reactchallengestudybackend.domain.user.dto.request.SignUpRequest;
import com.example.reactchallengestudybackend.domain.user.dto.response.UserInfoResponse;
import com.example.reactchallengestudybackend.domain.user.entity.Role;
import com.example.reactchallengestudybackend.domain.user.entity.User;
import com.example.reactchallengestudybackend.domain.user.repository.RoleRepository;
import com.example.reactchallengestudybackend.domain.user.repository.UserRepository;
import com.example.reactchallengestudybackend.utils.encoder.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoderUtil passwordEncoderUtil;

    // 회원가입
    @Transactional
    @Override
    public void registerUser(SignUpRequest signUpDto) {
        log.info("userService registerUser run");

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new CustomException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        // create user object
        // test role : admin
        Role role = roleRepository.findByName("ADMIN")
                .orElseThrow(() -> new CustomException(ErrorCode.ROLE_NOT_FOUND));
        log.info("role: {}", role);

        User user = User.builder()
                .name(signUpDto.getName())
                .email(signUpDto.getEmail())
                .password(passwordEncoderUtil.encodePassword(signUpDto.getPassword()))
                .role(User.RoleType.ADMIN)
                .roles(Collections.singleton(role))
                .build();

        userRepository.save(user);
    }

    // 회원정보 조회
    @Override
    public UserInfoResponse getUser(Long userId) {
        log.info("userService getUser run");

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        return UserInfoResponse.from(user);
    }
}
