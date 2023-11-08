package com.example.driveBack.service.impl;

import com.example.driveBack.dto.RideDTO;
import com.example.driveBack.dto.VehiclePreview;
import com.example.driveBack.model.Ride;
import com.example.driveBack.model.Vehicle;
import com.example.driveBack.repo.RideRepository;
import com.example.driveBack.repo.VehicleRepository;
import com.example.driveBack.service.RideService;
import com.example.driveBack.service.UserService;
import com.example.driveBack.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class RideServiceImpl implements RideService {
    @Autowired@Lazy
    VehicleService vehicleService;
    @Autowired
    UserService userService;
    @Autowired
    RideRepository rideRepository;

    @Override
    public void makeRide(RideDTO rideDTO) {
        Ride ride = new Ride(rideDTO);
        ride.setVehicle(vehicleService.getVehicle(rideDTO.getRideId()));
        ride.setUser(userService.getLoggedIn());
        rideRepository.save(ride);
    }
}

//prikaz svih
