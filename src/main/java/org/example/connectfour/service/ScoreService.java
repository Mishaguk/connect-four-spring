package org.example.connectfour.service;



import org.example.connectfour.entity.Score;

import java.util.List;

public interface ScoreService {
    void addScore(Score score) throws ScoreException;
    List<Score> getTopScores(String game) throws ScoreException;
    void reset() throws ScoreException;
    List<Score> getScoreByName(String game, String nickname);
}
