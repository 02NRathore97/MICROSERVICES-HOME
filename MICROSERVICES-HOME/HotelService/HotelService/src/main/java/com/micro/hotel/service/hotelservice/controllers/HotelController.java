package com.micro.hotel.service.hotelservice.controllers;

import com.micro.hotel.service.hotelservice.entities.Hotel;
import com.micro.hotel.service.hotelservice.services.HotelServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelServiceInt hotelService;

    //create hotel
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.saveHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotel1);
    }
    //get single hotel
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable String hotelId){
        Hotel hotel = hotelService.getHotel(hotelId);
        return ResponseEntity.ok(hotel);
    }
    //get all hotel
    @PreAuthorize("hasAuthority('SCOPE_internal')|| hasAuthority('Admin')")
    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotel(){
        List<Hotel> allHotel = hotelService.getAllhotel();
        return ResponseEntity.ok(allHotel);
    }
    //update hotel
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping
    public ResponseEntity<Hotel> updateHotel(@RequestBody Hotel hotel){
        Hotel hotel1 = hotelService.updateHotel(hotel);
        return ResponseEntity.ok(hotel1);
    }
    //delete hotel
    @PreAuthorize("hasAuthority('Admin')")
    @DeleteMapping("/{hotelId}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable String hotelId){
        Hotel hotel = hotelService.deleteHotel(hotelId);
        return ResponseEntity.ok(hotel);
    }
}
