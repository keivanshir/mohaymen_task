package com.example.mohaymen_task.service;

import com.example.mohaymen_task.dto.Response;
import com.example.mohaymen_task.dto.UserDto;

public interface UserService {

    Response<UserDto> createUser(UserDto userDto);

    Response<UserDto> updateUser(Long id, UserDto userDto);

    Response<UserDto> deleteUser(Long userId);

}
