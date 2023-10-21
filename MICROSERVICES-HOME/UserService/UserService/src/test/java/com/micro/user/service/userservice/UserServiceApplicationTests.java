package com.micro.user.service.userservice;

import com.micro.user.service.userservice.entities.Rating;
import com.micro.user.service.userservice.external.services.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}


	//testing of createRatings feign client
	// @Autowired
//	private RatingService ratingService;
//	@Test
//	void createRating(){
//		Rating rating = Rating.builder()
//						.rating(10)
//						.userId("")
//						.hotelId("")
//						.feedback("This is created using feign client").build();
//		Rating saveRating = ratingService.createRating(rating);
//		System.out.println("New rating is created !!!!");
//	}
}
