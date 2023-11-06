package com.example.driveBack.dto;

import com.example.driveBack.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String email;

    public UserDTO(User user){
        email = user.getEmail();
    }
}
