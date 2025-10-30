package com.bhumika.MiniMart.Service;

import com.bhumika.MiniMart.Dto.LoginDto;
import com.bhumika.MiniMart.Dto.UserDto;
import com.bhumika.MiniMart.Entity.Role;
import com.bhumika.MiniMart.Entity.User;
import com.bhumika.MiniMart.Exception.InvalidInputException;
import com.bhumika.MiniMart.Exception.ResourceNotFoundException;
import com.bhumika.MiniMart.Repository.UserRepository;
import com.bhumika.MiniMart.Service.UserService;
import com.bhumika.MiniMart.Util.DtoMapper;
import com.bhumika.MiniMart.config.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(DtoMapper::toUserDto) // <-- use DtoMapper
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return DtoMapper.toUserDto(user);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // encode password
        user.setName(userDto.getName());
        user.setRole(Role.CUSTOMER);

        User savedUser = userRepository.save(user);

        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), null, savedUser.getRole().name());
        // password null bhejna response me (security ke liye)
    }
    @Override
    public UserDto createUser(UserDto userDto) {
        User user = DtoMapper.toUser(userDto);
        User saved = userRepository.save(user);
        return DtoMapper.toUserDto(saved);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        u.setName(userDto.getName());
        u.setEmail(userDto.getEmail());
        if (userDto.getPassword() != null && !userDto.getPassword().isBlank()) {
            u.setPassword(userDto.getPassword()); // TODO: hash
        }
        User updated = userRepository.save(u);
        return DtoMapper.toUserDto(updated);
    }



    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + loginDto.getEmail()));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new InvalidInputException("Invalid password");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}

