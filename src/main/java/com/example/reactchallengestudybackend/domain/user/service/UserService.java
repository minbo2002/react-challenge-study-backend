package com.example.reactchallengestudybackend.domain.user.service;

import com.example.reactchallengestudybackend.domain.user.dto.request.SignUpRequest;
import com.example.reactchallengestudybackend.domain.user.dto.response.UserInfoResponse;

public interface UserService {

    void registerUser(SignUpRequest signUpDto);

    UserInfoResponse getUser(Long userId);
}
