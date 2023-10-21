package com.micro.user.service.userservice.external.services;

import com.micro.user.service.userservice.entities.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="HOTEL-SERVICE")
public interface HotelService {
    @GetMapping("/hotels/{hotelId}")
    Hotel getHotel(@PathVariable String hotelId);
}