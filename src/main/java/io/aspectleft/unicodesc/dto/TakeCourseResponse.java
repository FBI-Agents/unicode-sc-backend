package io.aspectleft.unicodesc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TakeCourseResponse {
    private Long takeCourseId;
    private String courseCode;
    private String courseName;

    private String username;
}
