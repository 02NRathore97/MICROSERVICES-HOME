package com.micro.user.service.userservice.services;

import com.micro.user.service.userservice.entities.Hotel;
import com.micro.user.service.userservice.entities.Rating;
import com.micro.user.service.userservice.entities.User;
import com.micro.user.service.userservice.exceptions.ResourceNotFoundException;
import com.micro.user.service.userservice.external.services.HotelService;
import com.micro.user.service.userservice.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserServiceInt{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private HotelService hotelService;

    //create user
    @Override
    public User saveUser(User user) {
        //generate unique userId
        String randomUserId = UUID.randomUUID().toString();
        System.out.println(randomUserId);
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    //get all user
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    //get single user
//    @Override
//    public User getUser(String userId) {
//        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !!" + userId));
//    }


    //***************************************************

    //get single user that call rating service and get ratings
//    @Override
//    public User getUser(String userId){
//    User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !!"));
//
//
//    //fetch rating of the above user from RATING SERVICE
//        ArrayList<Rating> ratingsOfUser =  restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), ArrayList.class);
//        logger.info("{} "+ratingsOfUser);
//        user.setRatings(ratingsOfUser);
//        return user;
//    }

    //***************************************************


    //fetch the hotel that is rated by this user from HOTEL SERVICE
    //   @Override
//    public User getUser(String userId){
//        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !!"));
//
//
//        //fetch rating of the above user from RATING SERVICE
//        Rating[] ratingsOfUser =  restTemplate.getForObject("http://localhost:8083/ratings/users/"+user.getUserId(), Rating[].class);
//        logger.info("{} "+ratingsOfUser);
//        List<Rating> ratings =  Arrays.stream(ratingsOfUser).collect(Collectors.toList());
//
//        //fetching hotel from HOTEL SERVICE
//      List<Rating> ratingList =  ratings.stream().map(rating -> {
//            //API call to hotel service to get the hotel
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://localhost:8082/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
//            logger.info("response status code  : {} "+forEntity.getStatusCode());
//            //set the hotel to rating
//          rating.setHotel(hotel);
//            //return the rating
//            return rating;
//        }).collect(Collectors.toList());
//        user.setRatings(ratingList);
//        return user;
//    }

    //**********************************************************

    //replace host with service name
//    @Override
//    public User getUser(String userId){
//        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !!"));
//
//
//        //fetch rating of the above user from RATING SERVICE
//        Rating[] ratingsOfUser =  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
//        logger.info("{} "+ratingsOfUser);
//        List<Rating> ratings =  Arrays.stream(ratingsOfUser).collect(Collectors.toList());
//
//        //fetching hotel from HOTEL SERVICE
//        List<Rating> ratingList =  ratings.stream().map(rating -> {
//            //API call to hotel service to get the hotel
//            ResponseEntity<Hotel> forEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
//            Hotel hotel = forEntity.getBody();
//            logger.info("response status code  : {} "+forEntity.getStatusCode());
//            //set the hotel to rating
//            rating.setHotel(hotel);
//            //return the rating
//            return rating;
//        }).collect(Collectors.toList());
//        user.setRatings(ratingList);
//        return user;
//    }

    //***************************************************************

    @Override
    public User getUser(String userId){
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id is not found on server !!"));


        //fetch rating of the above user from RATING SERVICE
        Rating[] ratingsOfUser =  restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(), Rating[].class);
        logger.info("{} "+ratingsOfUser);
        List<Rating> ratings =  Arrays.stream(ratingsOfUser).collect(Collectors.toList());

        //fetching hotel from HOTEL SERVICE
        List<Rating> ratingList =  ratings.stream().map(rating -> {
            //API call to hotel service to get the hotel from FeignClient
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
            //set the hotel to rating
            rating.setHotel(hotel);
            //return the rating
            return rating;
        }).collect(Collectors.toList());
        user.setRatings(ratingList);
        return user;
    }


    //delete user
    @Override
    public User deleteUser(String userId) {
        User user = getUser(userId);
        userRepository.delete(user);
        return user;
    }


    //update user
    @Override
    public User updateUser(User user) {
        User user1 = getUser(user.getUserId());
        if(user1 != null){
            user1.setName(user.getName());
            user1.setEmail(user.getEmail());
            user1.setAbout(user.getAbout());
            userRepository.save(user1);
        }
        return user1;
    }
}
