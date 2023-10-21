package com.micro.hotel.service.hotelservice.repositories;

import com.micro.hotel.service.hotelservice.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
