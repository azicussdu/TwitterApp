package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Length(min = 3, message = "Username length is 3 characters minimum")
    @Length(max = 20, message = "Username length is 20 characters maximum")
    private String name;

    @NotEmpty(message = "Please provide a username")
    private String username;

    @Length(min = 5, message = "Password length is 5 characters minimum")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
