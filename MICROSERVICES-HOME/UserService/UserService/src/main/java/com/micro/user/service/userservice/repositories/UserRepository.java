package com.micro.user.service.userservice.repositories;


import com.micro.user.service.userservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    //if you want to implement any custom method or query

}
