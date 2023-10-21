package com.micro.hotel.service.hotelservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="hotels")
public class Hotel {
    @Id
    @Column(name="ID")
    private String hotelId;
    @Column(name="NAME", length = 20)
    private String name;
    @Column(name="LOCATION")
    private String location;
    @Column(name="ABOUT")
    private String about;
}
