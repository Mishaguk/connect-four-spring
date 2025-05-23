package org.example.connectfour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.connectfour.service.*;

@SpringBootApplication
@Configuration
@EntityScan("org.example.connectfour.entity")
public class ConnectfourApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConnectfourApplication.class, args);
    }

    @Bean
    ScoreService scoreService() {
        return new ScoreServiceJPA();
    }
    @Bean
    CommentService commentService() {
        return new CommentServiceJPA();
    }
    @Bean
    RatingService ratingService() {
        return new RatingServiceJPA();
    }
    @Bean
    UserService userService(){
        return new UserServiceJPA();
    }
    @Bean
    public JwtService jwtService() {
        return new JwtService();
    }
}
