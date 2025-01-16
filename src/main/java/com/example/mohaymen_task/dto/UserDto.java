package com.example.mohaymen_task.dto;

import com.example.mohaymen_task.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{
    private Long id;
    private String name;
    private String email;
    private String password;
    private UserRole role;
}
