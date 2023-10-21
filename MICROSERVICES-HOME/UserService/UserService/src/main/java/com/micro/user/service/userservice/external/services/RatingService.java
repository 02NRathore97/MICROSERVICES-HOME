package com.micro.user.service.userservice.external.services;

import com.micro.user.service.userservice.entities.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(name="RATING-SERVICE")
public interface RatingService {

    //get
    @GetMapping("/ratings")
    public List<Rating> getRatings();


    //get single rating
    @GetMapping("/ratings/{ratingId}")
    public Rating getRating(@PathVariable String ratingId);


    //post
    @PostMapping("/ratings")
    public Rating createRating(Rating rating);


    //put
    @PutMapping("/ratings/{ratingId}")
    public Rating updateRating(@PathVariable String ratingId, Rating rating);

    //delete
    @DeleteMapping("/ratings/{ratingId}")
    public void deleteRating(@PathVariable String ratingId);
}
