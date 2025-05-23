package org.example.connectfour.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.connectfour.entity.Score;

import java.util.List;


@Transactional
public class ScoreServiceJPA implements ScoreService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) throws ScoreException {
        entityManager.persist(score);
    }

    @Override
    public List<Score> getTopScores(String game) throws ScoreException {
        return entityManager.createNamedQuery("Score.getTopScores")
                .setParameter("game", game).setMaxResults(5).getResultList();
    }

    @Override
    public List<Score> getScoreByName(String game, String nickname) throws ScoreException {
        return entityManager.createNamedQuery("Score.getScoreByName", Score.class).setParameter("game", game).setParameter("player", nickname).getResultList();
    }

    @Override
    public void reset() {
        entityManager.createNamedQuery("Score.resetScores").executeUpdate();;
    }
}