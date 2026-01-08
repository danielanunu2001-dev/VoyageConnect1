package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.UserDto;
import com.travelagency.voyageconnect1.domain.entity.Role;
import com.travelagency.voyageconnect1.domain.entity.User;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", source = "roles", qualifiedByName = "rolesToStrings")
    UserDto toDto(User user);

    @Named("rolesToStrings")
    default Set<String> rolesToStrings(Set<Role> roles) {
        if (roles == null) {
            return null;
        }
        return roles.stream().map(Role::getName).collect(Collectors.toSet());
    }
}
