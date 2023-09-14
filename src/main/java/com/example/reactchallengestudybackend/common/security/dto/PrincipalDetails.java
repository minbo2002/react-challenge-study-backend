package com.example.reactchallengestudybackend.common.security.dto;

import com.example.reactchallengestudybackend.domain.user.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class PrincipalDetails implements UserDetails {

    private User user;

    public PrincipalDetails(User user) {    // PrincipalDetails 안에 User 정보를 넣기 위해 생성자에 셋팅!
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        /* 단수 role 형태일때
           Collection<GrantedAuthority> collections = new ArrayList<>();
           collections.add(() -> String.valueOf(user.getRole()));
           return collections;   */
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
