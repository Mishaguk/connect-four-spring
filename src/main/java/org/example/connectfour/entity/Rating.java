package org.example.connectfour.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@NamedQuery( name = "Rating.getAverage", query = "SELECT AVG(r.rating) FROM Rating r WHERE r.game =:game")
@NamedQuery(name = "Rating.getByName", query = "SELECT r.rating FROM Rating r WHERE r.game=:game AND r.player=:player")
@NamedQuery(name = "Rating.updateRating", query = "UPDATE Rating r SET " +
        "r.rating=:rating, r.ratedOn=:ratedOn WHERE " +
        "r.game=:game AND r.player=:player")
@NamedQuery( name = "Rating.resetRating",
        query = "DELETE FROM Rating ")

public class Rating {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ident;


    @Getter
    @Setter
    private String player;
    @Getter @Setter
    private String game;
    @Getter @Setter
    private int rating;
    @Getter @Setter
    private Date ratedOn;

    public Rating() {}

    public Rating(String game, String player, int rating, Date ratedOn) {
        this.player = player;
        this.game = game;
        this.rating = rating;
        this.ratedOn = ratedOn;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", rating=" + rating +
                ", ratedOn=" + ratedOn +
                '}';
    }

}
