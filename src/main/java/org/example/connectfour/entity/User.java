package org.example.connectfour.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@NamedQuery(name = "User.login", query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
@NamedQuery(name="User.isExists", query = "SELECT COUNT(u) FROM User u WHERE u.username = :username")
    @NamedQuery(name = "User.findUserByName", query = "SELECT u FROM User u WHERE u.username = :username")
@Table(name = "users")
public class User {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ident;

    @Getter @Setter
    private String username;
    @Getter @Setter
    private String password;





}
