package com.noz.identity.service;

import com.noz.identity.dto.request.UserCreationRequest;
import com.noz.identity.dto.request.UserUpdateRequest;
import com.noz.identity.dto.response.UserResponse;
import com.noz.identity.entity.User;
import com.noz.identity.enums.Role;
import com.noz.identity.exception.AppException;
import com.noz.identity.exception.ErrorCode;
import com.noz.identity.mapper.UserMapper;
import com.noz.identity.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = userMapper.toUser(request);
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.toUserResponse(userRepository.save(user));

    }
    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("user not found"));
        userMapper.updateUser(user,request);

        return userMapper.toUserResponse(userRepository.save(user));

    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
    public List<UserResponse> getUser(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .toList();
    }


    public UserResponse getUser(String id){
        return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(()->new RuntimeException("user not found")));
    }
}
