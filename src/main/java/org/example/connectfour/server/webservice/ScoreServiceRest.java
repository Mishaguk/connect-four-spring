package org.example.connectfour.server.webservice;

import org.example.connectfour.entity.Score;
import org.example.connectfour.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/score")
public class ScoreServiceRest {

    @Autowired
    private ScoreService scoreService;

    @GetMapping("/{game}")
    public List<Score> getTopScores(@PathVariable String game) {
        return scoreService.getTopScores(game);
    }

    @PostMapping
    public void addScore(@RequestBody Score score) {
        scoreService.addScore(score);
    }

    @GetMapping("/{game}/search")
    public List<Score> getScoreByName(@PathVariable String game, @RequestParam String nickname) {
     return   scoreService.getScoreByName(game, nickname);
    }
}