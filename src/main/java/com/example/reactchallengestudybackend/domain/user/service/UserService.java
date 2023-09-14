package com.example.reactchallengestudybackend.domain.user.service;

import com.example.reactchallengestudybackend.domain.user.dto.request.SignUpDto;
import com.example.reactchallengestudybackend.domain.user.dto.response.UserInfoDto;

public interface UserService {

    void registerUser(SignUpDto signUpDto);

    UserInfoDto getUser(Long userId);
}
