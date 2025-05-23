package org.example.connectfour.server.webservice;


import org.example.connectfour.entity.Rating;
import org.example.connectfour.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/rating")
public class RatingServiceRest {
    @Autowired
    RatingService ratingService ;


    @PostMapping
    public void setRating(@RequestBody Rating rating) {

            ratingService.setRating(rating);

    }

    @GetMapping("average/{game}")
    public int getAverageRating(@PathVariable String game) {
        return ratingService.getAverageRating(game) ;
    }

    @GetMapping("/{game}/{player}")
    public int getRating(@PathVariable String game, @PathVariable String player) {
        return ratingService.getRating(game,player);
    }

}
