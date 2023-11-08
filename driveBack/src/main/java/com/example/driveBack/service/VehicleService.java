package com.example.driveBack.service;

import com.example.driveBack.dto.VehiclePreview;
import com.example.driveBack.dto.VehiclePreviewI;
import com.example.driveBack.model.Position;

import java.util.List;

public interface VehicleService {
    List<VehiclePreview> getNearest(Position currentPosition, Position newPosition);

    boolean bookVehicle(VehiclePreview vehiclePreview);
}
