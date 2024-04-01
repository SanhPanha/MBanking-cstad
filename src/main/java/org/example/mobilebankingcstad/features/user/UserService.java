package org.example.mobilebankingcstad.features.user;

import org.example.mobilebankingcstad.features.user.dto.UserRequest;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
}

