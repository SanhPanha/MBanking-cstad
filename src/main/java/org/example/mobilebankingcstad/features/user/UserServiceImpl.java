package org.example.mobilebankingcstad.features.user;

import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.domain.Role;
import org.example.mobilebankingcstad.domain.User;
import org.example.mobilebankingcstad.features.roles.RoleRepository;
import org.example.mobilebankingcstad.features.user.dto.UserRequest;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;
import org.example.mobilebankingcstad.features.user.dto.UserUpdateRequest;
import org.example.mobilebankingcstad.features.userAccount.UserAccountRepository;
import org.example.mobilebankingcstad.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final UserAccountRepository userAccountRepository;

    @Override
    public UserResponse createUser(UserRequest userRequest) {

        if (userRepository.existsByUsername(userRequest.username())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username: <" + userRequest.username() + "> " +
                    "already exists!");
        }

        if (userRepository.existsByEmail(userRequest.email())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email: <" + userRequest.email() + "> already " +
                    "exists!");
        }

        //userRepository.save(userRequest);
        Set<Role> roles = new HashSet<>();
        for (var role : userRequest.roles()) {
            var roleObj = roleRepository.findByName(role).orElseThrow(
                    () -> new NoSuchElementException("Role: <" + role + "> not found!")
            );
            roles.add(roleObj);
        }
        User newUser = userMapper.requestToUser(userRequest);
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return userMapper.toUserResponse(newUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(String id) {
        var user = userRepository.findById(id).orElseThrow(); //NoSuchElementException...
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUserById(String id) {
        var user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User with id: <" + id + "> not found!")
        );
        userRepository.delete(user);
    }

    @Override
    public UserResponse updateUserById(String id, UserUpdateRequest userRequest) {
        var updatedUser = userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User with id: <" + id + "> not found!")
        );

        userMapper.updateFromRequest(updatedUser, userRequest);
//        System.out.println(updatedUser);  // display the updated user in the console
        return userMapper.toUserResponse(userRepository.save(updatedUser));
    }

    @Override
    public UserResponse disableUser(String id) {
        int affectedRow = userRepository.updateBlockedStatusById(id, true);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(
                    userRepository.findById(id).orElse(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with id: <" + id + "> not found!"
            );
        }
    }

    @Override
    public UserResponse enableUser(String id) {
        int affectedRow = userRepository.updateBlockedStatusById(id, false);
        if (affectedRow > 0) {
            return userMapper.toUserResponse(
                    userRepository.findById(id).orElse(null));
        } else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "User with id: <" + id + "> not found!"
            );
        }
    }

}


