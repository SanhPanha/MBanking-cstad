package org.example.mobilebankingcstad.features.user;

import org.example.mobilebankingcstad.features.user.dto.UserRequest;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;
import org.example.mobilebankingcstad.features.user.dto.UserUpdateRequest;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserById(String id);

    void deleteUserById(String id);

    UserResponse updateUserById(String id, UserUpdateRequest userRequest);

    UserResponse disableUser(String id);

    UserResponse enableUser(String id);
}

