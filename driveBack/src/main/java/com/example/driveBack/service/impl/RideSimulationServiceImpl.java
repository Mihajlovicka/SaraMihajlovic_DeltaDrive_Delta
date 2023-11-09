package com.example.driveBack.service.impl;

import com.example.driveBack.model.Position;
import com.example.driveBack.model.Ride;
import com.example.driveBack.model.RideSimulation;
import com.example.driveBack.repo.RideSimulationRepository;
import com.example.driveBack.service.RideSimulationService;
import com.example.driveBack.service.RoutingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RideSimulationServiceImpl implements RideSimulationService {
    @Autowired
    RideSimulationRepository rideSimulationRepository;
    @Autowired
    RoutingService routingService;

    @Override
    public void newSimulation(Ride ride, Position start, Position end){
        List<Position> positions = routingService.getPositionsFromRoute(
                new Position(ride.getVehicle().getCurrentPosition().getY(),
                        ride.getVehicle().getCurrentPosition().getX()),
                start,end);

        rideSimulationRepository.save(new RideSimulation(ride, positions));
    }
}
