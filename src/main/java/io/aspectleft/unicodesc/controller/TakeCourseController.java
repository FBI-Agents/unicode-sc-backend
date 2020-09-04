package io.aspectleft.unicodesc.controller;

import io.aspectleft.unicodesc.dto.TakeCourseRequest;
import io.aspectleft.unicodesc.dto.TakeCourseResponse;
import io.aspectleft.unicodesc.service.TakeCourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/take-course")
@AllArgsConstructor
public class TakeCourseController {
    private final TakeCourseService takeCourseService;

    @PostMapping
    public ResponseEntity<Void> takeCourse(@RequestBody TakeCourseRequest takeCourseRequest) {
        takeCourseService.save(takeCourseRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<TakeCourseResponse>> getTakeCourseByUsername(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(takeCourseService.getTakeCourseByUsername(username));
    }

    @GetMapping("by-course-code/{courseCode}")
    public ResponseEntity<List<TakeCourseResponse>> getTakeCourseByCourseCode(@PathVariable String courseCode) {
        return ResponseEntity.status(HttpStatus.OK).body(takeCourseService.getTakeCourseByCourseCode(courseCode));
    }
}
