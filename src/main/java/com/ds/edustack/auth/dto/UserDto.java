package com.ds.edustack.auth.dto;

import com.ds.edustack.auth.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id;
    private String email;
    private String name;
    private UserRole userRole;
}
