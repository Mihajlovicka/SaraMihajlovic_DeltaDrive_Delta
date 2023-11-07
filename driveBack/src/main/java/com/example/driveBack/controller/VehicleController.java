package com.example.driveBack.controller;

import com.example.driveBack.model.Position;
import com.example.driveBack.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle/")
public class VehicleController {

    @Autowired
    VehicleService rideService;

    @GetMapping("get-nearest/{latitude}/{longitude}")
    public ResponseEntity getNearest(@PathVariable double latitude, @PathVariable double longitude) {
        return new ResponseEntity<>(rideService.getNearest(new Position(latitude, longitude)
        ), HttpStatus.OK);
    }
}
