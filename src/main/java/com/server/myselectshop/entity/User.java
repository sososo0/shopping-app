package com.server.myselectshop.entity;

import com.server.myselectshop.dto.SignupRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public User(SignupRequestDto requestDto, UserRoleEnum role) {
        this.username = requestDto.getUsername();
        this.password = requestDto.getPassword();
        this.email = requestDto.getEmail();
        this.role = role;
    }

    public User(String robbie, String encode, String mail, UserRoleEnum userRoleEnum) {
        this.username = robbie;
        this.password = encode;
        this.email = mail;
        this.role = userRoleEnum;
    }
}
