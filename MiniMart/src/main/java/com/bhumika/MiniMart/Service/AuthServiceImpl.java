package com.bhumika.MiniMart.Service;

import com.bhumika.MiniMart.Dto.UserDto;
import com.bhumika.MiniMart.Entity.Role;
import com.bhumika.MiniMart.Entity.User;
import com.bhumika.MiniMart.Repository.UserRepository;
import com.bhumika.MiniMart.Service.AuthService;
import com.bhumika.MiniMart.Util.DtoMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(UserDto userDto) {
        User user = DtoMapper.toUser(userDto);

        // password encode karna hoga
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        // role agar null h to default CUSTOMER set kar do
        if (user.getRole() == null) {
            user.setRole(Role.CUSTOMER);
        }

        User saved = userRepository.save(user);
        return DtoMapper.toUserDto(saved);
    }
}
