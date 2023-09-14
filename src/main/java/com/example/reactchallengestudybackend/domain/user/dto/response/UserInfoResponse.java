package com.example.reactchallengestudybackend.domain.user.dto.response;

import com.example.reactchallengestudybackend.domain.user.entity.Role;
import com.example.reactchallengestudybackend.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;

@Getter
@ToString
public class UserInfoResponse {

    private String name;
    private String email;
    private User.RoleType role;
    private Set<Role> roles;

    @Builder
    public UserInfoResponse(String name, String email, User.RoleType role, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.role = role;
        this.roles = roles;
    }

    public static UserInfoResponse from(User user) {
        return UserInfoResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .roles(user.getRoles())
                .build();
    }
}
