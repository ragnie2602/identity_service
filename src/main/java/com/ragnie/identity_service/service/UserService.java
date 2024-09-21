package com.ragnie.identity_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ragnie.identity_service.dto.request.UserCreationRequest;
import com.ragnie.identity_service.dto.request.UserUpdateRequest;
import com.ragnie.identity_service.entity.User;
import com.ragnie.identity_service.exception.AppException;
import com.ragnie.identity_service.exception.ErrorCode;
import com.ragnie.identity_service.mapper.UserMapper;
import com.ragnie.identity_service.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public User createUser(UserCreationRequest request) {
        if (userRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.USER_EXITED);

        // return userRepository.save(new User(
        // request.getUsername(),
        // request.getPassword(),
        // request.getFirstName(),
        // request.getLastName(),
        // request.getDob()));

        return userRepository.save(userMapper.toUser(request));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public User updateUser(String userId, UserUpdateRequest request) {
        User user = getUser(userId);

        if (request.getPassword() != null)
            user.setPassword(request.getPassword());
        if (request.getFirstName() != null)
            user.setFirstName(request.getFirstName());
        if (request.getLastName() != null)
            user.setLastName(request.getLastName());
        if (request.getDob() != null)
            user.setDob(request.getDob());

        return userRepository.save(user);
    }
}
