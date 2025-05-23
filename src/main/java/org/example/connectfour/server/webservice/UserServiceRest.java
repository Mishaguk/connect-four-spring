package org.example.connectfour.server.webservice;


import org.example.connectfour.entity.User;
import org.example.connectfour.service.JwtService;
import org.example.connectfour.service.UserException;
import org.example.connectfour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/auth")
public class UserServiceRest {

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        System.out.println(user.getPassword());
        userService.register(user.getUsername(), user.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            User response = userService.login(user.getUsername(), user.getPassword());
            String token = jwtService.generateToken(response);
            return ResponseEntity.ok(Map.of("token", token, "username", response.getUsername()));
        } catch (UserException.UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User with this login does not exist"));
        } catch (UserException.InvalidPasswordException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid password"));
        }

    }


}
