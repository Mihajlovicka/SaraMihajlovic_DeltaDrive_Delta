package com.example.driveBack.service;

import com.example.driveBack.model.Position;

import java.util.List;

public interface VehicleService {
    List<Object[]> getNearest(Position position);
}
