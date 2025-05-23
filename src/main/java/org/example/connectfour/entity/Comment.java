package org.example.connectfour.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Entity
@NamedQuery(name = "Comment.getComments", query = "SELECT c FROM Comment c WHERE c.game=:game")
@NamedQuery(name = "Comment.resetComments", query = "DELETE FROM Comment ")
public class Comment {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ident;

    @Getter @Setter
    private String player;
    @Getter @Setter
    private String game;
    @Getter @Setter
    private String comment;
    @Getter @Setter
    private Date commentedOn;

    public Comment() {}

    public Comment(String game, String player, String comment, Date commentedOn) {
        this.player = player;
        this.game = game;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }

    public String toString() {
        return "Comment{" +
                "game='" + game + '\'' +
                ", player='" + player + '\'' +
                ", comment=" + comment +
                ", commentedOn=" + commentedOn +
                '}';
    }

}
