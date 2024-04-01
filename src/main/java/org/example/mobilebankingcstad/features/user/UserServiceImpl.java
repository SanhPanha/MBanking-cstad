package org.example.mobilebankingcstad.features.user;


import lombok.RequiredArgsConstructor;
import org.example.mobilebankingcstad.domain.Role;
import org.example.mobilebankingcstad.domain.User;
import org.example.mobilebankingcstad.features.roles.RoleRepository;
import org.example.mobilebankingcstad.features.user.dto.UserRequest;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;
import org.example.mobilebankingcstad.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    @Override
    public UserResponse createUser(UserRequest userRequest) {
        //userRepository.save(userRequest);
        Set<Role> roles = new HashSet<>();
        for(var role : userRequest.roles()){
            var roleObj = roleRepository.findByName(role).orElseThrow();
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
}


