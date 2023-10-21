package com.micro.user.service.userservice.controllers;

import com.micro.user.service.userservice.entities.User;
import com.micro.user.service.userservice.services.UserServiceInt;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServiceInt userService;
    //create
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){

        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

//****************************************************************************

//    //Circuit Breaker
//    //get single user
//    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
//        User user = userService.getUser(userId);
//        return ResponseEntity.ok(user);
//    }
//    //creating fall back method for circuitbreaker
//    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//        logger.info("Fallback is executed because service is down : ",ex.getMessage());
//        User user = User.builder()
//                .email("dummy@gmail.com")
//                .name("Dummy")
//                .about("This user is created dummy because some service is down !!")
//                .userId("12345")
//                .build();
//        return new ResponseEntity<>(user,HttpStatus.OK);
//    }
//

    //*************************************************************************



//    //Retry
//    //get single user
//    int retryCount = 1;
//    @GetMapping("/{userId}")
//    @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
//    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
//        logger.info("Retry count : {} ", retryCount);
//        retryCount++;
//        User user = userService.getUser(userId);
//        return ResponseEntity.ok(user);
//    }
//    //creating fall back method for retry
//
//    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
//        User user = User.builder()
//                .email("dummy@gmail.com")
//                .name("Dummy")
//                .about("This user is created dummy because some service is down !!")
//                .userId("12345")
//                .build();
//        return new ResponseEntity<>(user,HttpStatus.OK);
//    }

//***********************************************************************************

    //RateLimiter
    //get single user
    @GetMapping("/{userId}")
    @RateLimiter(name="userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    //creating fall back method for rateLimiter

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex){
        ex.printStackTrace();
        User user = User.builder()
                .email("dummy@gmail.com")
                .name("Dummy")
                .about("This user is created dummy because some service is down !!")
                .userId("12345")
                .build();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }





    //get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable String userId){
        User user = userService.deleteUser(userId);
        return ResponseEntity.ok(user);
    }
    //update user
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User user){
        User user1 = userService.updateUser(user);
        return ResponseEntity.ok(user);
    }
}
