package org.example.mobilebankingcstad.features.user;

import org.example.mobilebankingcstad.features.user.dto.UserRequest;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(String id);
}

