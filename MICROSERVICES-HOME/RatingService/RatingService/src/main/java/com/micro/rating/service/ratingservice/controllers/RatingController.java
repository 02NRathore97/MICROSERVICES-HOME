package com.micro.rating.service.ratingservice.controllers;

import com.micro.rating.service.ratingservice.entities.Rating;
import com.micro.rating.service.ratingservice.services.RatingServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingServiceInt ratingService;

    //create rating
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
        Rating rating1 = ratingService.createRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(rating1);
    }

    //get all ratings
    @PreAuthorize("hasAuthority('SCOPE_internal')|| hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Rating>> getRatings(){
        return ResponseEntity.ok(ratingService.getRatings());

    }
    //get rating by userId
    @PreAuthorize("hasAuthority('SCOPE_internal')|| hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    public ResponseEntity<List<Rating>> getRatingByUserId(@PathVariable String userId){
        return ResponseEntity.ok(ratingService.getRatingByUserId(userId));
    }
    //get rating by hotelId
    @PreAuthorize("hasAuthority('SCOPE_internal')|| hasAuthority('Admin')")
    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<List<Rating>> getRatingByHotelId(@PathVariable String hotelId){
     return ResponseEntity.ok(ratingService.getRatingByHotelId(hotelId));
    }
}
