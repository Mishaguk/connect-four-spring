package org.example.connectfour.service;

import org.example.connectfour.connectFour.core.Game;
import org.example.connectfour.entity.Score;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ScoreServiceTest {

    @Autowired
    ScoreService scoreService;

    @Test
    void reset() {
        scoreService.reset();
        assertEquals(0, scoreService.getTopScores(Game.GAME_TITLE).size());
    }

    @Test
    void addScore() {
        scoreService.reset();
        scoreService.addScore(new Score(Game.GAME_TITLE, "testPlayer", 440, new Date()));
        Score score = scoreService.getTopScores(Game.GAME_TITLE).get(0);
        assertEquals("testPlayer", score.getPlayer());
        assertEquals(Game.GAME_TITLE, score.getGame());
        assertEquals(440, score.getPoints());

    }

    @Test
    void getTopScores() {
        scoreService.reset();
        scoreService.addScore(new Score(Game.GAME_TITLE, "testPlayer1", 440, new Date()));
        scoreService.addScore(new Score(Game.GAME_TITLE, "testPlayer2", 640, new Date()));
        scoreService.addScore(new Score(Game.GAME_TITLE, "testPlayer3", 240, new Date()));
        scoreService.addScore(new Score("mines", "testPlayer1", 440, new Date()));
        scoreService.addScore(new Score(Game.GAME_TITLE, "testPlayer4", 340, new Date()));
        scoreService.addScore(new Score(Game.GAME_TITLE, "testPlayer5", 110, new Date()));
        scoreService.addScore(new Score(Game.GAME_TITLE, "testPlayer6", 50, new Date()));

        List<Score> topScores = scoreService.getTopScores(Game.GAME_TITLE);
        assertEquals(5, topScores.size());
        assertEquals("testPlayer2", topScores.get(0).getPlayer());
        assertEquals(640, topScores.get(0).getPoints());
    }


}