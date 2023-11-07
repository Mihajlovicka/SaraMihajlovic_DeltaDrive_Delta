package com.example.driveBack.repo;

import com.example.driveBack.model.Vehicle;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT *, ST_Distance(current_position, ST_GeomFromText(:point)) AS distance " +
            "FROM vehicle " +
            "WHERE state = 0 " +
            "ORDER BY distance " +
            "LIMIT 10", nativeQuery = true)
    List<Object[]> findNearestVehicles(@Param("point") String point);

}
