package com.bhumika.MiniMart.Service;
import com.bhumika.MiniMart.Dto.LoginDto;
import com.bhumika.MiniMart.Dto.UserDto;
import com.bhumika.MiniMart.Entity.User;
import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto registerUser(UserDto userDto);
    UserDto createUser(UserDto userDto);
    UserDto updateUser(Long id, UserDto userDto);
    void deleteUser(Long id);
    String login(LoginDto loginDto);
}

