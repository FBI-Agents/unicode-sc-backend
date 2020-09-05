package io.aspectleft.unicodesc.repository;

import io.aspectleft.unicodesc.model.Location;
import io.aspectleft.unicodesc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findTopByUserOrderByCreatedDesc(User user);

    @Query(value = "select * " +
            "from location " +
            "where created = ( " +
            "select MAX(l1.created) from location l1 where l1.user_id = location.user_id" +
            ") " +
            "group by user_id", nativeQuery = true)
    List<Location> getCurrentLocations();

//
//    @Query(value = "select * from" +
//            "(select * " +
//            "from location l0" +
//            "where l0.created = ( " +
//            "select MAX(l1.created) from location l1 where l1.user_id = l0.user_id" +
//            ") as l3 " +
//            "group by l0.user_id) " +
//            "where (POW(69.1 * (l3.latitude - ?1)) + POW(69.1 * (?2 - l3.longitude) * COS(l3.latitude / 57.3), 2)) < 1", nativeQuery = true)
//    List<Location> findNearByUsers(Double latitude, Double longitude);
}
