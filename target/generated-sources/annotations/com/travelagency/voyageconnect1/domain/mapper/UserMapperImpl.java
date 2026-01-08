package com.travelagency.voyageconnect1.domain.mapper;

import com.travelagency.voyageconnect1.domain.dto.UserDto;
import com.travelagency.voyageconnect1.domain.entity.User;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-01-08T00:14:16+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Ubuntu)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        Set<String> roles = null;
        Long id = null;
        String username = null;
        String email = null;

        roles = rolesToStrings( user.getRoles() );
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();

        UserDto userDto = new UserDto( id, username, email, roles );

        return userDto;
    }
}
