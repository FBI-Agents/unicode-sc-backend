package io.aspectleft.unicodesc.mapper;

import io.aspectleft.unicodesc.dto.LocationRequest;
import io.aspectleft.unicodesc.dto.LocationResponse;
import io.aspectleft.unicodesc.model.Location;
import io.aspectleft.unicodesc.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    @Mapping(target = "created", expression = "java(java.time.Instant.now())")
    Location map(LocationRequest locationRequest, User user);

    @Mapping(target = "username", source = "user.username")
    LocationResponse mapToDto(Location location);
}
