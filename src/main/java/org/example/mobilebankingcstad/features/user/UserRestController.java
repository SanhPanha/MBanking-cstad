package org.example.mobilebankingcstad.features.user;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.features.user.dto.UserRequest;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;
import org.example.mobilebankingcstad.utils.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping
    @Operation(summary = "Register new user")
    public BaseResponse<UserResponse> registerUser(
            @Valid @RequestBody UserRequest userRequest) {
        return BaseResponse
                .<UserResponse>createSuccess()
                .setPayload(userService.createUser(userRequest));
    }

    @GetMapping
    @Operation(summary = "Get all users")
    public BaseResponse<List<UserResponse>> getAllUsers() {
        return BaseResponse
                .<List<UserResponse>>ok()
                .setPayload(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public BaseResponse<UserResponse> getUserById( @PathVariable String id) {
        return BaseResponse
                .<UserResponse>ok()
                .setPayload(userService.getUserById(id));
    }
}

