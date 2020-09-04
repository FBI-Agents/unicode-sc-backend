package io.aspectleft.unicodesc.mapper;

import io.aspectleft.unicodesc.dto.TakeCourseRequest;
import io.aspectleft.unicodesc.dto.TakeCourseResponse;
import io.aspectleft.unicodesc.model.TakeCourse;
import io.aspectleft.unicodesc.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class TakeCourseMapper {
    public abstract TakeCourse map(TakeCourseRequest takeCourseRequest, User user);

    @Mapping(target = "username", source = "user.username")
    public abstract TakeCourseResponse mapToDto(TakeCourse takeCourse);
}
