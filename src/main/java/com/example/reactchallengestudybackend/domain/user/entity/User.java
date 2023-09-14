package com.example.reactchallengestudybackend.domain.user.entity;

import com.example.reactchallengestudybackend.domain.base.BaseTimeEntity;
import com.example.reactchallengestudybackend.domain.user.dto.Address;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
@ToString(exclude = {"roles"})
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    private String name;

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 100)
    private String password;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    // 단개로 Role 관리
    public enum RoleType {
        USER, ADMIN
    }

    // 복수개로 Role 관리, 중간매핑 테이블 구현
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
    )
    private Set<Role> roles;

    @Builder
    public User(Long id, String name, String email, String password, Address address, RoleType role, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
        this.roles = roles;
    }
}
