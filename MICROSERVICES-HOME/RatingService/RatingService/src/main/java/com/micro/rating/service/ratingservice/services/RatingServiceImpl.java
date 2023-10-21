package com.micro.rating.service.ratingservice.services;

import com.micro.rating.service.ratingservice.entities.Rating;
import com.micro.rating.service.ratingservice.repositories.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingServiceInt{
    @Autowired
    private RatingRepository ratingRepository;

    //create rating
    @Override
    public Rating createRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    //get all ratings
    @Override
    public List<Rating> getRatings() {
        return ratingRepository.findAll();
    }

    //get rating by userId
    @Override
    public List<Rating> getRatingByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    //get rating by hotelId
    @Override
    public List<Rating> getRatingByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }
}
