package com.micro.hotel.service.hotelservice.services;

import com.micro.hotel.service.hotelservice.entities.Hotel;

import java.util.List;

public interface HotelServiceInt {
    //create hotel
    Hotel saveHotel(Hotel hotel);

    //get single hotel
    Hotel getHotel(String hotelId);

    //get all hotel
    List<Hotel> getAllhotel();

    //update hotel
    Hotel updateHotel(Hotel hotel);

    //delete hotel
    Hotel deleteHotel(String hotelId);
}
