package org.example.connectfour.service;

import org.example.connectfour.connectFour.core.Game;
import org.example.connectfour.entity.Rating;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RatingServiceTest {

    @Autowired
    RatingService ratingService;

    @Test
    void reset() {
        ratingService.reset();
        assertEquals(0,ratingService.getAverageRating(Game.GAME_TITLE));
    }

    @Test


    void setRating() {
        Date date = new Date();
        ratingService.reset();
        ratingService.setRating(new Rating(Game.GAME_TITLE, "testPlayer1", 3, date));
        assertEquals(3, ratingService.getRating(Game.GAME_TITLE, "testPlayer1"));
        ratingService.setRating(new Rating(Game.GAME_TITLE, "testPlayer1", 5, date));
        assertEquals(5, ratingService.getRating(Game.GAME_TITLE, "testPlayer1"));
    }

    @Test
    void getAverageRating() {
        ratingService.reset();
        ratingService.setRating(new Rating(Game.GAME_TITLE, "testPlayer1", 3, new Date()));
        ratingService.setRating(new Rating(Game.GAME_TITLE, "testPlayer2", 5, new Date()));
        ratingService.setRating(new Rating(Game.GAME_TITLE, "testPlayer3", 4, new Date()));
        assertEquals(4, ratingService.getAverageRating(Game.GAME_TITLE));
    }

    @Test
    void getRating() {
        ratingService.reset();
        ratingService.setRating(new Rating(Game.GAME_TITLE, "testPlayer1", 3, new Date()));
        ratingService.setRating(new Rating(Game.GAME_TITLE, "testPlayer2", 5, new Date()));
        ratingService.setRating(new Rating("chess", "testPlayer2", 4, new Date()));
        assertEquals(3, ratingService.getRating(Game.GAME_TITLE, "testPlayer1"));
        assertEquals(5, ratingService.getRating(Game.GAME_TITLE, "testPlayer2"));
        assertEquals(4, ratingService.getRating("chess", "testPlayer2"));
    }


}