package org.example.mobilebankingcstad.features.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.features.user.dto.UserRequest;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;
import org.example.mobilebankingcstad.features.user.dto.UserUpdateRequest;
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
    @Operation(summary = "Register new user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            schema = @Schema(implementation = UserRequest.class),
                            examples = @ExampleObject(
                                    value = """
                                            {
                                              "username": "sokkhann",
                                              "fullName": "string",
                                              "gender": "male",
                                              "pin": "898989",
                                              "email": "sokkhann@gmail.com",
                                              "password": "string",
                                              "profileImage": "string",
                                              "phoneNumber": "string",
                                              "cityOrProvince": "string",
                                              "khanOrDistrict": "string",
                                              "sangkatOrCommune": "string",
                                              "employeeType": "string",
                                              "companyName": "string",
                                              "mainSourceOfIncome": "string",
                                              "monthlyIncomeRange": 0,
                                              "studentCardId": "string",
                                              "roles": [
                                                "ADMIN","STUFF"
                                              ]
                                            }
                                                                        
                                                                        
                                            """
                            )
                    )
            )
    )
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
    public BaseResponse<UserResponse> getUserById(@PathVariable String id) {
        return BaseResponse
                .<UserResponse>ok()
                .setPayload(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user by id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public BaseResponse<?> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return BaseResponse.ok();
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update user by id")
    public BaseResponse<UserResponse> updateUserByID(@PathVariable String id, @RequestBody UserUpdateRequest userRequest) {
        return BaseResponse
                .<UserResponse>updateSuccess()
                .setPayload(userService.updateUserById(id, userRequest));
    }

    @PatchMapping("/{ID}/disable")
    @Operation(summary = "Disable user by id")
    public BaseResponse<UserResponse> disableUser(@PathVariable String ID){
        return BaseResponse
                .<UserResponse>disableSuccess()
                .setPayload(userService.disableUser(ID));
    }

    @PatchMapping("/{ID}/enable")
    @Operation(summary = "Enable user by id")
    public BaseResponse<UserResponse> enableUser(@PathVariable String ID){
        return BaseResponse
                .<UserResponse>enableSuccess()
                .setPayload(userService.enableUser(ID));
    }
}

