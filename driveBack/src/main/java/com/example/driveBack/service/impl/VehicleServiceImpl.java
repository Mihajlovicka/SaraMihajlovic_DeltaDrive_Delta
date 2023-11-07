package com.example.driveBack.service.impl;

import com.example.driveBack.model.Position;
import com.example.driveBack.repo.VehicleRepository;
import com.example.driveBack.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Object[]> getNearest(Position position) {
        List<Object[]> nearestVehicles = vehicleRepository.findNearestVehicles(position.point());

        return nearestVehicles;
    }
}
