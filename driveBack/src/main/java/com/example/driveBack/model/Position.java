package com.example.driveBack.model;


import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {
    private double latitude;
    private double longitude;

    public String point(){
        return String.format("POINT(%s %s)",latitude, longitude);
    }


}
