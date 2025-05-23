package org.example.connectfour.service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.example.connectfour.entity.Rating;

import java.util.List;

@Transactional
public class RatingServiceJPA implements RatingService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void setRating(Rating rating) throws RatingException {

        TypedQuery<Rating> query = entityManager.createQuery(
                "SELECT r FROM Rating r WHERE r.player = :player AND r.game = :game",
                Rating.class
        );
        query.setParameter("player", rating.getPlayer());
        query.setParameter("game", rating.getGame());

        List<Rating> existingRatings = query.getResultList();

        if (!existingRatings.isEmpty()) {

            Rating existing = existingRatings.get(0);
            existing.setRating(rating.getRating());
            existing.setRatedOn(rating.getRatedOn());
            entityManager.merge(existing);
        } else {

            entityManager.persist(rating);
        }

    }

    @Override
    public int getAverageRating(String game) throws RatingException {
        var averageRating = entityManager.createNamedQuery("Rating.getAverage", Double.class).setParameter("game", game).getSingleResult();
        return averageRating != null ? (int)(double)averageRating : 0;
    }

    @Override
    public int getRating(String game, String player) throws RatingException {
        List<Integer> results = entityManager
                .createNamedQuery("Rating.getByName", Integer.class)
                .setParameter("game", game)
                .setParameter("player", player)
                .getResultList();

        if (results.isEmpty()) {
            return 0;
        } else {
            return results.get(0);
        }
    }

    @Override
    public void reset() throws RatingException {
        entityManager.createNamedQuery("Rating.resetRating").executeUpdate();
    }
}
