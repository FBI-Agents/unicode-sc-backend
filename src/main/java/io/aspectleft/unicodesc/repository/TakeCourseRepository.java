package io.aspectleft.unicodesc.repository;

import io.aspectleft.unicodesc.model.TakeCourse;
import io.aspectleft.unicodesc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakeCourseRepository extends JpaRepository<TakeCourse, Long> {
    List<TakeCourse> findByUser(User user);

    List<TakeCourse> findAllByCourseCode(String courseCode);
}
