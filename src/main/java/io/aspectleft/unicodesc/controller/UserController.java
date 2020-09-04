package io.aspectleft.unicodesc.controller;

import io.aspectleft.unicodesc.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("by-course-code/{courseCode}")
    public ResponseEntity<List<String>> getTakeCourseByCourseCode(@PathVariable String courseCode) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserByCourseCode(courseCode));
    }
}
