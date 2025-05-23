package org.example.connectfour.service;

import org.example.connectfour.connectFour.core.Game;
import org.example.connectfour.entity.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class CommentServiceTest {

    @Autowired
    CommentService commentService;

    @Test
    void reset() {
        commentService.reset();
        assertEquals(0, commentService.getComments(Game.GAME_TITLE).size());
    }
    @Test
    void addComment() {
        commentService.reset();
        commentService.addComment(new Comment(Game.GAME_TITLE, "testPlayer1", "Game was so interesting", new Date()));
        commentService.addComment(new Comment(Game.GAME_TITLE, "testPlayer2", "Game was more interesting than chess", new Date()));
        List<Comment>  comments = commentService.getComments(Game.GAME_TITLE);
        assertEquals(2, comments.size());
        assertEquals("testPlayer1", comments.get(0).getPlayer());
        assertEquals("Game was more interesting than chess", comments.get(1).getComment());
    }

    @Test
    void getComments() {
        commentService.reset();
        commentService.addComment(new Comment(Game.GAME_TITLE, "testPlayer1", "Game was so interesting", new Date()));
        commentService.addComment(new Comment(Game.GAME_TITLE, "testPlayer2", "Game was more interesting than chess", new Date()));
        commentService.addComment(new Comment(Game.GAME_TITLE, "testPlayer3", "Game was so interesting", new Date()));
        commentService.addComment(new Comment(Game.GAME_TITLE, "testPlayer4", "Game was no interesting", new Date()));
        List<Comment> comments = commentService.getComments(Game.GAME_TITLE);
        assertEquals(4, comments.size());
        assertEquals("testPlayer1", comments.get(0).getPlayer());
    }


}