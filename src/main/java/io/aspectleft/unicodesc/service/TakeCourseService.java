package io.aspectleft.unicodesc.service;

import io.aspectleft.unicodesc.dto.TakeCourseRequest;
import io.aspectleft.unicodesc.dto.TakeCourseResponse;
import io.aspectleft.unicodesc.mapper.TakeCourseMapper;
import io.aspectleft.unicodesc.model.User;
import io.aspectleft.unicodesc.repository.TakeCourseRepository;
import io.aspectleft.unicodesc.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class TakeCourseService {
    private final TakeCourseRepository takeCourseRepository;
    private final TakeCourseMapper takeCourseMapper;
    private final AuthService authService;

    private final UserRepository userRepository;

    public void save(TakeCourseRequest takeCourseRequest) {
        takeCourseRepository.save(takeCourseMapper.map(takeCourseRequest, authService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public List<TakeCourseResponse> getTakeCourseByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return takeCourseRepository.findByUser(user)
                .stream()
                .map(takeCourseMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TakeCourseResponse> getTakeCourseByCourseCode(String courseCode) {
        return takeCourseRepository.findAllByCourseCode(courseCode)
                .stream()
                .map(takeCourseMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
