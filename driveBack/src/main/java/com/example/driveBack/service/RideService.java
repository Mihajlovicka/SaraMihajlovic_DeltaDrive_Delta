package com.example.driveBack.service;

import com.example.driveBack.dto.RideDTO;
import com.example.driveBack.dto.RidePreview;
import com.example.driveBack.model.Rating;

import java.util.List;

public interface RideService {
    void newRide(RideDTO rideDTO);

    List<RidePreview> getUserRides();

    void rateRide(Long id, Rating rating);
}
