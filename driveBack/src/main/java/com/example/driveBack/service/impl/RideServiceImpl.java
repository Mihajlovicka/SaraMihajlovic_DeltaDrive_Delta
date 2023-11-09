package com.example.driveBack.service.impl;

import com.example.driveBack.dto.RideDTO;
import com.example.driveBack.dto.RidePreview;
import com.example.driveBack.dto.VehiclePreview;
import com.example.driveBack.exception.NotFoundException;
import com.example.driveBack.model.Rating;
import com.example.driveBack.model.Ride;
import com.example.driveBack.model.User;
import com.example.driveBack.model.Vehicle;
import com.example.driveBack.repo.RatingRepository;
import com.example.driveBack.repo.RideRepository;
import com.example.driveBack.repo.VehicleRepository;
import com.example.driveBack.service.RideService;
import com.example.driveBack.service.UserService;
import com.example.driveBack.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {
    @Autowired@Lazy
    VehicleService vehicleService;
    @Autowired
    UserService userService;
    @Autowired
    RideRepository rideRepository;
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public void makeRide(RideDTO rideDTO) {
        Ride ride = new Ride(rideDTO);
        ride.setVehicle(vehicleService.getVehicle(rideDTO.getRideId()));
        ride.setUser(userService.getLoggedIn());
        rideRepository.save(ride);
    }

    @Override
    public List<RidePreview> getUserRides() {
        User u = userService.getLoggedIn();
        return rideRepository.findRideByUser(u).stream()
                .map(RidePreview::new)
                .collect(Collectors.toList());
    }

    @Override
    public void rateRide(Long id, Rating rating) {
        Ride r = rideRepository.findById(id).orElseThrow(()-> new NotFoundException("Ride not found."));
        r.setRating(rating);
        ratingRepository.save(rating);
        rideRepository.save(r);
    }
}
