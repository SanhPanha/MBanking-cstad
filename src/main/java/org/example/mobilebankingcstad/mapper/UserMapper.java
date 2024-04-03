package org.example.mobilebankingcstad.mapper;


import org.example.mobilebankingcstad.domain.Role;
import org.example.mobilebankingcstad.domain.User;
import org.example.mobilebankingcstad.features.user.dto.UserRequest;
import org.example.mobilebankingcstad.features.user.dto.UserResponse;
import org.example.mobilebankingcstad.features.user.dto.UserUpdateRequest;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    @Mapping(target = "studentCardId", source = "studentIdCard")
    UserResponse toUserResponse(User user);

    default Set<String> mapRoles(Set<Role> roles) {
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }


    //    define the mapping from UserRequest to User
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "studentIdCard", source = "studentCardId")
    User requestToUser(UserRequest userRequest);


    @Mapping(target = "roles", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequest(@MappingTarget User user, UserUpdateRequest userRequest);
}
