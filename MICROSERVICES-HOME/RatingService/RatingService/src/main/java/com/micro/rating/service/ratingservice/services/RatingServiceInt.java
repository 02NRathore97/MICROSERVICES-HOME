package com.micro.rating.service.ratingservice.services;

import com.micro.rating.service.ratingservice.entities.Rating;

import java.util.List;

public interface RatingServiceInt {
    //create rating
    Rating createRating(Rating rating);

    //get all ratings
    List<Rating> getRatings();

    //get ratings by userId
    List<Rating> getRatingByUserId(String userId);

    //get ratings by hotelId
    List<Rating> getRatingByHotelId(String hotelId);
}
