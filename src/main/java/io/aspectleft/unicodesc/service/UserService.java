package io.aspectleft.unicodesc.service;

import io.aspectleft.unicodesc.repository.TakeCourseRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class UserService {
    private final TakeCourseRepository takeCourseRepository;

    public List<String> getUserByCourseCode(String courseCode) {
        return takeCourseRepository.findAllByCourseCode(courseCode)
                .stream()
                .map(takeCourse -> takeCourse.getUser().getUsername())
                .collect(Collectors.toList());
    }
}
