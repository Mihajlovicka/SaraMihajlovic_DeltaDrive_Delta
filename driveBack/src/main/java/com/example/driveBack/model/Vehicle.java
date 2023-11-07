package com.example.driveBack.model;

import com.vividsolutions.jts.geom.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private Driver driver;
    private double startPrice;
    private double pricePerKm;
    private VehicleState state = VehicleState.FREE;
    @Column(columnDefinition = "POINT")
    private Point currentPosition;

}
