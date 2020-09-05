package io.aspectleft.unicodesc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeCourseRequest {
    private String courseCode;
    private String courseName;

}
