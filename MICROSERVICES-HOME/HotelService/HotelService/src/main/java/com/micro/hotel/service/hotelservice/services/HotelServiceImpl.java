package com.micro.hotel.service.hotelservice.services;

import com.micro.hotel.service.hotelservice.entities.Hotel;
import com.micro.hotel.service.hotelservice.exceptions.ResourceNotFoundException;
import com.micro.hotel.service.hotelservice.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelServiceInt{
    @Autowired
    private HotelRepository hotelRepository;

    //create hotel
    @Override
    public Hotel saveHotel(Hotel hotel) {
        String randomHotelId = UUID.randomUUID().toString();
        hotel.setHotelId(randomHotelId);
        return hotelRepository.save(hotel);
    }

    //get single hotel
    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()->new ResourceNotFoundException("Given Hotel id is not found !!"));
    }

    //get all hotel
    @Override
    public List<Hotel> getAllhotel() {
        return hotelRepository.findAll();
    }


    //update hotel
    @Override
    public Hotel updateHotel(Hotel hotel) {
        Hotel hotel1 = getHotel(hotel.getHotelId());
        if(hotel1!=null){
            hotel1.setName(hotel.getName());
            hotel1.setAbout(hotel.getAbout());
            hotel1.setLocation(hotel.getLocation());
        }
        return hotel1;
    }

    //delete hotel
    @Override
    public Hotel deleteHotel(String hotelId) {
        Hotel hotel = getHotel(hotelId);
        hotelRepository.delete(hotel);
        return hotel;
    }
}
